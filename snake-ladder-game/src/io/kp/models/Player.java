package io.kp.models;

/**
 * Class denoting a single player.
 */
public class Player {

    private String name;
    private boolean isWinner;
    private final Dice dice;
    private Piece piece;

    public Player(String name, Dice dice) {
        this.name = name;
        this.dice = dice;
        this.isWinner = false;
        piece = new Piece();
    }

    public Dice getDice() {
        return dice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Method to roll dice for this player and return its move.
     */
    public int rollDice() {
        return dice.roll();
    }
}
