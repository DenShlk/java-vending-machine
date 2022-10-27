package cli;

/**
 * Хранит информацию о продукте, позволяет уменьшать его количество.
 */
public class Item {
    final String name;
    final int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s price=%d", name, price);
    }
}
