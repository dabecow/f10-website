package team.f10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.f10.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e " +
            "join e.user u " +
            "join e.occupation o " +
            "left join u.profileImage i "
    )
    List<Employee> findAllFetchPhotos();
}
