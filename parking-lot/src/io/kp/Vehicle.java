package io.kp;

public class Vehicle {
    private String regNumber;
    private String color;
    private VehicleType vehicleType;

    private ParkingSlot slot;

    public Vehicle(String regNumber, String color, VehicleType vehicleType) {
        this.regNumber = regNumber;
        this.color = color;
        this.vehicleType = vehicleType;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public void setSlot(ParkingSlot slot) {
        this.slot = slot;
    }

    /**
     * Unpark the vehicle from the parking slot.
     */
    public void unpark() {
        try {
            slot.unparkVehicle();
            setSlot(null);
            System.out.println("Unparked vehicle with Registration number " + regNumber +
                    " and Color: " + color);
        } catch (Exception e) {
            System.out.println("Exception occurred unparking vehicle.");
        }
    }
}
