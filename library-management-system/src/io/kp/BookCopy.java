package io.kp;

import java.util.Arrays;

public class BookCopy extends Book {

    private String bookCopyId;
    private BookStatus status;
    private User borrowedBy;
    private String dueDate;
    private Rack rack;

    public BookCopy(String id, String copyId, String title, String[] authors,
                    String[] publishers) {
        super(id, title, authors, publishers);
        this.bookCopyId = copyId;
        this.status = BookStatus.AVAILABLE;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public User getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(User borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }



    public String getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(String bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    @Override
    public String toString() {
        String str = "Book Copy: ";
        str += this.bookCopyId + " ";
        str += this.getBookId() + " ";
        str += this.getTitle() + " ";
        str += Arrays.toString(this.getAuthors()) + " ";
        str += Arrays.toString(this.getPublishers()) + " ";
        str += this.getRack() == null ? "-1 " : this.getRack().getRackNumber() + " ";
        str += this.borrowedBy == null ? "" : this.borrowedBy.getUserId() +  " ";
        str += this.dueDate == null ? "" : this.dueDate;

        return str;
    }
}
