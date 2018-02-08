package post;

/**
 * @author  Ian Dennis
 */

public class Product {

    private String upc;
    private String description;
    private double price;

    Product() {
        this.upc = "";
        this.description = "";
        this.price = 0.0;
    }

    Product(String barcode, String prodDescription, double price) {

        this.upc = barcode;
        this.description = prodDescription;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getUpc() {
        return upc;
    }

    public String getProductDescription() {
        return description;
    }

    public void setProductDescription(String description) {
        this.description = description;
    }

    public void setPrice(double unitPrice) {
        this.price = unitPrice;
    }

    public void setUPC(String upc) {
        this.upc = upc;
    }
}
