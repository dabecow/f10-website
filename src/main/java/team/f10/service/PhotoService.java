package team.f10.service;

import org.springframework.web.multipart.MultipartFile;
import team.f10.model.User;

public interface PhotoService {
    void addPhoto(MultipartFile file, User user);
    MultipartFile getPhoto(Long id);
}
