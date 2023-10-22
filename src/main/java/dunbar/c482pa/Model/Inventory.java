package dunbar.c482pa.Model;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

//javaDocs located in: src/main/JavaDocs

/** This class creates an inventory application that can be used to track parts and products. Holds the main method for the application.
 * FUTURE ENHANCEMENT: Adding the ability to create a history list for parts and products to track items no longer
 * kept in inventory. Could be used to create an obsolete part database to reference if a customer wanted to buy a
 * replacement that is no longer in inventory. */
public class Inventory extends Application {
    /** List of all Parts in Inventory*/
    private static ObservableList<Part> allParts = FXCollections.observableArrayList(); //Holds all parts currently stored in application memory
    /**List of all Products in Inventory*/
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList(); //Holds all products currently stored in application memory
    /**List of filtered parts based off of search inputs*/
    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList(); //Holds the parts that match the filter criteria for the part search bar
    /**List of filtered proucts based off of search inputs*/
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList(); //Holds the parts that match the filter criteria for the product search bar

    /** Adds a Part object to the allParts Observable List.
     * Accepts a Part object and appends it to allParts.
     * @param newPart Part to add
     * */
    public static void addPart(Part newPart) {

        allParts.add(newPart);
    }

    /** Adds a product to the allProducts Observable list.
     * Accepts a Product object and appends it to allProducts.
     * @param newProduct Product to add*/
    public static void addProduct(Product newProduct){

        allProducts.add(newProduct);
    }

    /** Searches the allParts observable list for a specified part.
     * Accepts a part ID value and searches the allParts list for a part with a matching ID.
     * @param partID ID of the part to search for.
     * @return Returns the desired part, or null if the part was not found*/
    public static Part lookupPart(int partID) {

        int index = -1;

        for(Part part: allParts){
            index++;
            if(part.getId() == partID){
                return allParts.get(index);
            }
        }
        return null;
    }

    /** Searches the allProducts observable list for a specified product.
     * Accepts a product ID value and searches the allProducts list for a product with a matching ID.
     * @param productID ID of the product to search for.
     * @return Returns the desired Product, or null if the product was not found */
    public static Product lookupProduct(int productID){

        int index = -1;

        for(Product product : allProducts){
            index++;
            if(product.getId() == productID) {
                return allProducts.get(index);
            }
        }
        return null;
    }

    /** Searches the allParts observable list for a specified part.
     * Accepts a string and searches the allParts list for a part with a name that contains the input string.
     * @param partName string to search for
     * @return Returns a list of all parts whose name contains the input string. If no parts contain the input string, returns an empty list.*/
    public static ObservableList<Part> lookupPart(String partName){
       for(Part part: allParts){
           if((part.getName()).toLowerCase().contains(partName.toLowerCase())){
               filteredParts.add(part);
           }
       }
       return filteredParts;
    }

    /** Searches the allProducts observable list for a specified product.
     * Accepts a string and searches the allParts list for a part with a name that contains the input string.
     * @param productName string to search for
     * @return Returns a list of all products whose name contains the input string. If no parts contain the input string, returns an empty list.*/
    public static ObservableList<Product> lookupProduct(String productName){
        for(Product product:allProducts){
            if((product.getName()).toLowerCase().contains(productName.toLowerCase())){
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    /** Replaces a part in the allParts list with another part.
     * Accepts an index value and a Part object, and places the Part at the desired index location, overwriting any existing part at that index.
     * @param index the index of the part to be replaced
     * @param selectedPart the part that is replacing the current part in the list */
    public static void updatePart(int index, Part selectedPart){

        allParts.set(index, selectedPart);
    }

    /** Replaces a product in the allProducts list with another product
     * Accepts an index value and a Product object, and places the product at the desired index location, overwriting any existing product at that index.
     * @param index the index of the product to be replaced
     * @param newProduct the product that is replacing the current product in the list*/
    public static void updateProduct(int index, Product newProduct){

        allProducts.set(index, newProduct);
    }

    /** Removes a part from the allParts list.
     * @param selectedPart Part to be removed
     * @return Returns true if the part was found and removed, otherwise returns false*/
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    /** Removes a part from the allProducts list.
     * @param selectedProduct Product to be removed
     * @return Returns true if the product was found and removed, otherwise returns false*/
    public static boolean deleteProduct(Product selectedProduct){

        return allProducts.remove(selectedProduct);
    }

    /** Retrieves the allParts list.
     * @return Returns the Observable List allParts*/
    public static ObservableList<Part> getAllParts(){

        return allParts;
    }

    /** Retrieves the allProducts list.
     * @return Returns the Observable list allProducts*/
    public static ObservableList<Product> getAllProducts() {

        return allProducts;
    }

    /** Retrieves the filteredParts list.
     * This list consists of parts currently meeting the criteria of the part search field.
     * @return Returns the Observable List filteredParts*/
    public static ObservableList<Part> getFilteredParts(){

        return filteredParts;
    }

    /** Retrieves the filteredProducts list.
     * This list consists of products currently meeting the criteria of the product search field.
     * @return Returns the Observable List filteredProducts*/
    public static ObservableList<Product> getFilteredProducts(){

        return filteredProducts;
    }

    /** Brings up the main window of the application.
     * Implements the start method of the Application interface to launch the main window of the application when launch() is called in main(). Applies the scene defined in MainWindow.fxml to the stage.
     * @param stage Sets the stage or the initial window of the application. */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Inventory.class.getResource("/dunbar/c482pa/MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    /** Launches the application and initializes the parts and products lists with sample inventory items.
     * @param args Allows various command line arguments to be passed into the application on launch.*/
    public static void main(String[] args) {
        InHouse gear1 = new InHouse(1, "Drive Gear", 10.00, 10, 1, 10, 1 );
        InHouse gear2 = new InHouse(2, "First Gear", 10.00, 10, 1, 10, 1 );
        Outsourced shaft = new Outsourced(3, "Shaft", 5.00, 10, 1, 10, "Shafts-R-Us");

        allParts.add(gear1);
        allParts.add(gear2);
        allParts.add(shaft);

        Product transmission = new Product(1, "Transmission", 25.00, 10, 1, 10);
        allProducts.add(transmission);

        Product gearSet = new Product(2, "Gear Set", 13.00, 2, 1, 10);
        allProducts.add(gearSet);

        transmission.addAssociatedPart(gear1);
        transmission.addAssociatedPart(gear2);
        transmission.addAssociatedPart(shaft);

        gearSet.addAssociatedPart(gear1);
        gearSet.addAssociatedPart(gear2);

        launch();
    }
}