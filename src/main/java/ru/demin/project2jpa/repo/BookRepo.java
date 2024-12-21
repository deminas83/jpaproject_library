package ru.demin.project2jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demin.project2jpa.models.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
}
