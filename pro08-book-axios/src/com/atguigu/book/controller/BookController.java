package com.atguigu.book.controller;

import com.atguigu.book.javabean.Book;
import com.atguigu.book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BookController {
    private BookService bookService;

    public String index(HttpSession session) {
        List<Book> bookList = bookService.getBookList(0, 100, 1);
        session.setAttribute("bookList", bookList);

        return "index";
    }
}
