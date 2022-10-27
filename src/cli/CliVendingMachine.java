package cli;

import cli.util.TablePrinter;
import matrixvm.MatrixVendingMachine;
import matrixvm.RowColumnId;

import java.util.List;
import java.util.Scanner;

/**
 * Demo version of vending machine, interacts with user through the console.
 */
public class CliVendingMachine extends MatrixVendingMachine<Item, PaymentMethod> {
    private static final int MESSAGE_DELAY_MS = 2000;

    private final Scanner scanner = new Scanner(System.in);

    public CliVendingMachine(List<PaymentMethod> paymentMethods, Item[][] itemMatrix, Integer[][] countsMatrix) {
        super(paymentMethods, itemMatrix, countsMatrix);

        assert !paymentMethods.isEmpty();
    }

    public void Run() {
        while (true) {
            printStorage();
            System.out.println();
            System.out.println("Enter item code (<column letter><row number> e.g. A1, D3)");

            String line = scanner.nextLine();
            if (line.isEmpty()) {
                continue;
            }

            char letter = line.toUpperCase().charAt(0);
            if (!Character.isAlphabetic(letter)) {
                printlnAndSleep("First character must be letter");
            }
            try {
                int number = Integer.parseInt(line.substring(1));
                processItem(new RowColumnId(number, letter));
            } catch (NumberFormatException e) {
                printlnAndSleep("Second to last characters of the string should be numbers");
            }
        }
    }

    private void processItem(RowColumnId id) {
        Item item = storage.getItem(id);
        if (item == null) {
            printlnAndSleep("No such item");
            return;
        }
        if (storage.isInStock(id)) {
            System.out.println("You are getting: " + item.name);
            if (offerPayment(item.price)) {
                storage.withdrawItem(id);
                System.out.print("B");
                for (int i = 0; i < 5; i++) {
                    System.out.print("-z");
                    sleep(500);
                }
                System.out.println();
                printlnAndSleep("Here you go: " + item.name + " is yours!");
            }
        } else {
            printlnAndSleep(item.name + " is out of stock, sorry.");
        }
    }

    /**
     * Offers payment options to user
     *
     * @param amount amount to pay
     * @return success of payment
     */
    private boolean offerPayment(int amount) {
        while (true) {
            System.out.println("Enter number of one of the payment methods below or empty line to cancel:");
            for (int i = 0; i < paymentMethods.size(); i++) {
                System.out.printf("[%d] %s\n", i + 1, paymentMethods.get(i).infoString());
            }
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                continue;
            }

            try {
                int idx = Integer.parseInt(line);
                if (idx > 0 && idx <= paymentMethods.size()) {
                    PaymentMethod method = paymentMethods.get(idx - 1);
                    return method.attempt(amount);
                } else {
                    printlnAndSleep("Index " + idx + " is not in the valid range");
                }
            } catch (NumberFormatException e) {
                printlnAndSleep("Expected index of payment method");
            }
        }
    }

    /**
     * Fills table of string with item names and prices, then prints it with TablePrinter
     */
    private void printStorage() {
        String[][] table = new String[itemMatrixHeight + 1][itemMatrixWidth + 1];
        for (int i = 1; i < table.length; i++) {
            table[i][0] = String.valueOf(i);
        }

        for (int j = 1; j < table[0].length; j++) {
            table[0][j] = String.valueOf((char) ('A' + j - 1));
        }

        for (int i = 0; i < itemMatrixHeight; i++) {
            for (int j = 0; j < itemMatrixWidth; j++) {
                RowColumnId id = RowColumnId.fromIndices(i, j);
                Item item = storage.getItem(id);
                table[i + 1][j + 1] = storage.isInStock(id) ? item.price + "₿ - " + item.name : "SOLD OUT";
            }
        }

        TablePrinter.printTable(table);
    }
    
    static void printlnAndSleep(String msg) {
        System.out.println(msg);
        sleep(MESSAGE_DELAY_MS);
    }

    static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
