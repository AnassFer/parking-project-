package parking.refactored;

public interface VehicleTariff {
    boolean supports(String vehicleType);
    double calculateFee(int hours);
}