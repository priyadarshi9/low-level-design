package io.kp.models;

/**
 * Class representing a piece for a single player.
 */
public class Piece {

    private int currentPosition;

    public Piece() {
        this.currentPosition = Board.INITIAL_POSITION;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
