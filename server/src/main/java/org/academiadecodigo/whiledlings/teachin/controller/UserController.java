package org.academiadecodigo.whiledlings.teachin.controller;

import org.academiadecodigo.whiledlings.teachin.command.UserDto;
import org.academiadecodigo.whiledlings.teachin.converters.UserDtoToUser;
import org.academiadecodigo.whiledlings.teachin.converters.UserToUserDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.User;
import org.academiadecodigo.whiledlings.teachin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    private UserToUserDto userToUserDto;
    private UserDtoToUser userDtoToUser;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserToUserDto(UserToUserDto userToUserDto) {
        this.userToUserDto = userToUserDto;
    }

    @Autowired
    public void setUserDtoToUser(UserDtoToUser userDtoToUser) {
        this.userDtoToUser = userDtoToUser;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<UserDto>> listUsers() {

        List<UserDto> usersDtos = new ArrayList<>();

        for (User user : userService.list()) {
            usersDtos.add(userToUserDto.convert(user));
        }

        return new ResponseEntity<>(usersDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<HttpHeaders> addUser(@RequestBody UserDto userDto, UriComponentsBuilder uriComponentsBuilder) {

        User savedUser = userService.save(userDtoToUser.convert(userDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/users/" + savedUser.getId()).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/{id}"})
    public ResponseEntity<UserDto> deleteUser(@PathVariable Integer id) {

        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/{id}"})
    public ResponseEntity<UserDto> userDetails(@PathVariable Integer id) {

        User user;

        user = userService.get(id);

        return new ResponseEntity<>(userToUserDto.convert(user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/{id}"})
    public ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto, @PathVariable Integer id) {

        if (userDto.getId() != null && !userDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (userService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userDto.setId(id);

        userService.save(userDtoToUser.convert(userDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
