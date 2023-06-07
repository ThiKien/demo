package com.example.demo.controller;

import com.example.demo.services.BookService;
import com.example.demo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Book;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/books")

public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book",new Book());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model){
        if(bindingResult !=null && bindingResult.hasErrors()){
            List<String> errors=bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList();
            model.addAttribute("errors",errors);
            return "book/add";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id")Long id){
        Book book = bookService.getBookById(id);
        bookService.deleteBook(id);
        return "redirect:/books";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm (@PathVariable("id") Long id, Model model)
    {
        List<Book> books = bookService.getAllBooks();
        Optional<Book> editBook= books.stream()
                .filter(book -> book.getId().equals(id)).findFirst();
        if(editBook.isPresent())
        {
            model.addAttribute("book",editBook.get());
            model.addAttribute("categories",categoryService.getAllCategories());
            return "book/edit";
        }
        else
        {
            return"not-found";
        }
    }
    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book updatedBook){
        List<Book> books = bookService.getAllBooks();
        books.stream()
                .filter(book ->book.getId()==updatedBook.getId())
                .findFirst()
                .ifPresent(book ->books.set(books.indexOf(book),updatedBook));
        bookService.updateBook(updatedBook);
        return  "redirect:/books";
    }
}
