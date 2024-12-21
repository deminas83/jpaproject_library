package ru.demin.project2jpa.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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

    @OneToMany()

}

