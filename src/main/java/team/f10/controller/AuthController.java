package team.f10.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import team.f10.dto.LoginUserDto;
import team.f10.dto.RegisterUserDto;
import team.f10.dto.UserDto;
import team.f10.exception.LoginFailedException;
import team.f10.model.User;
import team.f10.service.PhotoService;
import team.f10.service.UserService;

@Controller
@Slf4j
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PhotoService photoService;

    private static final String PAGE_META_NAME = "Authentication";

    @GetMapping("/login")
    public String loginView(Model model) {
        model.addAttribute("metaName", PAGE_META_NAME);
        model.addAttribute("user", new LoginUserDto());
        return "auth/login";
    }

    @PostMapping("/login")
    public RedirectView processLogin(@ModelAttribute("user") LoginUserDto loginUserDto, RedirectAttributes redirectAttributes) {
        boolean success = userService.processLogin(loginUserDto);

        if (!success) {
            log.error("User " + loginUserDto.getUsername() + " login failed");
            throw new LoginFailedException("User " + loginUserDto.getUsername() + " login failed");
        }

        log.info("User " + loginUserDto.getUsername() + " just logged.");
        return new RedirectView("/", true); //todo status page?
    }

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("metaName", PAGE_META_NAME);
        model.addAttribute("user", new RegisterUserDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public RedirectView processRegister(@ModelAttribute("user") RegisterUserDto dto,
                                        @RequestParam("image") MultipartFile image,
                                        RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/", true);

        User user = userService.addUser(dto);
        log.info("Added user " + user.getUsername());

        photoService.addPhoto(image, user);

        return redirectView;
    }
}
