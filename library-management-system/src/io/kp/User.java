package io.kp;

public class User {

    private String userId;
    private int numOfBooksIssued;

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getNumOfBooksIssued() {
        return numOfBooksIssued;
    }

    public void incrementBooksIssued() {
        numOfBooksIssued++;
    }

    public void decrementBooksIssued() {
        numOfBooksIssued--;
    }
}
