package com.atguigu.book.dao;

import com.atguigu.book.javabean.Book;

import java.util.List;

public interface BookDao {
    List<Book> getBookList(Integer minPrice, Integer maxPrice, Integer pageNo);

    Book getBook(Integer bookId);
}
