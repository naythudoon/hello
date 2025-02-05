package org.example.bookshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Book extends IdClass{
    private String title;
    private double price;
    private int stock;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearPublished;

    public Book(String title, double price, int stock, LocalDate yearPublished) {
        this.title = title;
        this.price = price;
        this.stock = stock;
        this.yearPublished = yearPublished;
    }
    @ManyToOne
    private Category category;
    @ManyToOne
    private Author author;
}
