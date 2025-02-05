package org.example.bookshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Author extends IdClass{

    private String authorName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String citizenship;

    @Enumerated(EnumType.STRING) //enum cannot work with database
    private Gender gender;
    private String genre;

    public Author(String authorName, LocalDate dateOfBirth, String citizenship, Gender gender, String genre) {
        this.authorName = authorName;
        this.dateOfBirth = dateOfBirth;
        this.citizenship = citizenship;
        this.gender = gender;
        this.genre = genre;
    }
    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        book.setAuthor(this);
        books.add(book);
    }


}
