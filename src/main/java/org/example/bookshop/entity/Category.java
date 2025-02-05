package org.example.bookshop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category extends IdClass{

    private String categoryName;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        book.setCategory(this);
        books.add(book);
    }

}
