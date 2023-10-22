package dunbar.c482pa.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class allows for related parts to be associated into Product objects, enabling inventory tracking of completely assembled items. */
public class Product {
    /**List of parts associated with a particular product*/
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    /**Unique product ID number*/
    private int id;
    /**Product name*/
    private String name;
    /**Product price*/
    private double price;
    /**Amount of a product in stock*/
    private int stock;
    /**Minimum required amount of product to be kept in stock*/
    private int min;
    /**Maximum amount of a product allowed to be kept in stock*/
    private int max;

    /** Constructor. Creates a Product object using the provided arguments.
     * @param id ID number of the product
     * @param name Name of the product
     * @param price Price of the product
     * @param stock Current inventory of the product
     * @param min Minimum required inventory of the product
     * @param max Maximum required inventory of the product*/
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** Retrieves the ID number of a product.
     * @return Returns the product ID*/
    public int getId() {

        return id;
    }

    /** Sets the ID number of a product.
     * @param id ID number of the product*/
    public void setId(int id) {

        this.id = id;
    }

    /** Retrieves the name of a product.
     * @return Returns the name of the product*/
    public String getName() {

        return name;
    }

    /** Sets the name of a product.
     * @param name Name for the product*/
    public void setName(String name) {

        this.name = name;
    }

    /** Retrieves the price for a product.
     * @return Returns the price of the product*/
    public double getPrice() {

        return price;
    }

    /** Sets the price of a product.
     * @param price Price of the product*/
    public void setPrice(double price) {

        this.price = price;
    }

    /** Retrieves the amount of a product in stock.
     * @return Returns the amount of the product in stock*/
    public int getStock() {

        return stock;
    }

    /** Sets the amount of a product in stock.
     * @param stock Current inventory of the product*/
    public void setStock(int stock) {

        this.stock = stock;
    }

    /** Retrieves the minimum required inventory for a product.
     * @return Returns the minimum required inventory for the product*/
    public int getMin() {

        return min;
    }

    /** Sets the minimum required inventory for a product.
     * @param min Minimum required inventory for the product */
    public void setMin(int min) {

        this.min = min;
    }

    /** Retrieves the maximum allowable inventory for a product.
     * @return Returns the maximum allowable inventory for the product. */
    public int getMax() {

        return max;
    }

    /** Sets the maximum allowable inventory for a product.
     * @param max Maximum allowable inventory for the product*/
    public void setMax(int max) {

        this.max = max;
    }

    /** Associates a Part with a product.
     * Done by adding the Part object to the associatedParts list for the Product.
     * @param part Part to be associated with the product*/
    public void addAssociatedPart(Part part) {

        associatedParts.add(part);
    }

    /** Disassociates a part from a product.
     * Done by removing the part from the associatedParts list for the product.
     * @param part Part to be removed from the product
     * @return Returns true if the part has been disassociated from the product, otherwise returns false*/
    public boolean deleteAssociatedPart(Part part) {
        return associatedParts.remove(part);

    }

    /** Retrieves the list of parts associated with a product.
     * @return Returns an Observable List associatedParts consisting of all parts associated with the product. */
    public ObservableList<Part> getAllAssociatedParts(){

        return associatedParts;
    }
}