package team.f10.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team.f10.dto.AssignRoleDto;
import team.f10.dto.EmployeeDto;
import team.f10.dto.LoginUserDto;
import team.f10.dto.RegisterUserDto;
import team.f10.dto.UserDto;
import team.f10.exception.NoSuchUserException;
import team.f10.mapper.UserMapper;
import team.f10.model.Photo;
import team.f10.model.Role;
import team.f10.model.User;
import team.f10.repository.UserRepository;
import team.f10.service.PhotoService;
import team.f10.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PhotoService photoService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User addUser(RegisterUserDto dto, MultipartFile image) {
        User user = userMapper.toUser(dto);
        user.setRole(Role.ROLE_USER);
        userRepository.save(user); //user should be persisted before adding photo by him
        Photo photo = photoService.addPhoto(image, user);
        user.setProfileImage(photo);
        return userRepository.save(user);
    }

    @Override
    public List<UserDto> getUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new NoSuchUserException(id);

        return user.get();
    }

    @Override
    public List<UserDto> getNotEmployeesUsers() {
        return userMapper.toDtoList(userRepository.findByRoleIsNullOrRoleIn(List.of(Role.ROLE_USER)));
    }

    @Override
    public Boolean processLogin(LoginUserDto dto) {
        return userRepository.existsUserByUsernameAndPassword(dto.getUsername(), dto.getPassword());
    }

    @Override
    @Transactional
    public List<EmployeeDto> getEmployees() {
        return userMapper.toEmployeesList(userRepository.findByRoleIsNullOrRoleIn(List.of(Role.ROLE_ADMIN, Role.ROLE_DIRECTOR)));
    }

    @Override
    public void assignRoleToUser(AssignRoleDto dto) {
        User user = getUserById(dto.getUserId());
        user.setRole(dto.getRole());
        userRepository.save(user);
    }

    @Override
    public void unassignEmploymentRole(AssignRoleDto dto) {
        User user = getUserById(dto.getUserId());
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }
}
