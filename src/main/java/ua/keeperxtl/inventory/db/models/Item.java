package ua.keeperxtl.inventory.db.models;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "itemId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    @ManyToOne
    @JoinColumn(name = "shelfId", nullable = false)
    private Shelf shelf;
    @Column(name = "count", nullable = false)
    private Float count;
    @Column(name = "price", nullable = false)
    private Float price;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public Float getCount() {
        return count;
    }

    public Item(Shelf shelf, Float count, Float price, Users user) {
        this.shelf = shelf;
        this.count = count;
        this.price = price;
        this.user = user;
    }

    public Item() {
    }

    public void setCount(Float count) {
        this.count = count;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
