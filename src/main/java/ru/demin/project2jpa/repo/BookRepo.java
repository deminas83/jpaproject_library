package ru.demin.project2jpa.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demin.project2jpa.models.Book;
import ru.demin.project2jpa.models.Person;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    public List<Book> findBookByOwner(Person owner);

    List<Book> findBooksByTitleStartingWith(String title);

    Page<Book> findAllByOrderByYearPublicAsc(Pageable pageable);

    Page<Book> findAllByOrderByYearPublicDesc(Pageable pageable);
}
