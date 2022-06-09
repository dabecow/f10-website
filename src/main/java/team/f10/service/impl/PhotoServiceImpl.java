package team.f10.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.f10.model.Article;
import team.f10.model.Photo;
import team.f10.model.User;
import team.f10.repository.ArticleRepository;
import team.f10.repository.PhotoRepository;
import team.f10.repository.UserRepository;
import team.f10.service.PhotoService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Override
    public void setUserProfileImage(MultipartFile file, User user) {
        Photo photo = addPhoto(file, user);
        user.setProfileImage(photo);

        userRepository.save(user);
    }

    @Override
    public void setArticleMainImage(MultipartFile file, Article article, User user) {
        Photo photo = addPhoto(file, user);
        article.setMainImage(photo);
        articleRepository.save(article);
    }

    @Override
    public MultipartFile getPhoto(Long id) {
        return null;
    }

    @Override
    public Photo addPhoto(MultipartFile file, User addedBy) {
        if (file.isEmpty())
            return null;

        try {

            Photo photo = Photo.builder()
                .addedBy(addedBy)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .name(file.getOriginalFilename())
                .imageData(file.getBytes())
                .fileType(file.getContentType())
                .build();

            return photoRepository.save(photo);
        } catch (Exception e) {
            String msg = "Couldn't process the photo: " + e.getMessage();
            log.error(msg);
            throw new RuntimeException(msg);
        }
    }
}
