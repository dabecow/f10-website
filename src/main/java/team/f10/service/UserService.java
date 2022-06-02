package team.f10.service;

import team.f10.dto.LoginUserDto;
import team.f10.dto.RegisterUserDto;
import team.f10.dto.UserDto;
import team.f10.model.User;

import java.util.List;

public interface UserService {
    User addUser(RegisterUserDto dto);
    List<UserDto> getUsers();

    List<UserDto> getNotEmployeesUsers();

    Boolean processLogin(LoginUserDto dto);
}
