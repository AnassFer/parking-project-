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
    private final FileLogger logger = new FileLogger("parking.log");
    private VehicleTariff[] tariffs;
    private double total;

    public ParkingGarage(VehicleTariff[] tariffs) {
        this.tariffs = tariffs;
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

    public void cancel(Vehicle vehicle, int hours, PaymentMethod method) {
        double amount = fee(vehicle, hours);
        if (method instanceof CashPayment) {            // cash cannot be refunded
            System.out.println("Refund " + amount + " MAD at the desk for "
                    + vehicle.getPlate());
        } else {
            method.refund(amount);
        }
        total -= amount;
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
