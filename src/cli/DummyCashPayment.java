package cli;

import java.util.Scanner;

/**
 * Represents an *honest* paying method, there is a box near the machine, in which users can put coins.
 * User enters what coins they put, also, if they need to take a change they take it from the box.
 */
public class DummyCashPayment implements PaymentMethod {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean attempt(int amount) {
        int sum = 0;
        while (sum < amount) {
            System.out.println("You need " + (amount - sum) + "₿ more.");
            System.out.println("Put a coin in the box, then enter its value, or \"cancel\" to cancel payment");
            String line = scanner.nextLine();
            if (line.equals("cancel")) {
                CliVendingMachine.printlnAndSleep("You may take back what you put in (" + sum +
                                "₿), but asking to pay and canceling is not exactly honest.");
                return false;
            }
            try {
                int value = Integer.parseInt(line);
                if (value < 0) {
                    CliVendingMachine.printlnAndSleep("Do you mean you took money out of the box? " +
                            "Stay where you are, police is being informed.");
                    continue;
                }
                sum += value;
            } catch (NumberFormatException e) {
                CliVendingMachine.printlnAndSleep("This is not a number. Are you not being honest?");
            }
        }
        if (sum > amount) {
            CliVendingMachine.printlnAndSleep("You may take a change (" + (sum - amount) +
                    "₿), but not more (if you cannot take the exact amount, take less)");
        }
        return true;
    }

    @Override
    public String infoString() {
        return "Honest cash payments™";
    }
}
