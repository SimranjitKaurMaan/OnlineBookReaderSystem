package com.simran;

import com.simran.exceptions.*;
import com.simran.models.Book;
import com.simran.models.User;
import javafx.util.Pair;

public class ReaderService
{
    private final BookService bookService;
    private final UserService userService;
    private Book activeBook;
    private User activeUser;

    public ReaderService(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    public Pair<Book,User> readBook(String bookId, String userId)
    {
        Book book = this.bookService.getBook(bookId);
        User user = this.userService.getUser(userId);
        if(activeBook!=null)
        {
            throw new BookNotAvailableToReadException();
        }
        activeBook = book;
        activeUser = user;
        return new Pair<>(activeBook,activeUser);
    }


    public boolean closeBook(String bookId,String userId)
    {
        Book book = this.bookService.getBook(bookId);
        User user = this.userService.getUser(userId);

        if(activeBook == book && activeUser == user)
        {
            activeBook=null;
            activeUser=null;
        }else if(activeBook!=book)
        {
            throw new NotActiveBookException();
        }else
        {
            throw new NotActiveUserException();
        }
        return true;
    }

}
