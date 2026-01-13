package tr.com.smartlib.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    @OneToOne // Hoca OneToOne istedi
    @JoinColumn(name = "book_id", unique = true)
    private Book book;

    @ManyToOne // Hoca Student -> Loan için OneToMany istedi, burası ManyToOne olur
    @JoinColumn(name = "student_id")
    private Student student;

    public Loan() {}
    public Loan(Student student, Book book) {
        this.student = student;
        this.book = book;
        this.borrowDate = LocalDate.now();
    }

    public Book getBook() { return book; }
    public Student getStudent() { return student; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public LocalDate getReturnDate() { return returnDate; }

    @Override
    public String toString() {
        return student.getName() + " aldı -> " + book.getTitle() +
                " (Tarih: " + borrowDate + ", İade: " + (returnDate == null ? "Teslim Edilmedi" : returnDate) + ")";
    }
}