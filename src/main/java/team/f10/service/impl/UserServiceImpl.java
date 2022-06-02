package team.f10.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.f10.dto.LoginUserDto;
import team.f10.dto.RegisterUserDto;
import team.f10.dto.UserDto;
import team.f10.mapper.UserMapper;
import team.f10.model.User;
import team.f10.repository.UserRepository;
import team.f10.service.UserService;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User addUser(RegisterUserDto dto) {
        return userRepository.save(userMapper.toUser(dto));
    }

    @Override
    public List<UserDto> getUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public List<UserDto> getNotEmployeesUsers() {
        return userMapper.toDtoList(userRepository.findAllNonEmployees());
    }

    @Override
    public Boolean processLogin(LoginUserDto dto) {
        return userRepository.existsUserByUsernameAndPassword(dto.getUsername(), dto.getPassword());
    }
}
