package team.f10.dto;

import lombok.Value;

import java.util.Set;

@Value
public class ArticleDto {
    Long id;
    String title;
    String content;
    String creationDate;
    PhotoDto mainImage;
    Set<String> tags;
    UserDto author;
    String editDate;
    UserDto lastEditor;
}
