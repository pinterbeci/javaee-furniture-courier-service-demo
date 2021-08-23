package hu.ulyssys.java.course.maven.entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "furniture_order_table")
public class Order extends AbstractFurniture {

    @Column(name = "delivery_date", nullable = false)
    private Date deliveryDate;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private AppUser customer;



    @JoinColumn(name = "furniture_list")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Furniture> furnitureList = new ArrayList<>();


    @Column(name = "settlement_name", nullable = false, length = 200)
    private String settlement;


    @Column(name = "public_space", nullable = false, length = 200)
    private String publicSpace;


    @Column(name = "address_number", nullable = false, length = 200)
    private String addressNumber;


    @Column(name = "nature_of_public_space", nullable = false, length = 200)
    private String natureOfPublicSpace;

    @Override
    public FurnitureType getFurnitureType() {
        return FurnitureType.COURIER;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    public void setFurnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getPublicSpace() {
        return publicSpace;
    }

    public void setPublicSpace(String publicSpace) {
        this.publicSpace = publicSpace;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public AppUser getCustomer() {
        return customer;
    }

    public void setCustomer(AppUser customer) {
        this.customer = customer;
    }

    public String getNatureOfPublicSpace() {
        return natureOfPublicSpace;
    }

    public void setNatureOfPublicSpace(String natureOfPublicSpace) {
        this.natureOfPublicSpace = natureOfPublicSpace;
    }
}
