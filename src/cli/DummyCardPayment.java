package cli;

/**
 * Позволяет оплачивать товары картой, карта всего одна, ее не нужно выбирать. Баланс задается при создании объекта.
 */
public class DummyCardPayment implements PaymentMethod {
    private int balance;

    public DummyCardPayment(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean attempt(int amount) {
        if (balance >= amount) {
            balance -= amount;
            CliVendingMachine.printlnAndSleep("Payment successful, " + balance + "₿ left.");
            return true;
        }
        CliVendingMachine.printlnAndSleep("Transaction declined, insufficient balance.");
        return false;
    }

    @Override
    public String infoString() {
        return "Single card bank. Balance: " + balance + "₿";
    }
}
