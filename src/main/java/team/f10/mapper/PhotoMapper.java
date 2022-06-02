package team.f10.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import team.f10.dto.PhotoDto;
import team.f10.model.Photo;

import java.util.Base64;

@Slf4j
@Component
public class PhotoMapper {
    public PhotoDto toDto(Photo photo) {
            return new PhotoDto(photo.getFileType(), Base64.getEncoder().encodeToString(photo.getImageData()));
    }
}
