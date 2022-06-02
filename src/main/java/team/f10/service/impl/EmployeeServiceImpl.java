package team.f10.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.f10.dto.EmployeeDto;
import team.f10.mapper.EmployeeMapper;
import team.f10.model.Employee;
import team.f10.model.Occupation;
import team.f10.model.User;
import team.f10.repository.EmployeeRepository;
import team.f10.repository.OccupationRepository;
import team.f10.repository.UserRepository;
import team.f10.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OccupationRepository occupationRepository;
    private final UserRepository userRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Employee addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee linkUserToOccupation(Long userId, Long occupationId) {
        //todo error handling
        Optional<User> user = userRepository.findById(userId);
        Optional<Occupation> occupation = occupationRepository.findById(occupationId);

        if (!user.isPresent())
            throw new RuntimeException();

        if (!occupation.isPresent())
            throw new RuntimeException();

        Employee employee = new Employee(user.get(), occupation.get());

        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public List<EmployeeDto> getEmployees() {
        return employeeMapper.toDtoList(employeeRepository.findAllFetchPhotos());
    }

}
