package com.atguigu.book.service.impl;

import com.atguigu.book.dao.BookDao;
import com.atguigu.book.javabean.Book;
import com.atguigu.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    @Override
    public Book getBook(Integer bookId) {
        return bookDao.getBook(bookId);
    }

    @Override
    public List<Book> getBookList(Integer minPrice, Integer maxPrice, Integer pageNo) {
        return bookDao.getBookList(minPrice, maxPrice, pageNo);
    }
}
