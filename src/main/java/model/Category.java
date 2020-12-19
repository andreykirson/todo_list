package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String desc;


    public Category() {

    }


    public Category(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public Category(String desc) {
        this.desc = desc;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String description) {
        this.desc = description;
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
                && Objects.equals(desc, category.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desc);
    }

    @Override
    public String toString() {
        return "Category{"
                + "description='" + desc + '\''
                + '}';
    }
}
