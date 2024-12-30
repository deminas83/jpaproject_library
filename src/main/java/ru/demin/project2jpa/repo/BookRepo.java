package ru.demin.project2jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demin.project2jpa.models.Book;
import ru.demin.project2jpa.models.Person;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    List<Book> findBookByOwner(Person owner);

    List<Book> findBooksByTitleStartingWith(String title);

}
