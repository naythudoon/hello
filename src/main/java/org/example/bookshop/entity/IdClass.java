package org.example.bookshop.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class IdClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
