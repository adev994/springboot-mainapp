package com.training.app.myapp.services;

import com.training.app.myapp.shared.dto.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends UserDetailsService {

    public UserDto createUser(UserDto userDto);
    public UserDto getUser(String userId);
    public UserDto getUserByEmail(String email);
    public UserDto updateUser(String userId,UserDto userDto);
    public UserDto deleteUser(String userId);
    public List<UserDto> getAllUsers(int page, int limit);
}
