package ru.demin.project2jpa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demin.project2jpa.models.Person;
import ru.demin.project2jpa.repo.PersonRepo;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private PersonRepo personRepo;

    @Autowired
    public PeopleService(PersonRepo personRepo) {
        this.personRepo = personRepo;
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

}
