package io.kp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        CommandHandler handler = new CommandHandler();

        Path filePath = Paths.get("src/io/kp/input/commands.txt");
        // Read all the commands from the file path here
        List<String> commands = readCommandsFromFile(filePath);

        if (commands == null) {
            System.out.println("Commands could not be read, exiting.");
        }

        for (int i = 0; i < commands.size(); i++) {
            String command = commands.get(i);
            String commandHead = command.split(" ")[0];

            switch(commandHead) {
                case "create_parking_lot":
                    handler.createParkingLot(command);
                    break;
                case "display":
                    handler.display(command);
                    break;
                case "park_vehicle":
                    handler.parkVehicle(command);
                    break;
                case "unpark_vehicle":
                    handler.unparkVehicle(command);
                    break;
            }
        }
    }

    private static List<String> readCommandsFromFile(Path path) {
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
}
