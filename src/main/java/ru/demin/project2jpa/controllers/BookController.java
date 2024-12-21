package ru.demin.project2jpa.controllers;

import jakarta.validation.Valid;
import jdk.internal.icu.text.NormalizerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.demin.project2jpa.models.Book;
import ru.demin.project2jpa.services.BookSevice;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookSevice bookSevice;

    @Autowired
    public BookController(BookSevice bookSevice) {
        this.bookSevice = bookSevice;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookSevice.getBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookSevice.getBookById(id));
        return "books/show";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id, Model model){
        bookSevice.deleteById(id);
        return "redirect:/book";
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
            return "redirect:/book";}
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
        return "redirect:/book";}
    }
}
