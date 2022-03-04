package io.kp;

/**
 * Class to handle command-specific logic.
 */
public class CommandHandler {

    private ParkingLot parkingLot;

    public CommandHandler() {
    }

    /**
     * Handle the Parking Lot creation command
     */
    public void createParkingLot(String command) {
        String[] args = command.strip().split(" ");
        String id = args[1];
        int numFloors = Integer.parseInt(args[2]);
        int numSlots = Integer.parseInt(args[3]);

        parkingLot = ParkingLot.getInstance(id, numFloors, numSlots);
        System.out.println("Created Parking Lot with " + numFloors + " floors and " +
                numSlots + " slots per floor.");
    }

    /**
     * Handle the display command for various attributes;
     */
    public void display(String command) {
        String[] args = command.strip().split(" ");
        String attribute = args[1];
        String vehicleType = args[2];

        switch (attribute) {
            case "free_count":
                parkingLot.displayFreeCount(vehicleType);
                break;
        }
    }

    /**
     * Handle the parking of a vehicle.
     */
    public void parkVehicle(String command) {
        String[] args = command.strip().split(" ");
        String vehicleType = args[1];
        String regNumber = args[2];
        String color = args[3];

        parkingLot.parkVehicle(vehicleType, regNumber, color);
    }

    /**
     * Handle the unparking of vehicles.
     */
    public void unparkVehicle(String command) {
        String[] args = command.strip().split(" ");
        String ticketId = args[1];

        parkingLot.unparkVehicle(ticketId);
    }
}
