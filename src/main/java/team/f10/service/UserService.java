package team.f10.service;

import org.springframework.web.multipart.MultipartFile;
import team.f10.dto.AssignRoleDto;
import team.f10.dto.EmployeeDto;
import team.f10.dto.LoginUserDto;
import team.f10.dto.RegisterUserDto;
import team.f10.dto.UserDto;
import team.f10.model.User;

import java.util.List;

public interface UserService {
    User addUser(RegisterUserDto dto, MultipartFile image);
    List<UserDto> getUsers();
    User getUserById(Long id);
    List<UserDto> getNotEmployeesUsers();
    Boolean processLogin(LoginUserDto dto);
    List<EmployeeDto> getEmployees();
    void assignRoleToUser(AssignRoleDto dto);
    void unassignEmploymentRole(AssignRoleDto dto);
}
