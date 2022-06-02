package team.f10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.f10.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
