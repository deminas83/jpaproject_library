package ru.demin.project2jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.demin.project2jpa.models.Book;
import ru.demin.project2jpa.models.Person;
import ru.demin.project2jpa.repo.PersonRepo;
import ru.demin.project2jpa.services.BookSevice;
import ru.demin.project2jpa.services.PeopleService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookSevice bookService;
    private final PeopleService peopleService;
    private final PersonRepo personRepo;

    @Autowired
    public BookController(BookSevice bookSevice, PeopleService peopleService, PersonRepo personRepo) {
        this.bookService = bookSevice;
        this.peopleService = peopleService;
        this.personRepo = personRepo;
    }

    @GetMapping()
    public String index(@RequestParam(value = "sort", required = false) String sortDirection,
                        @RequestParam(defaultValue = "0", required = false) int page,
                        @RequestParam(defaultValue = "10", required = false) int pageSize, Model model) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Book> pagedBooks;

        if (sortDirection == null) {
            pagedBooks = bookService.findAll(pageRequest);
        } else {
            pagedBooks = bookService.getSortedBooks(sortDirection, pageRequest);
        }

        model.addAttribute("books", pagedBooks.getContent());
        model.addAttribute("pagedBooks", pagedBooks);

        return "books/index";
    }


    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model bookModel, Model personModel){
        bookModel.addAttribute("book", bookService.getBookById(id));
        personModel.addAttribute("readers", peopleService.showAll());
        return "books/show";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id, Model model){
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String findBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book, @PathVariable("id") int id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "books/edit";
        else
        {bookService.update(id, book);
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
        else {bookService.save(book);
        return "redirect:/books";}
    }

    @PostMapping("/assign-book")
    public String assignBook(@RequestParam("bookId") int bookId, @RequestParam("selectedPersonId") int selectedPersonId) {
        // Далее можете выполнить необходимые действия с полученными параметрами
        Person person = personRepo.findById(selectedPersonId).orElse(null);
        bookService.updateOwner(person, bookId);
        return "redirect:/books"; // Возвращаем redirection на другую страницу после обработки
    }

    @PostMapping("/free-book")
    public String freeBook(@RequestParam("bookId") int bookId) {
        bookService.deleteOwner(bookId);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "bookTitle", required = false) String title, Model model){
        if (title != null && !title.isEmpty()) {
            model.addAttribute("searchBook", bookService.findBooksByTitle(title));
        } else {
            model.addAttribute("searchBook", new ArrayList<Book>());
        }
        return "books/search";
    }

}
