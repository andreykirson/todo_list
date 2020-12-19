package store;

import model.Category;
import model.Item;
import model.User;

import java.util.List;

public interface Store {
    void addItem(Item item);
    void delete(Item item);
    void update(Item item);
    Item findItemById(int id);
    List<Item> findAll();
    User findUserByEmail(String email);
    void createUser(User user);
    List<Category> findAllCategories();
}
