package org.academiadecodigo.whiledlings.teachin.converters;

import org.academiadecodigo.whiledlings.teachin.command.UserDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.User;
import org.academiadecodigo.whiledlings.teachin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;

@Component
public class UserDtoToUser implements Converter<UserDto, User> {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User convert(UserDto userDto) {

        User user = (userDto.getId() != null ? userService.get(userDto.getId()) : new User());

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        return user;
    }
}
