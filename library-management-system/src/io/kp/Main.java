package io.kp;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> commands = new ArrayList<>();
        // Autowire the Command Handler here
        CommandHandler handler = new CommandHandler();

        // Populate the commands
        commands.add("create_library 10");
        commands.add("add_book 1 book1 author1,author2 publisher1 book_copy1,book_copy2,book_copy3");
        commands.add("add_book 2 book2 author2,author3 publisher2,publisher3 book_copy4,book_copy5,book_copy6,book_copy7,book_copy8,book_copy9,book_copy10");
        commands.add("add_book 3 book3 author2 publisher2 book_copy11,book_copy12,book_copy13");
        commands.add("search book_id 1");
        commands.add("search book_id 3");
        commands.add("search author_id author2");
        commands.add("search author_id author3");
        commands.add("remove_book_copy book_copy6");
        commands.add("remove_book_copy book_copy13");
        commands.add("add_book 3 book3 author2 publisher2 book_copy13");
        commands.add("search book_id 2");
        commands.add("print_borrowed user1");
        commands.add("borrow_book 1 user1 2020-12-31");
        commands.add("borrow_book 1 user1 2020-12-31");
        commands.add("borrow_book 1 user1 2020-12-31");
        commands.add("borrow_book 1 user1 2020-12-31");
        commands.add("search book_id 1");
        commands.add("search author_id author1");
        commands.add("borrow_book 4 user1 2020-12-31");
        commands.add("borrow_book 2 user1 2020-12-31");
        commands.add("borrow_book 2 user1 2020-12-31");
        commands.add("borrow_book 2 user1 2020-12-31");
        commands.add("print_borrowed user1");
        commands.add("return_book_copy book_copy1");
        commands.add("return_book_copy book_copy2");
//        commands.add("borrow_book_copy book_copy1");
//        commands.add("borrow_book_copy book_copy1");
//        commands.add("borrow_book_copy book_copy2");
//        commands.add("borrow_book_copy book_copy10");
        commands.add("print_borrowed user1");
        commands.add("exit");


        // Parse the inputs here and call the respective function
        for (int i = 0; i < commands.size(); i++) {
            String command = commands.get(i);
            String operation = command.split(" ")[0];
            if (operation.equals("exit")) {
                break;
            }
            switch (operation) {
                case "create_library":
                    handler.createLibrary(command);
                    break;
                case "add_book":
                    handler.addBook(command);
                    break;
                case "remove_book_copy":
                    handler.removeBookCopy(command);
                    break;
                case "borrow_book":
                    handler.borrowBook(command);
                    break;
                case "search":
                    handler.search(command);
                    break;
                case "print_borrowed":
                    handler.printBorrowed(command);
                    break;
                case "return_book_copy":
                    handler.returnBookCopy(command);
                    break;
                default:
                    System.out.println("Invalid command found.");
            }
        }
    }
}
