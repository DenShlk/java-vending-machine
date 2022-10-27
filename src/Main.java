import cli.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Item[][] items = new Item[][]{
                {
                        new Item("Cola 0.33", 15), new Item("Cola 0.5", 25)
                },
                {
                        new Item("Twix", 12), new Item("Nuts", 10)
                },
        };
        Integer[][] counts = new Integer[][] {
                {1, 2},
                {3, 4}
        };
        List<PaymentMethod> payments = Arrays.asList(new DummyCashPayment(), new DummyCardPayment(30));

        CliVendingMachine vm = new CliVendingMachine(payments, items, counts);
        vm.Run();
    }
}
