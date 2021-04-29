import com.simran.BookService;
import com.simran.ReaderService;
import com.simran.UserService;
import com.simran.models.Book;
import com.simran.models.MembershipStatus;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BookServiceTest
{
    @Test
    public void defaultTest()
    {
        UserService userService = new UserService();
        String userId1 = userService.addUser("U1");
        String userId2 =userService.addUser("U2");
        Map<String, Book> bookMap = new HashMap<>();
        Book book1 = new Book("B1");
        bookMap.put(book1.getId(),book1);
        Book book2 = new Book("B2");
        bookMap.put(book2.getId(),book2);
        Book book3 = new Book("B3");
        bookMap.put(book3.getId(),book3);
        Book book4 = new Book("B4");
        bookMap.put(book4.getId(),book4);
        BookService bookService = new BookService(bookMap);
        ReaderService readerService = new ReaderService(bookService,userService);
        System.out.println(readerService.readBook(book1.getId(),userId1));
        System.out.println(readerService.closeBook(book1.getId(),userId1));
        System.out.println(readerService.readBook(book2.getId(),userId2));
        System.out.println(readerService.closeBook(book2.getId(),userId2));
        userService.getUser(userId1).setStatus(MembershipStatus.expired);
        userService.extendUser(userId1);
        System.out.println(userService.getUser(userId1));
        System.out.println(readerService.readBook(book1.getId(),userId1));
    }
}
