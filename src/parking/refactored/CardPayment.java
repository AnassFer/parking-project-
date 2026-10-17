package parking.refactored;

public class CardPayment implements RefundablePaymentMethod {
    private final String lastDigits;

    public CardPayment(String lastDigits) {
        this.lastDigits = lastDigits;
    }

    @Override
    public String name() {
        return "Card (**** " + lastDigits + ")";
    }

    @Override
    public void pay(double amount) {
        System.out.println("Processing card payment of: " + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Processing card refund of: " + amount);
    }
}