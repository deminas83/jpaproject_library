package ru.demin.project2jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.demin.project2jpa.models.Book;
import ru.demin.project2jpa.services.BookSevice;
import ru.demin.project2jpa.services.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookSevice bookSevice;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookSevice bookSevice, PeopleService peopleService) {
        this.bookSevice = bookSevice;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookSevice.getBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model bookModel, Model personModel){
        bookModel.addAttribute("book", bookSevice.getBookById(id));
        personModel.addAttribute("readers", peopleService.showAll());
        return "books/show";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id, Model model){
        bookSevice.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String findBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookSevice.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book, @PathVariable("id") int id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "books/edit";
        else
        {bookSevice.update(id, book);
            return "redirect:/books";}
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "books/new";
        else {bookSevice.save(book);
        return "redirect:/books";}
    }
}
