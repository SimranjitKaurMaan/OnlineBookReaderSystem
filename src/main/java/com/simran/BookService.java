package com.simran;

import com.simran.exceptions.*;
import com.simran.models.Book;
import com.simran.models.MembershipStatus;
import com.simran.models.User;

import java.util.Map;

public class BookService
{
    private Map<String, Book> bookMap;

    public BookService(Map<String, Book> bookMap) {
        this.bookMap = bookMap;
    }

    public Book getBook(String bookId)
    {
        if(!this.bookMap.containsKey(bookId))
        {
            throw new BookNotFoundException();
        }

        return this.bookMap.get(bookId);
    }

}
