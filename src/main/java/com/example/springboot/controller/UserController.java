package com.example.springboot.controller;

import com.example.springboot.data.User;
import com.example.springboot.dto.UserDto;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/v1/user" )
public class UserController {
    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        try{
        return new ResponseEntity<>(userService.getAll(), HttpStatus.ACCEPTED);}
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<User> findById(@PathVariable String id ) {
        try{
            return new ResponseEntity<>(userService.findById(id), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }


    @PostMapping
    public ResponseEntity<User> create( @RequestBody UserDto userDto ) {
        try {
            User user = new User("", userDto.getName(), userDto.getEmail(), userDto.getLastName(), "");
            return new ResponseEntity<>(userService.create(user), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<User> update( @RequestBody UserDto userDto, @PathVariable String id ) {
        try{
            User user = userService.findById(id);
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setLastName(userDto .getLastName());
            return new ResponseEntity<>(userService.update(user, id),HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> delete( @PathVariable String id ) {
        try{
            userService.deleteById(id);
            return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}