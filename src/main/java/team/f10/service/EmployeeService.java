package team.f10.service;

import team.f10.dto.AddEmployeeDto;
import team.f10.dto.EditEmployeeDto;
import team.f10.dto.EmployeeDto;
import team.f10.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(Employee employee);
    void addEmployee(AddEmployeeDto employee);
    List<EmployeeDto> getEmployees();
    void editEmployee(EditEmployeeDto employeeDto);
    void removeEmployee(EditEmployeeDto employeeDto);
}
