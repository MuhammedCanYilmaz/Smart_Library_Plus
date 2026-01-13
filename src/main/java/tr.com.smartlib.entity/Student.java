package tr.com.smartlib.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String department;

    // Bir öğrencinin çok ödünç kaydı olabilir
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<Loan> loans;

    public Student() {}
    public Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public Long getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() { return id + " | " + name + " (" + department + ")"; }
}