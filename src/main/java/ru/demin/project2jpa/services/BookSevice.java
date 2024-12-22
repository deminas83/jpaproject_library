package ru.demin.project2jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demin.project2jpa.models.Book;
import ru.demin.project2jpa.repo.BookRepo;
import ru.demin.project2jpa.repo.PersonRepo;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookSevice {

   // private final PersonRepo personRepo;
    private BookRepo bookRepo;

    @Autowired
    public BookSevice(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    public Book getBookById(int id) {
        return bookRepo.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Book book){
        bookRepo.delete(book);
    }

    @Transactional
    public void deleteById(int id){
        bookRepo.deleteById(id);
    }

    @Transactional
    public void save(Book book){
        bookRepo.save(book);
    }

    @Transactional
    public void update(int id, Book book){
        book.setBook_id(id);
        bookRepo.save(book);
    }
}
