package model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Andrey
 * @date 10/12/2020
 */


@Entity
@Table(name = "item")

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    private boolean done;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Item() {

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Item(String description, Date createdTime, boolean done, User user, Category category) {
        this.description = description;
        this.createdTime = createdTime;
        this.done = done;
        this.user = user;
        this.category = category;
    }

    public Item(Integer id, String description, Date createdTime, boolean done, User user, Category category) {
        this.id = id;
        this.description = description;
        this.createdTime = createdTime;
        this.done = done;
        this.user = user;
        this.category = category;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date created) {
        this.createdTime = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id
                && done == item.done
                && Objects.equals(description, item.description)
                && Objects.equals(createdTime, item.createdTime)
                && Objects.equals(user, item.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, createdTime, done, user);
    }


    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", createdTime=" + createdTime
                + ", done=" + done
                + ", user=" + user.getUsername()
                + ", category=" + category.getDesccategory()
                + '}';
    }
}
