package team.f10.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import team.f10.configuration.security.annotation.AdminOnly;
import team.f10.dto.ArticleDto;
import team.f10.dto.ArticleSmallDto;
import team.f10.dto.CreateArticleDto;
import team.f10.dto.EditArticleDto;
import team.f10.service.ArticleService;
import team.f10.service.TagService;

import java.util.Objects;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final TagService tagService;

    private static final int PAGE_SIZE = 5;
    private static final Long HARDCODE_ID = 2L; //todo remove after jwt

    @GetMapping
    public String viewArticles(@RequestParam(required = false) Integer page, Model model) {
        Page<ArticleSmallDto> articlesPage =
                articleService.getArticles(
                        PageRequest.of(
                                Objects.isNull(page) || page <= 0 ? 0 : page,
                                PAGE_SIZE)
                );

        int currentPage = articlesPage.getNumber();
        int numberOfPages = articlesPage.getTotalPages();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("startPage", Math.max(currentPage - 3, 0));
        model.addAttribute("endPage", Math.min(currentPage + 3, Math.max(numberOfPages - 1, 0)));
        model.addAttribute("articles", articlesPage.getContent());
        model.addAttribute("isLast", articlesPage.isLast());
        model.addAttribute("isFirst", articlesPage.isFirst());
        model.addAttribute("maxPage", Math.max(numberOfPages - 1, 0));
        return "articles/view-articles";
    }

    @GetMapping("/create")
    @AdminOnly
    public String viewCreateArticle(Model model) {
        model.addAttribute("article", new CreateArticleDto());
        return "articles/create-articles";
    }

    @PostMapping("/create/process")
    @AdminOnly
    public RedirectView processCreateArticle(@ModelAttribute("article") CreateArticleDto dto,
                                             @RequestParam("image") MultipartFile image,
                                             RedirectAttributes redirectAttributes){
        final RedirectView redirectView = new RedirectView("/articles", true);
        articleService.addArticle(dto, image, HARDCODE_ID);
        return redirectView;
    }

    @GetMapping("/{articleId}")
    public String viewArticle(@PathVariable("articleId") Long articleId, Model model) {
        ArticleDto article = articleService.getArticle(articleId);
        model.addAttribute("article", article);
        return "articles/view-article";
    }

    @GetMapping("/{articleId}/edit")
    @AdminOnly
    public String viewEditArticle(@PathVariable("articleId") Long articleId, Model model) {
        EditArticleDto article = articleService.getArticleForEdit(articleId);
        model.addAttribute("article", article);
        model.addAttribute("articleId", articleId);
        return "articles/edit-article";
    }

    @PostMapping("/{articleId}/edit/process")
    @AdminOnly
    public RedirectView processEditArticle(@ModelAttribute("article") EditArticleDto dto,
                                           @RequestParam("image") MultipartFile image,
                                           @PathVariable("articleId") Long articleId,
                                           Model model) {
        final RedirectView redirectView = new RedirectView("/articles", true);
        articleService.editArticle(articleId, image, dto, HARDCODE_ID);
        return redirectView;
    }

    @GetMapping("/{articleId}/remove")
    @AdminOnly
    public RedirectView viewRemoveArticle(@PathVariable("articleId") Long articleId, Model model) {
        final RedirectView redirectView = new RedirectView("/articles", true);
        articleService.removeArticle(articleId);
        return redirectView;
    }
}
