package parking.refactored;

public class CarTariff implements VehicleTariff {
    @Override
    public boolean supports(String vehicleType) {
        return "Car".equalsIgnoreCase(vehicleType);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 5.0; // Standard car rate
    }
}
