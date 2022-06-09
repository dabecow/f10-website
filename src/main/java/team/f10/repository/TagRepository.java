package team.f10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.f10.model.Tag;

import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Set<Tag> findByIdIn(Set<Long> ids);
    boolean existsTagByName(String name);
}
