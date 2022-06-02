package team.f10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.f10.model.Occupation;

public interface OccupationRepository extends JpaRepository<Occupation, Long> {
}
