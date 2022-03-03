package io.kp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog implements Search {

    private final String name = "KP Central Catalog";

    private Map<String, List<BookCopy>> bookIdToBooks = new HashMap<>();
    private Map<String, List<BookCopy>> authorToBooks = new HashMap<>();
    private Map<String, List<BookCopy>> publisherToBooks = new HashMap<>();

    @Override
    public List<BookCopy> searchByAuthor(String author) {
        return authorToBooks.get(author);
    }

    @Override
    public List<BookCopy> searchByBookId(String id) {
        return bookIdToBooks.get(id);
    }

    @Override
    public List<BookCopy> searchByPublisher(String publisher) {
        return publisherToBooks.get(publisher);
    }

    public void updateCatalog(BookCopy bookCopy) {
        String bookId = bookCopy.getBookId();
        String[] authors = bookCopy.getAuthors();
        String[] publishers = bookCopy.getPublishers();

        if (bookIdToBooks.get(bookId) == null) {
            bookIdToBooks.put(bookId, new ArrayList<>());
        }
        bookIdToBooks.get(bookId).add(bookCopy);

        for (String author : authors) {
            if (authorToBooks.get(author) == null) {
                authorToBooks.put(author, new ArrayList<>());
            }
            authorToBooks.get(author).add(bookCopy);
        }

        for (String publisher : publishers) {
            if (publisherToBooks.get(publisher) == null) {
                authorToBooks.put(publisher, new ArrayList<>());
            }
            authorToBooks.get(publisher).add(bookCopy);
        }

    }
}
