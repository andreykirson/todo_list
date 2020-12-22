package model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Andrey
 * @date 10/12/2020
 */


@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String desccategory;

    public Category() {

    }

    public Category(int id, String desccategory) {
        this.id = id;
        this.desccategory = desccategory;
    }

    public Category(String desccategory) {
        this.desccategory = desccategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesccategory() {
        return desccategory;
    }

    public void setDesccategory(String description) {
        this.desccategory = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return id == category.id
                && Objects.equals(desccategory, category.desccategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desccategory);
    }

    @Override
    public String toString() {
        return "Category{"
                + "id=" + id
                + ", desccategory='" + desccategory + '\''
                + '}';
    }
}
