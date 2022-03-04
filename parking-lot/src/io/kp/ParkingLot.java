package io.kp;

import java.util.*;

/**
 * Parking Lot class representing the entire system.
 */
public class ParkingLot {
    private final String name = "KP's Parking Lot";
    private final String parkingLotId;
    private final int numFloors;
    private final int numSlotsPerFloor;

    private List<Floor> floors;
    private Set<String> activeTickets;
    Map<String, Vehicle> ticketToVehicle;

    // Make the constructor private so as to make this into a Singleton class
    private ParkingLot(String parkingLotId, int numFloors, int numSlotsPerFloor) {
        this.parkingLotId = parkingLotId;
        this.numFloors = numFloors;
        this.numSlotsPerFloor = numSlotsPerFloor;

        activeTickets = new HashSet<>();
        ticketToVehicle = new HashMap<>();
        floors = new ArrayList<>();

        initializeParkingLot();
    }

    // private static instance variable for implementing the singleton pattern
    private static ParkingLot parkingLot = null;

    // Static getInstance method to implement the Singleton pattern
    public synchronized static ParkingLot getInstance(String parkingLotId, int numFloors, int numSlotsPerFloor) {
        if (parkingLot == null) {
            return new ParkingLot(parkingLotId, numFloors, numSlotsPerFloor);
        }
        return parkingLot;
    }

    /**
     * Initialize all the floors and slots
     */
    private void initializeParkingLot() {
        for (int i = 1; i <= numFloors; i++) {
            // Create a parking Floor
            Floor floor = new Floor(i, numSlotsPerFloor);
            floors.add(floor);
        }
    }

    public String getName() {
        return name;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public int getNumFloors() {
        return numFloors;
    }

    public int getNumSlotsPerFloor() {
        return numSlotsPerFloor;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public Set<String> getActiveTickets() {
        return activeTickets;
    }

    public void setActiveTickets(Set<String> activeTickets) {
        this.activeTickets = activeTickets;
    }

    public Map<String, Vehicle> getTicketToVehicle() {
        return ticketToVehicle;
    }

    public void setTicketToVehicle(Map<String, Vehicle> ticketToVehicle) {
        this.ticketToVehicle = ticketToVehicle;
    }

    // Add the useful functions here
    /**
     * Display Free slots for given vehicle and all floors.
     */
    public void displayFreeCount(String vehicleType) {
        VehicleType type = VehicleType.valueOf(vehicleType);

        for (int i = 0; i < floors.size(); i++) {
            Floor floor = floors.get(i);
            int freeCount = floor.getFreeSlotsCount(type);
            System.out.println("No. of free slots for " + vehicleType + " on Floor " +
                    floor.getFloorNumber() + " : " + freeCount);
        }
    }

    /**
     * Park the vehicle given vehicle params.
     */
    public void parkVehicle(String vehicleType, String regNumber, String color) {
        Vehicle vehicle = createVehicle(vehicleType, regNumber, color);

        ParkingSlot firstEmptySlot = getFirstEmptySlot(VehicleType.valueOf(vehicleType));

        if (firstEmptySlot == null) {
            System.out.println("Parking Lot Full.");
            return;
        }

        parkAndCreateParkingTicket(vehicle, firstEmptySlot);
    }

    /**
     * Unpark the vehicle given ticketId.
     */
    public void unparkVehicle(String ticketId) {
        if (!activeTickets.contains(ticketId)) {
            System.out.println("Invalid Ticket");
            return;
        }

        Vehicle vehicle = ticketToVehicle.get(ticketId);
        try {
            vehicle.unpark();
            activeTickets.remove(ticketId);
        } catch (Exception e) {
            System.out.println("Exception unparking vehicle in parking lot");
        }
    }

    // Park the vehicle and create Parking Ticket
    private void parkAndCreateParkingTicket(Vehicle vehicle, ParkingSlot slot) {
        // Park the vehicle in the given slot
        slot.assignVehicle(vehicle);
        vehicle.setSlot(slot);

        // Create a new parking Ticket
        String ticketId = new StringBuilder(parkingLotId)
                .append("_").append(slot.getFloorNumber()).append("_").append(slot.getSlotNumber())
                .toString();

        ParkingTicket ticket = new ParkingTicket(ticketId, vehicle);
        activeTickets.add(ticketId);
        ticketToVehicle.put(ticketId, vehicle);

        System.out.println("Parked Vehicle. Ticket ID: " + ticketId);
    }

    // Create a vehicle instance from given params and persist
    private Vehicle createVehicle(String vehicleType, String regNumber, String color) {
        return new Vehicle(regNumber, color, VehicleType.valueOf(vehicleType));
    }

    // Get the first empty slot from all floors
    private ParkingSlot getFirstEmptySlot(VehicleType vehicleType) {
        ParkingSlot slot = null;

        for (int i = 0; i < floors.size() && slot == null; i++) {
            slot = floors.get(i).getEmptySlot(vehicleType);
        }
        return slot;
    }


}
