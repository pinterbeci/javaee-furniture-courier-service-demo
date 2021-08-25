package hu.ulyssys.java.course.maven.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;


@Table(name = "furniture_table")
@Entity
public class Furniture extends AbstractFurniture {

    @Column(name = "furniture_name", length = 200, nullable = false)
    private String furnitureName;

    @Column(name = "furniture_description", length = 500, nullable = false)
    private String description;

    @Column(name = "furniture_price", nullable = false)
    private int price;

    public Furniture() {
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public FurnitureType getFurnitureType() {
        return FurnitureType.FURNITURE;
    }
}
