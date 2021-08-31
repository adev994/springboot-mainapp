package com.training.app.myapp.services;

import com.training.app.myapp.shared.dto.UserAddressDto;

import java.util.List;


public interface AddressService {
    List<UserAddressDto> getAllAddresses(String email);

    UserAddressDto createAddress(UserAddressDto userAddressDto,String email);

    UserAddressDto getAddress(String addressId);

    void deleteAddress(String addressId);
    void updateAddress(UserAddressDto address,String email);

}
