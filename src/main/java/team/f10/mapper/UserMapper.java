package team.f10.mapper;

import org.springframework.stereotype.Component;
import team.f10.dto.RegisterUserDto;
import team.f10.dto.UserDto;
import team.f10.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    public List<UserDto> toDtoList(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

    public User toUser(RegisterUserDto dto){
        return User.builder()
                .firstName(dto.getFirstName())
                .patronymic(dto.getPatronymic())
                .lastName(dto.getLastName())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }
}
