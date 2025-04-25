package com.sashmitha.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

    @Id
    private Long id;
    private String title;
    private String author;
    private int publishedYear;
    private int availableCopies;

}
