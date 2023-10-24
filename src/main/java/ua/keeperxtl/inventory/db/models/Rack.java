package ua.keeperxtl.inventory.db.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rack")
public class Rack {
    @Id
    @Column(name = "rackId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rackId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "inventoryId", nullable = false)
    private Inventory inventory;

    public Rack() {
    }

    public Rack(Users user, String name, Inventory inventory) {
        this.user = user;
        this.name = name;
        this.inventory = inventory;
    }

    @OneToMany(mappedBy = "rack", cascade = CascadeType.ALL)
    private List<Shelf> shelfList = new ArrayList<>();

    public Integer getRackId() {
        return rackId;
    }

    public void setRackId(Integer rackId) {
        this.rackId = rackId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Shelf> getShelfList() {
        return shelfList;
    }

    public void setShelfList(List<Shelf> shelfList) {
        this.shelfList = shelfList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
