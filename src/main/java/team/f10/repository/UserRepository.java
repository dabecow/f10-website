package team.f10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.f10.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsUserByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM User u where not exists (select 1 from Employee e where e.user = u)")
    List<User> findAllNonEmployees();
}
