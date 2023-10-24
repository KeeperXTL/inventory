package ua.keeperxtl.inventory.db.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventoryId", nullable = false)
    private Integer inventoryId;
    @Column(name = "date", nullable = false)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<Rack> rackList = new ArrayList<>();

    public Inventory() {
    }

    public Inventory(Date date, Users user) {
        this.date = date;
        this.user = user;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Rack> getRackList() {
        return rackList;
    }

    public void setRackList(List<Rack> rackList) {
        this.rackList = rackList;
    }
}
