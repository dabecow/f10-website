package team.f10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {

    private static final String PAGE_META_NAME = "aboutUs";

    @GetMapping("/about")
    public String aboutUsView(Model model) {
        model.addAttribute("metaName", PAGE_META_NAME);
        return "about";
    }
}
