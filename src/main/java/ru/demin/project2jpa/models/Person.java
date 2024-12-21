package ru.demin.project2jpa.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int person_id;

    @NotEmpty(message = "ФИО не может быть пустым!")
    @Size(min = 1, max = 100, message = "ФИО должно быть от 1 до 100 символов!")
    @Column(name = "fio")
    private String fio;

    @NotEmpty(message = "Дата рождения не может быть пустой!")
    @Column(name = "year_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd.MM.yyyy")
    private Date year_of_birth;

    @OneToMany(mappedBy = "owner")
    List<Book> bookList;

    public Person() {
    }

    public @NotEmpty(message = "Дата рождения не может быть пустой!") Date getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(@NotEmpty(message = "Дата рождения не может быть пустой!") Date year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public @NotEmpty(message = "ФИО не может быть пустым!") @Size(min = 1, max = 100, message = "ФИО должно быть от 1 до 100 символов!") String getFio() {
        return fio;
    }

    public void setFio(@NotEmpty(message = "ФИО не может быть пустым!") @Size(min = 1, max = 100, message = "ФИО должно быть от 1 до 100 символов!") String fio) {
        this.fio = fio;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}

