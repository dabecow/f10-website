package team.f10.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import team.f10.dto.ArticleDto;
import team.f10.dto.ArticleSmallDto;
import team.f10.dto.EditArticleDto;
import team.f10.dto.PhotoDto;
import team.f10.dto.UserDto;
import team.f10.model.Article;
import team.f10.model.Tag;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ArticleMapper {

    private final PhotoMapper photoMapper;
    private final UserMapper userMapper;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    public ArticleDto toDto(Article model) {
        PhotoDto photoDto = photoMapper.toDto(model.getMainImage());
        UserDto creator = userMapper.toDto(model.getAuthor());
        String created = dateFormat.format(model.getCreationDate());

        UserDto editor = null;
        String edited = null;

        if (Objects.nonNull(model.getLastEditedDate()) && Objects.nonNull(model.getLastEditor())) {
            editor = userMapper.toDto(model.getLastEditor());
            edited = dateFormat.format(model.getLastEditedDate());
        }

        Set<String> tags = model.getTags().stream().map(Tag::getName).collect(Collectors.toSet());

        return new ArticleDto(model.getId(), model.getTitle(), model.getContent(), created, photoDto, tags, creator, edited, editor);
    }

    public ArticleSmallDto toSmallDto(Article model) {
        PhotoDto photoDto = photoMapper.toDto(model.getMainImage());
        return new ArticleSmallDto(model.getId(), model.getTitle(), photoDto);
    }

    public List<ArticleDto> toDtoList(List<Article> modelList) {
        return modelList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public EditArticleDto toEditDto(Article article) {
        return EditArticleDto.builder()
                .content(article.getContent())
                .title(article.getTitle())
                .build();
    }
}
