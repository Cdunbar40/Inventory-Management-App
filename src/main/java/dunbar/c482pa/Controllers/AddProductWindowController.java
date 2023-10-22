package dunbar.c482pa.Controllers;

import dunbar.c482pa.Model.Inventory;
import dunbar.c482pa.Model.Part;
import dunbar.c482pa.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**The AddProductWindowController controls the interface used to add new products to the Inventory.
 * It includes methods for associating a part with the new Product. The Initializable interface is implemented to
 * populate a table of parts that can be associated with the product, and to automatically generate a unique product ID. */
public class AddProductWindowController implements Initializable {
    /** Holds the main window to display scenes*/
    private Stage stage;
    /** Holds the scene for the window*/
    private Parent scene;
    /**Holds the list of associated parts before saving them to the new product's associatedParts list*/
    private ObservableList<Part> partHolder = FXCollections.observableArrayList();
    /** Initializes the ID for the next product to 1.
     * Used in initialize()*/
    private int nextID = 1;
    /**Table for all available parts*/
    @FXML
    private TableView<Part> availablePartsTable;
    /**Table column for part IDs*/
    @FXML
    private TableColumn<Part, Integer> allPartsIDCol;
    /**Table column for part inventory*/
    @FXML
    private TableColumn<Part, Integer> allPartsInvCol;
    /**Table column for part names*/
    @FXML
    private TableColumn<Part, String> allPartsNameCol;
    /**Table column for part prices*/
    @FXML
    private TableColumn<Part, Double> allPartsPriceCol;
    /**Text field for the product ID*/
    @FXML
    private TextField idText;
    /**Text field for product inventory*/
    @FXML
    private TextField invText;
    /**Text field for the max inventory value*/
    @FXML
    private TextField maxText;
    /**Text field for the min inventory value*/
    @FXML
    private TextField minText;
    /**Text field for the product name*/
    @FXML
    private TextField nameText;
    /** Text field for the product price*/
    @FXML
    private TextField priceText;
    /**Text field for the Part table search bar*/
    @FXML
    private TextField partSearch;
    /**Table for the product's associated parts*/
    @FXML
    private TableView<Part> partsInProductTable;
    /**Column for the associated part IDs*/
    @FXML
    private TableColumn<Part, Integer> usedPartsIDCol;
    /**Column for the associated part inventory*/
    @FXML
    private TableColumn<Part, Integer> usedPartsInvCol;
    /**Column for the associated part names*/
    @FXML
    private TableColumn<Part, String> usedPartsNameCol;
    /**Column for the associated part prices*/
    @FXML
    private TableColumn<Part, Double> usedPartsPriceCol;

    /** Loads the specified scene for a new window.
     * @param event An action even for a button press in the GUI
     * @param newAddress The package address for the desired FXML file*/
    private void changeWindowButton (ActionEvent event, String newAddress) throws IOException{
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load((getClass().getResource(newAddress)));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Populates the part table and generates a unique Product ID when the window is loaded.
     *@param url The location used to resolve relative paths for the root object, or null if the location is not known.
     *@param resourceBundle The resources used to localize the root object, or null if the root object was not localized.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partHolder.clear();
        availablePartsTable.setItems(Inventory.getAllParts());
        allPartsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        int i = 0;
        int numProds = Inventory.getAllProducts().size();

        //Compares nextID to each element in allProducts and grabs the next free ID number,  starting with number 1.
        for(i = 0; i < numProds; i++)
            if(nextID == (Inventory.getAllProducts()).get(i).getId()){
                nextID++;
                i = 0;
            }
        idText.setText(String.valueOf(nextID));

    }

    /**Associates a Part with the Product when the Add part button is pressed.
     * Adds the selected Part object to the Product's associatedParts observable list when the Add button is pressed.
     * @param event Event generated by pressing the Add button*/
    @FXML
    void onActionAddAssociatedPart(ActionEvent event) {
        Part selectedPart = availablePartsTable.getSelectionModel().getSelectedItem();
        partHolder.add(selectedPart);

        partsInProductTable.setItems(partHolder);
        usedPartsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        usedPartsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        usedPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        usedPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**Disassociates a Part from the Product when the Remove Associated Part button is pressed.
     * Removes the selected part from the Product's associatedParts observable list when the Remove Associated Parts button is pressed.
     * @param event Event generated by pressing the Remove Associated Parts button*/
    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {
        String selectedPart = partsInProductTable.getSelectionModel().getSelectedItem().getName();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Remove part: " + selectedPart + "?");
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        confirmation.getButtonTypes().setAll(yesButton, noButton);
        Optional<ButtonType> result = confirmation.showAndWait();

        if(result.isPresent() && result.get() == yesButton) {
            partHolder.remove(partsInProductTable.getSelectionModel().getSelectedItem());
            partsInProductTable.setItems(partHolder);
            usedPartsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            usedPartsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            usedPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            usedPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        }

    }

    /**Implements search and filtering functionality for the part table.
     * Every time a key is pressed in the part search text box, runs the appropriate Inventory lookupPart()
     * method based on the type of input (integer vs string) then filters the part table contents accordingly.
     * @param event Event generated by typing in the part search text box*/
    @FXML
    void onActionSearchParts(KeyEvent event) {
        String search = partSearch.getText();
        int partID = 0;
        ObservableList<Part> filter = FXCollections.observableArrayList();  //Establishes a local filter list for the search.

        availablePartsTable.getSelectionModel().clearSelection();   //Clears any previous selections in the part table

        if(!(Inventory.getFilteredParts().isEmpty())){  //Clears the FilteredParts field from Inventory
            Inventory.getFilteredParts().clear();
        }

        try{
            //Attempts to run Inventory.lookupPart() using a part ID. If the input string cannot be parsed as an integer, throws an exception.
            partID = Integer.parseInt(search);
            if(!(Inventory.lookupPart(partID) == null)) {
                filter.add(Inventory.lookupPart(partID));
            }
        }
        //If the input was not an integer, runs Inventory.lookupPart() using a string instead.
        catch(NumberFormatException notInt) {
            filter = Inventory.lookupPart(search);
        }
        //Regenerates the part table using the filtered list. If the list is empty, outputs an error message.
        availablePartsTable.setItems(filter);
        allPartsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        if(filter.size() == 1){
            availablePartsTable.getSelectionModel().select(filter.get(0));
        }
        if(filter.size() == 0){
            Alert notFound = new Alert(Alert.AlertType.ERROR, "Item not found!");
            notFound.show();
        }
    }

    /** Returns to the Main window when the cancel button is pressed.
     * @param event Event generated by pressing the cancel button*/
    @FXML
    void onActionReturnToMain(ActionEvent event) throws IOException {
        changeWindowButton(event, "/dunbar/c482pa/MainWindow.fxml");
    }

    /** Saves the new product and returns to the main window when the Save button is pressed.
     * Generates a new Product object based off of the values in the text fields and passes them to the relevant
     * constructor, then adds the new product to the Inventory allProducts list.
     * @param event Event generated by pressing the Save button*/
    @FXML
    void onActionSaveAndReturn(ActionEvent event){
        try {
            int id = Integer.parseInt(idText.getText());
            String name = "fill";
            int inventory = 0;
            double price = 0;
            int max = 0;
            int min = 0;
            Product newProd;

            //Generates the name, stock, price, max, min, and min for the new product based off of the associated
            // text fields. Exception handling used to ensure valid inputs.
            try {
                name = nameText.getText();
                if (name.isEmpty()) {
                    throw new Exception();
                }
            } catch (Exception badName) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a name");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
                return;
            }

            try {
                inventory = Integer.parseInt(invText.getText());
                if (inventory < 0) {
                    throw new Exception();
                }
            } catch (NumberFormatException badValue) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid Inventory value. This includes whole numbers greater than or equal to zero.");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
                return;
            } catch (Exception badValue) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid Inventory value. This includes whole numbers greater than or equal to zero.");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
                return;
            }

            try {
                price = Double.parseDouble(priceText.getText());
                price = Math.floor(price * 100) / 100;  //Truncates price values to two decimal places.
                if (price < 0) {
                    throw new Exception();
                }
            } catch (NumberFormatException notADouble) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid Price. Price should be greater than or equal to zero and formatted \"0.00\" ");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
                return;
            } catch (Exception badValue) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid Price. Price should be greater than or equal to zero and formatted \"0.00\" ");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
                return;
            }

            try {
                max = Integer.parseInt(maxText.getText());
                min = Integer.parseInt(minText.getText());
                if (max < 0 || min < 0 || max < min) {
                    throw new Exception();
                }
            } catch (NumberFormatException notInt) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid values for max/min. This includes whole numbers greater than or equal to zero. Max cannot be less than min.");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
                return;
            } catch (Exception badValue) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid values for max/min. This includes whole numbers greater than or equal to zero. Max cannot be less than min.");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
                return;
            }
            if (inventory < min || inventory > max) {
                throw new Exception();
            }

            Inventory.addProduct(new Product(id, name, price, inventory, min, max));    //Generates the Product Object
            newProd = Inventory.getAllProducts().get(Inventory.getAllProducts().size() - 1);
            for (Part part : partHolder) {      //Populates the associatedParts list
                newProd.addAssociatedPart(part);
            }
            changeWindowButton(event, "/dunbar/c482pa/MainWindow.fxml");
        }
        catch (Exception badStock) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid inventory/ max and min combination. Ensure amount in stock is equal to or between max and min.");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
            return;
        }
    }


}
