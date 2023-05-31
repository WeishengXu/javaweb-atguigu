package com.atguigu.book.javabean;

public class Book {
    private Integer bookId;
    private String bookImg;
    private String bookName;
    private Double bookPrice;
    private String bookAuthor;
    private Integer bookSaleCount;
    private Integer bookCount;
    private Integer bookStatus;

    public Book() {
    }

    public Book(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getBookSaleCount() {
        return bookSaleCount;
    }

    public void setBookSaleCount(Integer bookSaleCount) {
        this.bookSaleCount = bookSaleCount;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookImg='" + bookImg + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookSaleCount=" + bookSaleCount +
                ", bookCount=" + bookCount +
                ", bookStatus=" + bookStatus +
                '}';
    }
}
