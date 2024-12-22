package ru.demin.project2jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demin.project2jpa.models.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
}
