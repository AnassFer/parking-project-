package parking.refactored;

public class TruckTariff implements VehicleTariff {
    @Override
    public boolean supports(String vehicleType) {
        return "Truck".equalsIgnoreCase(vehicleType);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 10.0; // Truck rate
    }
}