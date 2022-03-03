package io.kp;

import java.util.ArrayList;
import java.util.List;

/**
 * Book class.
 */
public class Book {

    private String bookId;
    private String title;

    private String[] authors;
    private String[] publishers;

    public Book(String id, String title, String[] authors, String[] publishers) {
        this.bookId = id;
        this.title = title;
        this.authors = authors;
        this.publishers = publishers;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String[] getPublishers() {
        return publishers;
    }
}
