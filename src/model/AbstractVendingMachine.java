package model;

import java.util.List;

/**
 * Абстрактный класс для автомата, вообще говоря, ничего не делает, кроме как хранит два основных компонента -
 * хранилище товаров и список способов оплаты.
 * @param <ID> класс идентификатора товара
 * @param <Item> класс товара
 * @param <PM> интерфейс метода оплаты
 */
public class AbstractVendingMachine<ID, Item, PM extends PaymentMethod> {
    protected final List<PM> paymentMethods;
    protected final ItemStorage<ID, Item> storage;

    public AbstractVendingMachine(List<PM> paymentMethods, ItemStorage<ID, Item> storage) {
        this.paymentMethods = paymentMethods;
        this.storage = storage;
    }
}
