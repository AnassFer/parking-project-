package parking.refactored;

public class Vehicle {
    private final String plate;
    private final String type;      // "CAR", "MOTORBIKE" or "TRUCK"

    public Vehicle(String plate, String type) {
        this.plate = plate;
        this.type = type;
    }

    public String getPlate() { return plate; }
    public String getType()  { return type; }
}
