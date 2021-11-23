package ru.red.otussimplerestfulcrud.mapper;

import org.springframework.stereotype.Component;
import ru.red.otussimplerestfulcrud.domain.User;
import ru.red.otussimplerestfulcrud.dto.UserPostDto;

@Component
public class UserMapper {
    public User userPostDtoToUser(UserPostDto userPostDto) {
        User user = new User();
        user.setUsername(userPostDto.getUsername());
        user.setFirstName(userPostDto.getFirstName());
        user.setLastName(userPostDto.getLastName());
        user.setEmail(userPostDto.getEmail());
        user.setPhone(userPostDto.getPhone());
        return user;
    }

    public UserPostDto userToUserPostDto(User user) {
        UserPostDto userPostDto = new UserPostDto();
        userPostDto.setUsername(user.getUsername());
        userPostDto.setFirstName(user.getFirstName());
        userPostDto.setLastName(user.getLastName());
        userPostDto.setEmail(user.getEmail());
        userPostDto.setPhone(user.getPhone());
        return userPostDto;
    }
}
