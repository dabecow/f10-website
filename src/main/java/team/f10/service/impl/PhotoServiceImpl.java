package team.f10.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.f10.model.Photo;
import team.f10.model.User;
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

    @Override
    public void addPhoto(MultipartFile file, User user) {
        if (file.isEmpty())
            return;
        try {
            Photo photo = Photo.builder()
                    .addedBy(user)
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .name(file.getOriginalFilename())
                    .imageData(file.getBytes())
                    .fileType(file.getContentType())
                    .build();
            user.setProfileImage(photo);

            photoRepository.save(photo);
            userRepository.save(user);

        } catch (Exception e) {
            String msg = "Couldn't process the photo: " + e.getMessage();
            log.error(msg);
            throw new RuntimeException(msg);
        }
    }

    @Override
    public MultipartFile getPhoto(Long id) {
        return null;
    }
}
