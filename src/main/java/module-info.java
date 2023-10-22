module dunbar.c482pa {
    requires javafx.controls;
    requires javafx.fxml;
            
                            

    exports dunbar.c482pa.Model;
    opens dunbar.c482pa.Model to javafx.fxml;
    exports dunbar.c482pa.Controllers;
    opens dunbar.c482pa.Controllers to javafx.fxml;
}