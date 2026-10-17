package parking.refactored;



public class ParkingReportFormatter {
    public String formatReceipt(Vehicle vehicle, double amount) {
        return "Checkout: " + vehicle.getPlate() + " - Paid: " + amount;
    }

    public String formatDailyReport(double total) {
        return "Daily Total: " + total;
    }
}