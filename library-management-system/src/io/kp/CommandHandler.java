package io.kp;

import java.util.Map;

public class CommandHandler {

    private Library library;

    public void createLibrary(String command) {
        String[] args = command.split(" ");

        int numOfRacks = Integer.parseInt(args[1]);

        library = new Library("KP Central Library", numOfRacks);
    }

    public void addBook(String command) {
        String[] args = command.split(" ");
        String bookId = args[1];
        String title = args[2];
        String[] authors = args[3].split(",");
        String[] publishers = args[4].split(",");
        String[] bookCopyIds = args[5].split(",");


        // Add book to library
        Book book = new Book(bookId, title, authors, publishers);
        library.addBook(book);

        // Try adding the copies
        for (int i = 0; i < bookCopyIds.length; i++) {
            // Create a book copy
            BookCopy bookCopy = new BookCopy(bookId, bookCopyIds[i], title, authors, publishers);

            try {
                library.addBookCopy(bookCopy);
            } catch (Exception e) {
               break;
            }
        }
        System.out.println("\n");

    }

    public void removeBookCopy(String command) {
        String[] args = command.split(" ");
        String bookCopyId = args[1];

        library.removeBookCopy(bookCopyId);
    }

    public void borrowBook(String command) {
        String[] args = command.split(" ");
        String bookId = args[1];
        String userId = args[2];
        String dueDate = args[3];

        Map<String, User> userMap = library.getUsers();
        if (!userMap.containsKey(userId)) {
            library.createUser(userId);
        }
        library.borrowBook(bookId, userMap.get(userId), dueDate);

    }

    public void search(String command) {
        String[] args = command.split(" ");
        String attribute = args[1];
        String value = args[2];

        library.search(attribute, value);
    }

    public void printBorrowed(String command) {
        String[] args = command.split(" ");
        String userId = args[1];
        library.printBorrowed(userId);

    }

    public void returnBookCopy(String command) {
        String[] args = command.split(" ");
        String bookCopyId = args[1];

        library.returnBookCopy(bookCopyId);
    }
}
