package alex.pro.employeeapi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Surname cannot be blank")
    private String surname;
    @NotBlank(message = "Role cannot be blank")
    private String role;
    @NotBlank(message = "Department cannot be blank")
    private String department;
    @PositiveOrZero(message = "Score must be zero or higher")
    private double score;

    public Employee () {}
    public Employee(String name, String surname, String role, String department, double score) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.department = department;
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    public double getScore() {
        return score;
    }
}
