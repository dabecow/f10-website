package team.f10.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.f10.dto.ArticleSmallDto;
import team.f10.dto.CreateArticleDto;
import team.f10.dto.ArticleDto;
import team.f10.dto.EditArticleDto;
import team.f10.exception.NoSuchArticleException;
import team.f10.mapper.ArticleMapper;
import team.f10.model.Article;
import team.f10.model.Photo;
import team.f10.model.Tag;
import team.f10.model.User;
import team.f10.repository.ArticleRepository;
import team.f10.service.ArticleService;
import team.f10.service.PhotoService;
import team.f10.service.TagService;
import team.f10.service.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final PhotoService photoService;
    private final TagService tagService;
    private final UserService userService;
    private final ArticleMapper articleMapper;

    @Override
    public void addArticle(CreateArticleDto dto, MultipartFile image, Long authorUserId) {

        Set<Tag> tags = null;
        if (Objects.nonNull(dto.getTagsIds()))
            tags = tagService.getTags(dto.getTagsIds());

        User user = userService.getUserById(authorUserId);
        Photo photo = photoService.addPhoto(image, user);
        Article article = Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .mainImage(photo)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .author(user)
                .build();

        if (Objects.nonNull(tags))
            article.setTags(tags);

        articleRepository.save(article);
    }

    @Override
    public ArticleDto getArticle(Long id) {
        return articleMapper.toDto(getArticleById(id));
    }

    @Override
    public EditArticleDto getArticleForEdit(Long id) {
        return articleMapper.toEditDto(getArticleById(id));
    }

    @Override
    public Page<ArticleSmallDto> getArticles(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles.map(articleMapper::toSmallDto);
    }

    @Override
    public void removeArticle(Long articleId) {
        articleRepository.delete(getArticleById(articleId));
    }

    @Override
    public void editArticle(Long articleId, MultipartFile image, EditArticleDto dto, Long userId) {
        if (!isArticleEdited(dto, image))
            return;

        Article article = getArticleById(articleId);
        User user = userService.getUserById(userId);

        article.setLastEditedDate(Timestamp.valueOf(LocalDateTime.now()));
        article.setLastEditor(user);

        if (!image.isEmpty()) {
            Photo photo = photoService.addPhoto(image, user);
            article.setMainImage(photo);
        }

        if (Objects.nonNull(dto.getTitle()))
            article.setTitle(dto.getTitle());

        if (Objects.nonNull(dto.getContent()))
            article.setContent(dto.getContent());

        articleRepository.save(article);
    }

    private Article getArticleById(Long articleId) {
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isEmpty())
            throw new NoSuchArticleException(articleId);

        return article.get();
    }

    private boolean isArticleEdited(EditArticleDto dto, MultipartFile image) {
        return !image.isEmpty() || Objects.nonNull(dto.getContent()) || Objects.nonNull(dto.getTitle());
    }
}
