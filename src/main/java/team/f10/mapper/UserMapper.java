package team.f10.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.f10.dto.EmployeeDto;
import team.f10.dto.RegisterUserDto;
import team.f10.dto.UserDto;
import team.f10.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PhotoMapper photoMapper;

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
                .occupation(dto.getOccupation())
                .build();
    }

    public EmployeeDto toEmployee(User user) {
        return EmployeeDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .occupation(user.getOccupation())
                .firstName(user.getFirstName())
                .photo(photoMapper.toDto(user.getProfileImage()))
                .build();
    }

    public List<EmployeeDto> toEmployeesList(List<User> users) {
        return users.stream().map(this::toEmployee).collect(Collectors.toList());
    }
}
