package team.f10.service;

import team.f10.dto.EmployeeDto;
import team.f10.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    Employee linkUserToOccupation(Long userId, Long occupationId);
    List<EmployeeDto> getEmployees();
}
