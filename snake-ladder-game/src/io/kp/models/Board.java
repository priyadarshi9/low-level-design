package io.kp.models;

import java.util.Map;

/**
 * Class representing board on which the game is played.
 */
public class Board {

    public static final int INITIAL_POSITION = 0;
    public static int FINAL_POSITION = 0;

    private int numRows;
    private int numCols;
    // TODO: Will see if we really need this or not.
    private int[][] board;

    private Map<Integer, Integer> snakesAndLadders;

    public Board(int numRows, int numCols, Map<Integer, Integer> snakesAndLadders) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.board = new int[numRows][numCols];
        this.snakesAndLadders = snakesAndLadders;
        FINAL_POSITION = numRows * numCols;
    }

    /**
     * Get next position of a piece given its current position and move.
     */
    public int getNextPosition(int currentPosition, int move) {
        int nextPosition = 0;
        if (snakesAndLadders.containsKey(currentPosition)) {
            nextPosition = snakesAndLadders.get(currentPosition);
            while (snakesAndLadders.containsKey(nextPosition)) {
                nextPosition = snakesAndLadders.get(nextPosition);
            }
            return nextPosition;
        } else {
            return currentPosition + move;
        }
    }
}
