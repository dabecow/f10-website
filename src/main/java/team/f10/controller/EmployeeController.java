package team.f10.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import team.f10.configuration.security.annotation.AdminOnly;
import team.f10.dto.AssignRoleDto;
import team.f10.dto.EmployeeDto;
import team.f10.model.Role;
import team.f10.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private static final String PAGE_META_NAME = "employees";

    private final UserService userService;

    @GetMapping
    public String viewEmployees(Model model) {
        List<EmployeeDto> employees = userService.getEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("metaName", PAGE_META_NAME);

        return "employee/view-employees";
    }

    @AdminOnly
    @GetMapping("/add")
    public String addEmployeeView(Model model) {
        model.addAttribute("users", userService.getNotEmployeesUsers());
        model.addAttribute("roles", Role.values());
        model.addAttribute("employee", new AssignRoleDto());
        return "employee/add-employee";
    }

    @AdminOnly
    @PostMapping("/add/process")
    public RedirectView addEmployee(@ModelAttribute("employee") AssignRoleDto dto, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/employees", true);
        userService.assignRoleToUser(dto);
        return redirectView;
    }

    @AdminOnly
    @GetMapping("/edit")
    public String editEmployeesView(Model model) {
        model.addAttribute("employees", userService.getEmployees());
        model.addAttribute("roles",  Role.values());
        model.addAttribute("employee", new AssignRoleDto());
        return "employee/edit-employee";
    }

    @AdminOnly
    @PostMapping("/edit/process")
    public RedirectView processEmployeeEdit(@ModelAttribute("employee") AssignRoleDto dto, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/employees", true);
        userService.assignRoleToUser(dto);
        return redirectView;
    }

    @AdminOnly
    @PostMapping("/remove")
    public RedirectView removeEmployee(@ModelAttribute("employee") AssignRoleDto dto, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/employees", true);
        userService.unassignEmploymentRole(dto);
        return redirectView;
    }
}
