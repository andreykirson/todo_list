package store;

import model.Item;
import java.util.List;

public interface Store {
    void addItem(Item item);
    boolean delete(Item item);
    boolean setStatus(int id, boolean status);
    List<Item> findAll();
}
