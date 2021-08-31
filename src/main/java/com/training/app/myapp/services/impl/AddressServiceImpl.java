package com.training.app.myapp.services.impl;

import com.training.app.myapp.entities.AddressEntity;
import com.training.app.myapp.entities.UserEntity;
import com.training.app.myapp.generated.Utils;
import com.training.app.myapp.repositories.AddressRepository;
import com.training.app.myapp.repositories.UserRepository;
import com.training.app.myapp.services.AddressService;
import com.training.app.myapp.shared.dto.UserAddressDto;
import com.training.app.myapp.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;


@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    Utils utils;
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<UserAddressDto> getAllAddresses(String email) {
        UserEntity user = userRepository.findByEmail(email);


        List<AddressEntity> allAddresses;
        if(user.getAdmin()){
            allAddresses =(List<AddressEntity>) addressRepository.findAll();
        }
        else{
            allAddresses= addressRepository.findAllByUser(user);
        }
        ModelMapper modelMapper=new ModelMapper();
        Type addresses = new TypeToken<List<UserAddressDto>>() {}.getType();
        List<UserAddressDto> allAddressDtos = modelMapper.map(allAddresses, addresses);
        return  allAddressDtos;

    }

    @Override
    public UserAddressDto createAddress(UserAddressDto address, String email) {
        UserEntity currentUser=userRepository.findByEmail(email);
        ModelMapper modelMapper=new ModelMapper();
        UserDto userDto=modelMapper.map(currentUser,UserDto.class);

        address.setAddressId(utils.generateStringId(5));
        address.setUserDto(userDto);

        AddressEntity addressEntity=modelMapper.map(address,AddressEntity.class);
        AddressEntity newAddress=addressRepository.save(addressEntity);
        UserAddressDto addressDto=modelMapper.map(newAddress,UserAddressDto.class);
        return addressDto;
    }

    @Override
    public UserAddressDto getAddress(String addressId) {

        AddressEntity addressEntity = addressRepository.findByAddressId(addressId);

        ModelMapper modelMapper = new ModelMapper();

        UserAddressDto addressDto = modelMapper.map(addressEntity, UserAddressDto.class);

        return addressDto;
    }

    @Override
    public void deleteAddress(String addressId) {

        AddressEntity address = addressRepository.findByAddressId(addressId);

        if(address == null) throw new RuntimeException("Address not found");

        addressRepository.delete(address);

    }

    @Override
    public void updateAddress(UserAddressDto address,String email){

        AddressEntity addressEntity = addressRepository.findByAddressId(address.getAddressId());
        addressEntity.setType(address.getType());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setPostalCode(address.getPostalCode());
        addressEntity.setCity(address.getCity());
        addressRepository.save(addressEntity);

    }
}
