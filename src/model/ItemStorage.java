package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Хранит и дает доступ к продуктам внутри автомата и их количествам.
 * @param <Id> класс идентификатора продукта
 */
public class ItemStorage<Id, Item> {
    private final Map<Id, Item> items = new HashMap<>();
    private final Map<Id, Integer> counts = new HashMap<>();

    public ItemStorage(Map<Id, Item> items, Map<Id, Integer> counts) {
        this.items.putAll(items);
        this.counts.putAll(counts);
    }

    public Item getItem(Id id) {
        return items.get(id);
    }

    public Integer getCount(Id id) {
        return counts.get(id);
    }

    public boolean isInStock(Id id) {
        return getCount(id) > 0;
    }

    public void withdrawItem(Id id) {
        int count = getCount(id);
        if (count > 0) {
            counts.put(id, count - 1);
        } else {
            throw new IllegalStateException("Attempt to withdraw item, which is out of stock: " + this);
        }
    }
}
