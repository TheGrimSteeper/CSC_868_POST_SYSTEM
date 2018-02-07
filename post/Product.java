package post;

/**
 * Created by dennisi1 on 2/7/18.
 */
public class Product {

    private String upc;
    private String description;
    private double unitPrice;

    Product(String barcode, String prodDescription, double price) {

        this.upc = barcode;
        this.description = prodDescription;
        this.unitPrice = price;
    }
}
