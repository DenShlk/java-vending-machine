package model;

/**
 * Предоставляет функционал попытки оплаты и отмены.
 */
public interface PaymentMethod {
    boolean attempt(int amount);
}
