package io.kp;

import io.kp.models.Board;
import io.kp.models.Dice;
import io.kp.models.Player;
import io.kp.services.SnakeAndLadderService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Read the inputs from the file and create the service.
        Path filePath = Paths.get("src/io/kp/inputs/inputs.txt");
        // Read all the commands from the file path here
        List<String> inputs = readInputsFromFile(filePath);

        SnakeAndLadderService gameService = initializeGameService(inputs);

        gameService.playGame();

    }

    private static List<String> readInputsFromFile(Path path) {
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            List<String> commands = new ArrayList<>(lines);
            return lines;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static SnakeAndLadderService initializeGameService(List<String> inputs) {
        // Get a singleton dice with maxValue of 6
        Dice dice = Dice.getInstance(6);

        // Parse the inputs and create board object
        Map<Integer, Integer> snakesAndLadders = new HashMap<>();
        int numOfSnakes = Integer.parseInt(inputs.get(0));
        for (int i = 1; i < numOfSnakes + 1; i++) {
            String[] input = inputs.get(i).split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            snakesAndLadders.put(from, to);
        }

        int numOfLadders = Integer.parseInt(inputs.get(numOfSnakes + 1));
        for (int i = numOfSnakes + 2; i < numOfSnakes + numOfLadders + 2; i++) {
            String[] input = inputs.get(i).split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            snakesAndLadders.put(from, to);
        }

        // Construct the board assuming a size of 10x10
        Board board = new Board(10, 10, snakesAndLadders);

        // Parse the inputs and construct players
        List<Player> players = new ArrayList<>();

        int numOfPlayers = Integer.parseInt(inputs.get(numOfSnakes + numOfLadders + 2));
        for (int i = numOfSnakes + numOfLadders + 3; i < numOfSnakes + numOfLadders + 3 + numOfPlayers; i++) {
            Player player = new Player(inputs.get(i), dice);
            players.add(player);
        }


        return new SnakeAndLadderService(board, players);
    }
}
