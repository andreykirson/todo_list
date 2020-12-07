package store;

import model.Item;
import java.util.List;

public interface Store {
    void addItem(Item item);
    void delete(Item item);
    void update(Item item);
    Item findItemById(int id);
    List<Item> findAll();
}
