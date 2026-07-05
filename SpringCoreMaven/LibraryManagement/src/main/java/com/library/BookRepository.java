package com.library;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private final List<String> books = new ArrayList<>();

    public BookRepository() {
        books.add("Introduction to Algorithms");
        books.add("Clean Code");
        books.add("Design Patterns");
    }

    public List<String> findAll() {
        return new ArrayList<>(books);
    }

    public void save(String title) {
        books.add(title);
    }
}
