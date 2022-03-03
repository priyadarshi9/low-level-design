package io.kp;

public class Rack {

    public static int NUMBER_OF_RACKS = 0;
    public static int NUMBER_OF_OCCUPIED_RACKS = 0;

    private int rackNumber = -1;
    private BookCopy bookCopy;
    private RackStatus rackStatus;

    public RackStatus getRackStatus() {
        return rackStatus;
    }

    public void setRackStatus(RackStatus rackStatus) {
        this.rackStatus = rackStatus;
    }

    public Rack() {
        this.rackNumber = ++NUMBER_OF_RACKS;
        this.rackStatus = RackStatus.AVAILABLE;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public int getRackNumber() {
        return rackNumber;
    }

    public void setRackNumber(int rackNumber) {
        this.rackNumber = rackNumber;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }
}
