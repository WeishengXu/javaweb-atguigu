package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.BookDao;
import com.atguigu.book.javabean.Book;
import com.atguigu.myssm.daoUtils.BaseDao;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {

    private String sql;

    @Override
    public List<Book> getBookList(Integer minPrice, Integer maxPrice, Integer pageNo) {
        sql = "select * from t_book where bookPrice >= ? and bookPrice <= ? and bookStatus = 0 limit ?, 10";
        List<Book> bookList = null;
        try {
            bookList = super.executeQuery(Book.class, sql, minPrice, maxPrice, (pageNo - 1) * 10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    @Override
    public Book getBook(Integer bookId) {
        sql = "select * from t_book where bookId = ? and bookStatus = 0";
        List<Book> bookList = null;
        try {
            bookList = super.executeQuery(Book.class, sql, bookId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (bookList != null && bookList.size() > 0) {
            return bookList.get(0);
        } else {
            return null;
        }
    }
}
