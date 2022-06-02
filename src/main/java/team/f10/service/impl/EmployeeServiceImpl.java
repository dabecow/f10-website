package team.f10.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.f10.dto.AddEmployeeDto;
import team.f10.dto.EditEmployeeDto;
import team.f10.dto.EmployeeDto;
import team.f10.exception.NoSuchEmployeeException;
import team.f10.exception.NoSuchOccupationException;
import team.f10.exception.NoSuchUserException;
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
    public void addEmployee(AddEmployeeDto employee) {
        Optional<User> user = userRepository.findById(employee.getUserId());
        if (user.isEmpty())
            throw new NoSuchUserException(employee.getUserId());

        Employee e = new Employee(user.get(), getOccupationById(employee.getOccupationId()));

        employeeRepository.save(e);
    }

    @Override
    @Transactional
    public List<EmployeeDto> getEmployees() {
        return employeeMapper.toDtoList(employeeRepository.findAllFetchPhotos());
    }

    @Override
    public void editEmployee(EditEmployeeDto employeeDto) {
        Employee employee = getEmployeeById(employeeDto.getEmployeeId());
        Occupation occupation = getOccupationById(employeeDto.getOccupationId());

        employee.setOccupation(occupation);

        employeeRepository.save(employee);
    }

    @Override
    public void removeEmployee(EditEmployeeDto employeeDto) {
        Employee employee = getEmployeeById(employeeDto.getEmployeeId());
        employeeRepository.delete(employee);
    }

    private Employee getEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty())
            throw new NoSuchEmployeeException(employeeId);

        return employee.get();
    }

    private Occupation getOccupationById(Long occupationId) {
        Optional<Occupation> occupation = occupationRepository.findById(occupationId);
        if (occupation.isEmpty())
            throw new NoSuchOccupationException(occupationId);

        return occupation.get();
    }
}
