package com.training.app.myapp.controllers;


import com.training.app.myapp.requests.UserAddressesRequest;
import com.training.app.myapp.responses.AddressResponse;
import com.training.app.myapp.services.AddressService;
import com.training.app.myapp.shared.dto.UserAddressDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

@RestController()
@RequestMapping(value="/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddresses(Principal principal){

        String email=principal.getName();
        List<UserAddressDto> allAddresses = addressService.getAllAddresses(email);
        ModelMapper modelMapper=new ModelMapper();
        Type addresses = new TypeToken<List<AddressResponse>>() {}.getType();
        List<AddressResponse> allAddressResponse = modelMapper.map(allAddresses, addresses);
        return new ResponseEntity<>(allAddressResponse, HttpStatus.OK);
    }


   @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity storeAddresse(@RequestBody UserAddressesRequest addressRequest, Principal principal){

      ModelMapper modelMapper=new ModelMapper();

        UserAddressDto addressDto=modelMapper.map(addressRequest,UserAddressDto.class);
        UserAddressDto createAddress=addressService.createAddress(addressDto,principal.getName());

        AddressResponse newAddress=modelMapper.map(createAddress,AddressResponse.class);

        return new ResponseEntity(newAddress,HttpStatus.CREATED);

    }


    @GetMapping("/{id}")
    public  ResponseEntity<AddressResponse> getOneAddresse(@PathVariable(name="id") String addressId) {

        UserAddressDto addressDto = addressService.getAddress(addressId);

        ModelMapper modelMapper = new ModelMapper();

        AddressResponse addressResponse = modelMapper.map(addressDto, AddressResponse.class);

        return new ResponseEntity(addressResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatreAddresse(@PathVariable(name="id") String addressId,@RequestBody UserAddressesRequest addressesRequest,Principal principal) {
        ModelMapper modelMapper = new ModelMapper();
        UserAddressDto userAddressDto=modelMapper.map(addressesRequest, UserAddressDto.class);
        userAddressDto.setAddressId(addressId);
        addressService.updateAddress(userAddressDto,principal.getName());
        return new ResponseEntity<>("update addresses", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddresse(@PathVariable(name="id") String addressId) {

        addressService.deleteAddress(addressId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
