package team.f10.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import team.f10.dto.ArticleSmallDto;
import team.f10.dto.CreateArticleDto;
import team.f10.dto.ArticleDto;
import team.f10.dto.EditArticleDto;

public interface ArticleService {
    void addArticle(CreateArticleDto dto, MultipartFile image, Long authorUserId);
    ArticleDto getArticle(Long id);
    EditArticleDto getArticleForEdit(Long id);
    Page<ArticleSmallDto> getArticles(Pageable pageable);
    void removeArticle(Long articleId);
    void editArticle(Long articleId, MultipartFile image, EditArticleDto dto, Long userId);
}
