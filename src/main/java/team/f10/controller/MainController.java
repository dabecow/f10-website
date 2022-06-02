package team.f10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private static final String PAGE_META_NAME = "main";

    @GetMapping
    public String loadMain(Model model) {
        model.addAttribute("metaName", PAGE_META_NAME);
        return "main";
    }
}
