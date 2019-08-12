package org.academiadecodigo.whiledlings.teachin.converters;

import org.academiadecodigo.whiledlings.teachin.command.UserDto;
import org.academiadecodigo.whiledlings.teachin.persistence.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDto extends AbstractConverter<User, UserDto> {

    @Override
    public UserDto convert(User user) {

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());

        return userDto;
    }

}
