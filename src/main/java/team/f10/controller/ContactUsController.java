package team.f10.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import team.f10.dto.MessageDto;

@Slf4j
@Controller
public class ContactUsController {

    @GetMapping("/contact")
    public String contactUsView(Model model) {
        model.addAttribute("message", new MessageDto());
        return "contactus";
    }

    @PostMapping("/contact")
    public RedirectView processAboutUs(@ModelAttribute("message") MessageDto messageDto, RedirectAttributes redirectAttributes) {
        log.info(String.format("Got message from %s (%s): %s", messageDto.getName(), messageDto.getEmail(), messageDto.getText()));
        return new RedirectView("/", true);
    }
}
