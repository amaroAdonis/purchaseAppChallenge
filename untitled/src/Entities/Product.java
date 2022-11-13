package Entities;

public class Product {

    private String productName;
    private Integer productId;

    public Product(String productName, Integer productId) {
        this.productName = productName;
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Product: "
                +productName
                + '\''
                + ", Product ID: " + productId;
    }
}
