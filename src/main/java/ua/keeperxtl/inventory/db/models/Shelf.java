package ua.keeperxtl.inventory.db.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shelf")
public class Shelf {
    @Id
    @Column(name = "shelfId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shelfId;
    @ManyToOne
    @JoinColumn(name = "rackId", nullable = false)
    private Rack rack;

    public Shelf(Rack rack, Integer ordinalNumber, Users user) {
        this.rack = rack;
        this.ordinalNumber = ordinalNumber;
        this.user = user;
    }

    public Shelf() {
    }

    @Column(name = "ordinalNumber", nullable = false)
    private Integer ordinalNumber;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;
    @OneToMany(mappedBy = "shelf", cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();

    public Integer getShelfId() {
        return shelfId;
    }

    public void setShelfId(Integer shelfId) {
        this.shelfId = shelfId;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public Integer getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(Integer ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
