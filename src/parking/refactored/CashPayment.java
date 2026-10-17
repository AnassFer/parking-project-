package parking.refactored;

public class CashPayment implements PaymentMethod {
    @Override
    public String name() {
        return "Cash";
    }

    @Override
    public void pay(double amount) {
        System.out.println("Processing cash payment of: " + amount);
    }
}
