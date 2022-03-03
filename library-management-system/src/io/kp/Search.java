package io.kp;

import java.util.List;

public interface Search {

    List<BookCopy> searchByAuthor(String author);
    List<BookCopy> searchByBookId(String title);
    List<BookCopy> searchByPublisher(String publisher);

}
