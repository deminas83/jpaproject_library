package ru.demin.project2jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demin.project2jpa.models.Book;
import ru.demin.project2jpa.models.Person;
import ru.demin.project2jpa.repo.BookRepo;
import ru.demin.project2jpa.repo.PersonRepo;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private PersonRepo personRepo;
    private BookRepo bookRepo;

@Autowired
    public PeopleService(PersonRepo personRepo, BookRepo bookRepo) {
        this.personRepo = personRepo;
        this.bookRepo = bookRepo;
    }

    @Transactional
    public void save(Person person){
        personRepo.save(person);
    }

    @Transactional
    public void deleteById(int id){
        personRepo.deleteById(id);
    }

    @Transactional
    public void delete(Person person){
        personRepo.delete(person);
    }

    @Transactional
    public void update(int id, Person person){
        person.setPerson_id(id);
        personRepo.save(person);
    }

    public List<Person> showAll(){
        return personRepo.findAll();
    }

    public Person showById(int id){
        return personRepo.findById(id).orElse(null);
    }

    public List<Book> findBookByOwner(Person owner){
        return bookRepo.findBookByOwner(owner);
    }

    public boolean checkExpiredTime(Date date) {
        long differenceInDays = ChronoUnit.DAYS.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
        return differenceInDays > 9;
    }

    public List<Book> updateMarkExpiredTime(List<Book> books){
        for (Book book : books) {
            book.setMarkExpiredTime(checkExpiredTime(book.getDate_booking()));
            System.out.println(book);
        }
        System.out.println(books.size());
    return books;
    }
}
