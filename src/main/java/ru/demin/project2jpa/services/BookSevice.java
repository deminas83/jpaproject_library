package ru.demin.project2jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demin.project2jpa.models.Book;
import ru.demin.project2jpa.models.Person;
import ru.demin.project2jpa.repo.BookRepo;
import ru.demin.project2jpa.repo.PersonRepo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
public class BookSevice {

   // private final PersonRepo personRepo;
    private final BookRepo bookRepo;

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
        book.setDateBooking(new Date());
    }

    @Transactional
    public void deleteOwner(int book_id){
        Book book = bookRepo.findById(book_id).orElse(null);
        assert book != null;
        book.setOwner(null);
        book.setDateBooking(null);
    }

    public List<Book> findBooksByTitle(String title){
        return bookRepo.findBooksByTitleStartingWith(title);
    }

    public Page<Book> findPaginated(int currentPage, int pageSize, List<Book> books, String sortDirection) {
        int startItem = (currentPage - 1) * pageSize; // Исправлено вычисление startItem
        List<Book> list;

        int toIndex = Math.min(startItem + pageSize, books.size());
        list = books.subList(startItem, toIndex);

        Comparator<Book> yearComparator = Comparator.comparingInt(Book::getYearPublic);
        if (sortDirection != null) {
            if (sortDirection.equals("asc")) {
                list.sort(yearComparator);
            } else {
                list.sort(yearComparator.reversed());
            }
        }
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), books.size());
    }

    public List<Integer> getCountPage(Page<Book> page) {
        int totalPages = page.getTotalPages();
        List<Integer> pageNumbers = Collections.emptyList();

        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return pageNumbers;
    }


}
