package io.kp;

/**
 * Class representing a parking slot.
 */
public class ParkingSlot {

    private int floorNumber;
    private int slotNumber;
    private SlotType slotType;
    private Vehicle vehicle;
    private SlotStatus slotStatus;

    public ParkingSlot(int slotNumber, SlotType slotType, int floorNumber) {
        this.slotNumber = slotNumber;
        this.slotType = slotType;
        this.floorNumber = floorNumber;
        this.slotStatus = SlotStatus.EMPTY;
    }

    /**
     * Method to assign vehicle to this slot.
     */
    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.slotStatus = SlotStatus.OCCUPIED;
    }

    /**
     * Method to assign vehicle to this slot.
     */
    private void removeVehicle() {
        this.vehicle = null;
        this.slotStatus = SlotStatus.EMPTY;
    }

    public SlotStatus getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(SlotStatus slotStatus) {
        this.slotStatus = slotStatus;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    /**
     * Checks if the slot is empty
     */
    public boolean isEmpty() {
        return slotStatus == SlotStatus.EMPTY;
    }

    /**
     * Unpark the vehicle parked in this slot.
     */
    public void unparkVehicle() {
        if (slotStatus == SlotStatus.EMPTY) {
            System.out.println("Cannot be unparked, slot is empty");
            return;
        }
        removeVehicle();
    }
}
