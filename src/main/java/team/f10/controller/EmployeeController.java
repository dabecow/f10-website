package team.f10.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import team.f10.dto.EmployeeDto;
import team.f10.model.Employee;
import team.f10.repository.OccupationRepository;
import team.f10.service.EmployeeService;
import team.f10.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private static final String PAGE_META_NAME = "employees";

    private final EmployeeService employeeService;
    private final UserService userService;
    private final OccupationRepository occupationRepository;

    @GetMapping
    public String viewEmployees(Model model) {
        List<EmployeeDto> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("metaName", PAGE_META_NAME);

        return "view-employees";
    }

    @GetMapping("/add")
    public String addEmployeeView(Model model) {
        model.addAttribute("users", userService.getNotEmployeesUsers());
        model.addAttribute("occupations", occupationRepository.findAll());
        return "add-employee";
    }

    @PostMapping("/add")
    public RedirectView addEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/employees/add", true);
        Employee e = employeeService.addEmployee(employee);
        redirectAttributes.addFlashAttribute("savedEmployee", e);
        redirectAttributes.addFlashAttribute("saveEmployeeSuccess", true);
        return redirectView;
    }
}
