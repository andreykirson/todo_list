package model;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "item")

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private Timestamp createdTime;
    private boolean done;

    public Item() {

    }

    public Item(String description, Timestamp createdTime, boolean done) {
        this.description = description;
        this.createdTime = createdTime;
        this.done = done;
    }

    public Item(Integer id, String description, Timestamp createdTime, boolean done) {
        this.id = id;
        this.description = description;
        this.createdTime = createdTime;
        this.done = done;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp created) {
        this.createdTime = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Item item = (Item) o;
            result = done == item.done
                    &&
                    Objects.equals(id, item.id)
                    &&
                    Objects.equals(description, item.description)
                    &&
                    Objects.equals(createdTime, item.createdTime);
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, createdTime, done);
    }

}
