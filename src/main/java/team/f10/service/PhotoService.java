package team.f10.service;

import org.springframework.web.multipart.MultipartFile;
import team.f10.model.Article;
import team.f10.model.Photo;
import team.f10.model.User;

public interface PhotoService {
    void setUserProfileImage(MultipartFile file, User user);
    void setArticleMainImage(MultipartFile file, Article article, User user);
    MultipartFile getPhoto(Long id);

    Photo addPhoto(MultipartFile file, User addedBy);
}
