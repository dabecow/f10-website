package team.f10.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team.f10.dto.EmployeeDto;
import team.f10.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EmployeeMapper {

    private final PhotoMapper photoMapper;

    public EmployeeDto toDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getUser().getFirstName())
                .occupation(employee.getOccupation().getName())
                .photo(photoMapper.toDto(employee.getUser().getProfileImage()))
                .username(employee.getUser().getUsername())
                .build();
    }

    public List<EmployeeDto> toDtoList(List<Employee> employees) {
        return employees.stream().map(this::toDto).collect(Collectors.toList());
    }
}
