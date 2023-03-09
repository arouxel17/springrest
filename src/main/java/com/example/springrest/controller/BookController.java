package com.example.springrest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrest.entity.Book;
import com.example.springrest.repository.BookRepository;

@RestController
public class BookController {
    @Autowired
    private BookRepository respository;

    @GetMapping("/books")
    public List<Book> index() {
        return respository.findAll();
    }

    @GetMapping("/books/search")
    public List<Book> search(@RequestParam String nomDeRecherche) {
        String searchTerm = nomDeRecherche;
        return respository.findByTitleContainingOrAuthorContaining(searchTerm, searchTerm);
    }

    @GetMapping("/books/show/{id}")
    public Book show(@PathVariable Long id) {
        return respository.findById(id).get();
    }

    @PostMapping("/books/save")
    public Book create(@RequestBody Book Book) {
        return respository.save(Book);
    }

    @PutMapping("/books/update/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book Book) {
        Book BookToUpdate = respository.findById(id).get();
        BookToUpdate.setTitle(Book.getTitle());
        BookToUpdate.setAuthor(Book.getAuthor());
        BookToUpdate.setDescription(Book.getDescription());
        return respository.save(BookToUpdate);
    }

    @DeleteMapping("books/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        respository.deleteById(id);
        return true;
    }
}