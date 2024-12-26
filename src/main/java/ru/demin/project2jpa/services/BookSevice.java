package ru.demin.project2jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demin.project2jpa.models.Book;
import ru.demin.project2jpa.models.Person;
import ru.demin.project2jpa.repo.BookRepo;
import ru.demin.project2jpa.repo.PersonRepo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class BookSevice {

   // private final PersonRepo personRepo;
    private BookRepo bookRepo;

    @Autowired
    public BookSevice(BookRepo bookRepo, PersonRepo personRepo) {
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

    @Transactional
    public void updateOwner(Person person, int book_id){
        Book book = bookRepo.findById(book_id).orElse(null);
        assert book != null;
        book.setOwner(person);
        book.setDate_booking(new Date());
    }

    @Transactional
    public void deleteOwner(int book_id){
        Book book = bookRepo.findById(book_id).orElse(null);
        assert book != null;
        book.setOwner(null);
        book.setDate_booking(null);
    }

    public List<Book> findBooksByTitle(String title){
        return bookRepo.findBooksByTitleStartingWith(title);
    }

    public List<Book> getSortedBooks(String sortDirection) {
        List<Book> books = bookRepo.findAll();
        if (sortDirection.equals("asc")) {
            books.sort(Comparator.comparing(Book::getYear_public));
        } else if (sortDirection.equals("desc")) {
            books.sort(Comparator.comparing(Book::getYear_public).reversed());
        }
        return books;
    }


}
