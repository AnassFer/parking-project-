package parking.refactored;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParkingGarage {
    private final List<String> receipts = new ArrayList<>();
    private double total = 0.0;

    private final VehicleTariff[] tariffs;
    private final Logger logger;
    private final ParkingReportFormatter formatter;


    public ParkingGarage(VehicleTariff[] tariffs, Logger logger, ParkingReportFormatter formatter) {
        this.tariffs = tariffs;
        this.logger = logger;
        this.formatter = formatter;
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

        String receipt = formatter.formatReceipt(vehicle, amount);
        receipts.add(receipt);
        logger.log(receipt);
    }

    public void cancel(Vehicle vehicle, int hours, RefundablePaymentMethod method) {
        double amount = fee(vehicle, hours);
        method.refund(amount);
        logger.log("Cancelled checkout for: " + vehicle.getPlate());
    }

    public String dailyReport() {

        String report = formatter.formatDailyReport(total);
        logger.log(report);
        return report;
    }
}
