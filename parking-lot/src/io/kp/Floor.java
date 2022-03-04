package io.kp;

import java.util.ArrayList;
import java.util.List;

/**
 * A floor of the parking lot.
 */
public class Floor {
    private int floorNumber;
    private int numSlots;

    private List<ParkingSlot> slots;

    public Floor(int floorNumber, int numSlots) {
        this.floorNumber = floorNumber;
        this.numSlots = numSlots;
        slots = new ArrayList<>();
        initializeSlots();
    }

    /**
     * Create and initialize all the slots for this floor.
     */
    private void initializeSlots() {
        for (int i = 1; i <= numSlots; i++) {
            // Create a parking slot
            ParkingSlot slot;
            if (i == 1) {
                slot = new ParkingSlot(i, SlotType.TRUCK, floorNumber);
            } else if (i == 2 || i == 3) {
                slot = new ParkingSlot(i, SlotType.BIKE, floorNumber);
            } else {
                slot = new ParkingSlot(i, SlotType.CAR, floorNumber);
            }
            slots.add(slot);
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getNumSlots() {
        return numSlots;
    }

    public void setNumSlots(int numSlots) {
        this.numSlots = numSlots;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public void setSlots(List<ParkingSlot> slots) {
        this.slots = slots;
    }

    /**
     * Get free slots for the given vehicle.
     */
    public int getFreeSlotsCount(VehicleType vehicleType) {
        if (vehicleType == VehicleType.TRUCK) {
            return slots.get(0).isEmpty() ? 1 : 0;
        } else if (vehicleType == VehicleType.BIKE) {
            int firstSlot = slots.get(1).isEmpty() ? 1 : 0;
            int secondSlot = slots.get(2).isEmpty() ? 1 : 0;
            return firstSlot + secondSlot;
        } else {
            int count = 0;
            for (int i = 3; i < slots.size(); i++) {
                count += (slots.get(i).isEmpty() ? 1 : 0);
            }
            return count;
        }
    }

    /**
     * Get empty slot for this floor given a vehicle type.
     */
    public ParkingSlot getEmptySlot(VehicleType vehicleType) {
        if (vehicleType == VehicleType.TRUCK) {
            return slots.get(0).isEmpty() ? slots.get(0) : null;
        } else if (vehicleType == VehicleType.BIKE) {
            boolean firstSlot = slots.get(1).isEmpty();
            boolean secondSlot = slots.get(2).isEmpty();
            if (firstSlot) {
                return slots.get(1);
            } else if (secondSlot) {
                return slots.get(2);
            }
            return null;
        } else {
            int count = 0;
            for (int i = 3; i < slots.size(); i++) {
                if (slots.get(i).isEmpty()) {
                    return slots.get(i);
                }
            }
            return null;
        }
    }
}
