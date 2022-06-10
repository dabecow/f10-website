package team.f10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.f10.model.Role;
import team.f10.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsUserByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    List<User> findByRoleIsNullOrRoleIn(List<Role> roleAdmin);
}
