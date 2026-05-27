package alex.pro.employeeapi;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesBySurname(String surname) {
        return employeeRepository.findBySurname(surname);
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findEmployeesByDepartment(department);
    }

    public List<Employee> getScoreLessThan(double score) {
        return employeeRepository.findByScoreLessThan(score);
    }

    public List<Employee> getScoreGreaterThan(double score) {
        return employeeRepository.findByScoreGreaterThan(score);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        existing.setName(employee.getName());
        existing.setSurname(employee.getSurname());
        existing.setRole(employee.getRole());
        existing.setDepartment(employee.getDepartment());
        existing.setScore(employee.getScore());

        return employeeRepository.save(existing);
    }

    public void deleteEmployee (Long id) {
        Employee exists = employeeRepository.findById(id)
                        .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.delete(exists);
    }

}
