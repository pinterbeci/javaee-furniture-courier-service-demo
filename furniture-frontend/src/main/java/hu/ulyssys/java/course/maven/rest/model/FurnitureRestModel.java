package hu.ulyssys.java.course.maven.rest.model;

public class FurnitureRestModel extends CoreRestModel {

    private String description;

    private String furnitureName;

    private int price;

    public FurnitureRestModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
