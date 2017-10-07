package com.example.hp.bookshop;

/**
 * Created by HP on 7/29/2017.
 */

public class Books {

    public String bookID;
    public String bookAuthor;
    public String bookRating;
    public Double ratingTwo;

    Books(String id,String author,String rating,Double ratingOne)
    {
        bookRating=rating;
        bookAuthor=author;
        bookID=id;
        ratingTwo=ratingOne;

    }

    public String getBookID()
    {
        return bookID;
    }
    public String getBookAuthor()
    {
        return bookAuthor;
    }
    public String getBookRating()
    {
        return bookRating;
    }
    public Double getRatingTwo()
    {
        return ratingTwo;
    }



}
