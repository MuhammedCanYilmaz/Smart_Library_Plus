package tr.com.smartlib.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int year;

    // AVAILABLE veya BORROWED
    private String status;

    public Book() {} // Zorunlu boş constructor

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.status = "AVAILABLE";
    }

    // Getter - Setter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() { return id + " | " + title + " (" + author + ") -> " + status; }
}