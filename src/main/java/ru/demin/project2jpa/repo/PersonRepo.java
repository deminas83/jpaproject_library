package ru.demin.project2jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demin.project2jpa.models.Person;

public interface PersonRepo extends JpaRepository<Person, Integer> {
}
