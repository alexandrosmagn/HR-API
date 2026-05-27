package alex.pro.employeeapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface EmployeeRepository extends JpaRepository<Employee, Long> {
        List<Employee> findBySurname(String surname);
        List<Employee> findEmployeesByDepartment(String department);
        List<Employee> findByScoreGreaterThan(double scoreIsGreaterThan);
        List<Employee> findByScoreLessThan(double scoreIsLessThan);
}
