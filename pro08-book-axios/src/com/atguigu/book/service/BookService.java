package com.atguigu.book.service;

import com.atguigu.book.javabean.Book;

import java.util.List;

public interface BookService {

    public Book getBook(Integer bookId);

    public List<Book> getBookList(Integer minPrice, Integer maxPrice, Integer pageNo);
}
