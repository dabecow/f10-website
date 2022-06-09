package team.f10.dto;

import lombok.Value;

@Value
public class ArticleSmallDto {
    Long id;
    String title;
    PhotoDto mainImage;
}
