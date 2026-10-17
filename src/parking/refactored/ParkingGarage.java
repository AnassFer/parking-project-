package parking.refactored;

import parking.CashPayment;
import parking.FileLogger;
import parking.PaymentMethod;
import parking.Vehicle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParkingGarage {
    private final List<String> receipts = new ArrayList<>();
    private VehicleTariff[] tariffs;
    private final Logger logger;
    private double total;

    // Both collaborators are injected from the outside
    public ParkingGarage(VehicleTariff[] tariffs, Logger logger) {
        this.tariffs = tariffs;
        this.logger = logger;
    }

    public double fee(Vehicle vehicle, int hours) {
        for (int i = 0; i < tariffs.length; i++) {
            if (tariffs[i].supports(vehicle.getType())) {
                return tariffs[i].calculateFee(hours);
            }
        }
        throw new IllegalArgumentException("Unknown vehicle type: " + vehicle.getType());
    }

    public void checkout(Vehicle vehicle, int hours, PaymentMethod method)
            throws IOException {
        double amount = fee(vehicle, hours);
        method.pay(amount);
        total += amount;

        String receipt = vehicle.getPlate() + " | " + vehicle.getType() + " | "
                + hours + " h | " + amount + " MAD | " + method.name();
        receipts.add(receipt);
        logger.log(receipt);
    }

    public void cancel(Vehicle vehicle, int hours, RefundablePaymentMethod method) {
        double amount = fee(vehicle, hours);
        method.refund(amount);
        logger.log("Cancelled checkout for: " + vehicle.getPlate());
    }

    public String dailyReport() {
        StringBuilder sb = new StringBuilder("=== Daily report ===\n");
        for (String receipt : receipts) {
            sb.append(receipt).append("\n");
        }
        sb.append("Total: ").append(total).append(" MAD");
        return sb.toString();
    }
}
