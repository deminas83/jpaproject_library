package ru.demin.project2jpa.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int book_id;

//    @Column(name = "pers_id")
//    private int pers_id;

    @NotEmpty(message = "Название не может быть пустым!")
    @Size(min = 5, max = 100, message = "Название должно быть от 5 до 100 символов!")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Автор не может быть пустым!")
    @Size(min = 5, max = 100, message = "Автор должен быть от 5 до 100 символов!")
    @Column(name = "author")
    private String author;

    @Column(name="year_public")
    private int year_public;

    @Column(name="date_booking")
    private Date date_booking;

    @ManyToOne
    @JoinColumn(name = "pers_id", referencedColumnName = "person_id")
    private Person owner;

    public Book() {
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public @NotEmpty(message = "Название не может быть пустым!") @Size(min = 5, max = 100, message = "Название должно быть от 5 до 100 символов!") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "Название не может быть пустым!") @Size(min = 5, max = 100, message = "Название должно быть от 5 до 100 символов!") String title) {
        this.title = title;
    }

    public @NotEmpty(message = "Автор не может быть пустым!") @Size(min = 5, max = 100, message = "Автор должен быть от 5 до 100 символов!") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotEmpty(message = "Автор не может быть пустым!") @Size(min = 5, max = 100, message = "Автор должен быть от 5 до 100 символов!") String author) {
        this.author = author;
    }

    public int getYear_public() {
        return year_public;
    }

    public void setYear_public(int year_public) {
        this.year_public = year_public;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getDate_booking() {
        return date_booking;
    }

    public void setDate_booking(Date date_booking) {
        this.date_booking = date_booking;
    }

}
