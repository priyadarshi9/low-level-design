package io.kp;

import java.util.*;

/**
 * Library class.
 */
public class Library {

    private String name;
    private final Catalog catalog = new Catalog();

    // Book copies in the library
    private final Set<String> bookCopies;
    private final Set<Book> books;
    private final Set<String> bookIds;
    private final Map<String, BookCopy> bookCopyMap;

    // Map of users to user objects
    private final Map<String, User> users;


    // Racks in the Library
    private final List<Rack> racks;

    public Library(String name, int totalRacks) {
        this.name = name;
        bookCopies = new HashSet<>();
        racks = new ArrayList<Rack>();
        books = new HashSet<>();
        users = new HashMap<>();
        bookIds = new HashSet<>();
        bookCopyMap = new HashMap<>();
        initializeRacks(totalRacks);
        System.out.println("Created library with " + totalRacks + " racks");
    }

    public Map<String, User> getUsers() {
        return users;
    }

    private void initializeRacks(int totalRacks) {
        for (int i = 0; i < totalRacks; i++) {
            Rack tempRack = new Rack();
            racks.add(tempRack);
        }
    }

    public void addBook(Book book) {
        books.add(book);
        bookIds.add(book.getBookId());
    }

    public void createUser(String userId) {
        User newUser = new User(userId);
        users.put(userId, newUser);
    }

    public void addBookCopy(BookCopy copy) throws Exception {
        if (Rack.NUMBER_OF_OCCUPIED_RACKS < racks.size()) {
            // Add the bookCopy to the first empty rack
            for (int i = 0; i < racks.size(); i++) {
                if (racks.get(i).getRackStatus().equals(RackStatus.AVAILABLE)) {
                    Rack rack = racks.get(i);
                    rack.setBookCopy(copy);
                    rack.setRackStatus(RackStatus.OCCUPIED);
                    Rack.NUMBER_OF_OCCUPIED_RACKS++;

                    copy.setRack(rack);
                    bookCopies.add(copy.getBookCopyId());
                    bookCopyMap.put(copy.getBookCopyId(), copy);
                    catalog.updateCatalog(copy);
                    System.out.println("Added Book to rack: " + rack.getRackNumber());
                    break;
                }
            }
        } else {
            System.out.println("Rack Not available");
            throw new Exception();
        }
    }

    public void removeBookCopy(String copyId) {
        // Check if bookCopy exists
        if (bookCopies.contains(copyId)) {
            // Remove the bookCopy from everywhere
            bookCopies.remove(copyId);
            for (int i = 0; i < racks.size(); i++) {
                if (racks.get(i).getBookCopy().getBookCopyId().equals(copyId)) {
                    // Update the rack
                    Rack rack = racks.get(i);
                    rack.setBookCopy(null);
                    rack.setRackStatus(RackStatus.AVAILABLE);
                    Rack.NUMBER_OF_OCCUPIED_RACKS--;
                    System.out.println("Removed book copy: " + copyId + " from rack: " + rack.getRackNumber());
                    break;
                }
            }
        } else {
            System.out.println("Invalid Book Copy ID");
        }

    }
    private void prettyPrint(BookCopy bookCopy) {
        System.out.println("Borrowed book from rack: " + bookCopy.getRack().getRackNumber());
    }

    public void borrowBook(String bookId, User user, String dueDate) {
        if (!bookIds.contains(bookId)) {
            System.out.println("Invalid Book ID");
        } else if (user.getNumOfBooksIssued() >= Constants.MAXIMUM_BOOKS_ISSUED) {
            System.out.println("Overlimit");
        } else {
            // Try and look for a book copy if available
            List<BookCopy> bookCopies = catalog.searchByBookId(bookId);
            for (int i = 0; i < bookCopies.size(); i++) {
                if (bookCopies.get(i).getStatus().equals(BookStatus.AVAILABLE)) {
                    // Found a book copy, mark it borrow
                    BookCopy bookCopy = bookCopies.get(i);
                    bookCopy.setStatus(BookStatus.BORROWED);
                    bookCopy.setBorrowedBy(user);
                    bookCopy.setDueDate(dueDate);
                    user.incrementBooksIssued();
                    prettyPrint(bookCopy);
                    Rack rack = bookCopy.getRack();
                    rack.setRackStatus(RackStatus.AVAILABLE);
                    bookCopy.setRack(null);
                    break;
                }
                if (i == bookCopies.size() - 1) {
                    System.out.println("Not Available");
                }
            }

        }
    }

    public void search(String attribute, String value) {
        List<BookCopy> results = null;
        switch(attribute) {
            case "book_id":
                results = catalog.searchByBookId(value);
                break;
            case "author_id":
                results = catalog.searchByAuthor(value);
                break;
            case "publisher":
                results = catalog.searchByPublisher(value);
                break;
            default:
                System.out.println("Invalid search attribute");
        }
        prettyPrintBookCopies(results);
    }

    private void prettyPrintBookCopies(List<BookCopy> copies) {
        if (copies == null) {
            return;
        }

        for (int i = 0; i < copies.size(); i++) {
            System.out.println(copies.get(i).toString());
        }
    }

    private void prettyPrintBookCopiesBorrowed(List<BookCopy> copies) {
        if (copies == null) {
            return;
        }

        for (int i = 0; i < copies.size(); i++) {
            System.out.println("Book Copy: " + copies.get(i).getBookCopyId() + " " +
                    copies.get(i).getDueDate());
        }
    }

    public void printBorrowed(String userId) {
        User user = users.get(userId);
        if (user == null) {
            return;
        } else if (user.getNumOfBooksIssued() == 0) {
            return;
        } else {
            // Give a brute force solution for now
            List<BookCopy> copies = new ArrayList<>();
            for (Rack rack : racks) {
                if (rack.getRackStatus().equals(RackStatus.OCCUPIED)) {
                    BookCopy copy = rack.getBookCopy();
                    if (copy.getStatus().equals(BookStatus.BORROWED)) {
                        copies.add(copy);
                    }
                }
            }
            prettyPrintBookCopiesBorrowed(copies);
        }
    }

    public void returnBookCopy(String copyid) {
        if (!bookCopies.contains(copyid)) {
            System.out.println("Invalid Book Copy ID");
        } else {
            BookCopy copy = bookCopyMap.get(copyid);
            // Add the bookCopy to the first empty rack
            for (int i = 0; i < racks.size(); i++) {
                if (racks.get(i).getRackStatus().equals(RackStatus.AVAILABLE)) {
                    Rack rack = racks.get(i);
                    rack.setBookCopy(copy);
                    rack.setRackStatus(RackStatus.OCCUPIED);
                    Rack.NUMBER_OF_OCCUPIED_RACKS++;

                    copy.setRack(rack);
                    bookCopies.add(copy.getBookCopyId());
                    copy.getBorrowedBy().decrementBooksIssued();
                    copy.setStatus(BookStatus.AVAILABLE);
                    System.out.println("Returned book copy : " + copy.getBookCopyId() + " and added to rack: " + rack.getRackNumber());
                    break;
                }
            }

        }
    }
}
