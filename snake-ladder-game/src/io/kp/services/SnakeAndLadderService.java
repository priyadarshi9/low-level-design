package io.kp.services;

import io.kp.models.Board;
import io.kp.models.Player;

import java.util.List;

/**
 * The service responsible for playing the game.
 */
public class SnakeAndLadderService {

    private Board board;
    private List<Player> players;
    private boolean gameInProgress;


    public SnakeAndLadderService(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.gameInProgress = true;
    }

    /**
     * The play method which runs the game given players and board.
     */
    public void playGame() {

        while (gameInProgress) {
            for (int i = 0; i < players.size(); i++) {
                Player currentPlayer = players.get(i);

                int move = currentPlayer.rollDice();
                int nextPosition = board.getNextPosition(currentPlayer.getPiece().getCurrentPosition(), move);

                // Have the logic to terminate game here
                if (nextPosition == Board.FINAL_POSITION) {
                    // currentPlayer won the game
                    System.out.println(currentPlayer.getName() + " wins the game");
                    currentPlayer.getPiece().setCurrentPosition(Board.INITIAL_POSITION);
                    gameInProgress = false;
                    break;
                } else if (nextPosition > Board.FINAL_POSITION) {
                    continue;
                } else {
                    int prevPosition = currentPlayer.getPiece().getCurrentPosition();
                    currentPlayer.getPiece().setCurrentPosition(nextPosition);

                    System.out.println(currentPlayer.getName() + " rolled a " + move +
                            " and moved from " + prevPosition + " to " + nextPosition);
                }
            }
        }

        // Game is over, reset the game
        gameInProgress = true;
    }
}
