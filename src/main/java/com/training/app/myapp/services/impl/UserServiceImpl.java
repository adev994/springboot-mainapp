package com.training.app.myapp.services.impl;

import com.training.app.myapp.entities.AddressEntity;
import com.training.app.myapp.entities.UserEntity;
import com.training.app.myapp.generated.Utils;
import com.training.app.myapp.repositories.UserRepository;
import com.training.app.myapp.services.UserService;
import com.training.app.myapp.shared.dto.UserAddressDto;
import com.training.app.myapp.shared.dto.UserDto;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    Utils utils;

    @Autowired
    UserRepository userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity checkUser = userRepository.findByEmail(user.getEmail());
        if (checkUser !=null){
            throw new RuntimeException("The User already exists");
        }

        ModelMapper modelMapper=new ModelMapper();
        for(int i=0;i<user.getAddresses().size();i++)
        {
            UserAddressDto userAddressDto = user.getAddresses().get(i);
            userAddressDto.setUserDto(user);
            userAddressDto.setAddressId(utils.generateStringId(5));
            user.getAddresses().set(i,userAddressDto);
        }
        user.getContact().setContactId(utils.generateStringId(5));
        user.getContact().setUserDto(user);
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setPasswordEncrypted(passwordEncoder.encode(user.getPassword()));
        userEntity.setUserId(utils.generateStringId(30));
        userEntity.getAddresses().forEach(addressEntity -> addressEntity.setUser(userEntity));
        UserEntity createUser = userRepository.save(userEntity);
        UserDto userDto = modelMapper.map(createUser, UserDto.class);

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity==null){
            throw new UsernameNotFoundException("Sorry, User Not exist");
        }
        return new User(userEntity.getEmail(),userEntity.getPasswordEncrypted(), Arrays.asList());
    }

    @Override
    public UserDto getUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity==null){
            throw new RuntimeException("UserId Not Exist");
        }

        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(userEntity,userDto);
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId,UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity==null){
            throw new RuntimeException("UserId Not Exist");
        }
        userEntity.setUserName(userDto.getUserName());
        userEntity.setPasswordEncrypted(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setEmail(userDto.getEmail());
        userEntity.setAdmin(userDto.getAdmin());
        userEntity.setActive(userDto.getActive());
        userRepository.save(userEntity);
        UserDto userDtoNew=new UserDto();
        BeanUtils.copyProperties(userEntity,userDtoNew);
        return userDtoNew;
    }

    @Override
    public UserDto deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity==null){
            throw new RuntimeException("User Not exist");
        }
        userRepository.delete(userEntity);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(userEntity,userDto);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers(int page, int limit) {
        if(page>0){
            page -= 1;
        }
        Pageable pageable=PageRequest.of(page,limit);
        Page<UserEntity> userEntities = userRepository.findAllByuserName(pageable);
        List<UserDto> userDtos=new ArrayList<UserDto>();
        for (UserEntity userEntity : userEntities){
            ModelMapper modelMapper=new ModelMapper();
            UserDto userDto = modelMapper.map(userEntity, UserDto.class);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity==null){
            throw new RuntimeException("UserId Not Exist");
        }

        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(userEntity,userDto);
        return userDto;

    }
}

