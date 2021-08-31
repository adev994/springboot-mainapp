package com.training.app.myapp.controllers;

import com.training.app.myapp.exceptions.UserException;
import com.training.app.myapp.requests.UserRequest;
import com.training.app.myapp.exceptions.UserErrors;
import com.training.app.myapp.responses.UserResponse;
import com.training.app.myapp.services.UserService;
import com.training.app.myapp.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public List<UserResponse> getAllUsers(@RequestParam(value = "page",defaultValue = "1")int page,
                                          @RequestParam(value = "limit",defaultValue = "10")int limit)
    {
        List<UserDto> userDtos = userService.getAllUsers(page, limit);
        List<UserResponse> userResponses=new ArrayList<UserResponse>();
        for (UserDto userDto : userDtos){
            ModelMapper modelMapper=new ModelMapper();
            UserResponse userResponse = modelMapper.map(userDto, UserResponse.class);
            userResponses.add(userResponse);
        }
        return userResponses;
    }


    @GetMapping(path="/{userId}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId){
        ModelMapper modelMapper=new ModelMapper();

        UserDto userDto = userService.getUser(userId);

        UserResponse userResponse = modelMapper.map(userDto, UserResponse.class);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);

    }
    @PostMapping(consumes={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {
        if(userRequest.getUserName()==null){
            throw new UserException(UserErrors.Field_Required.getError_message());
        }
        ModelMapper modelMapper=new ModelMapper();
        UserDto userDto = modelMapper.map(userRequest, UserDto.class);
        UserDto createUser=userService.createUser(userDto);
        UserResponse userResponse = modelMapper.map(createUser,UserResponse.class);
        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }



    @PutMapping(path="/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String userId,@RequestBody UserRequest userRequest){
        ModelMapper modelMapper=new ModelMapper();
        UserDto userDto = modelMapper.map(userRequest, UserDto.class);
        UserDto updateuser = userService.updateUser(userId, userDto);
        UserResponse userResponse = modelMapper.map(updateuser, UserResponse.class);
        return new ResponseEntity<>(userResponse,HttpStatus.ACCEPTED);
    }


    @DeleteMapping(path="/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userId){
        UserDto deleteUser = userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
