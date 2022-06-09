package team.f10.controller.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.f10.dto.ArticleSmallDto;

@RestController
@RequestMapping("/api/articles/")
public class ArticleRestController {

    @GetMapping("/edit")
    public ArticleSmallDto viewEditArticles(Model model) {
//        model.addAttribute("article", new CreateArticleDto());
        return new ArticleSmallDto(1L, null, null);
    }
}
