module co.edu.uniquindio.estructuras.tienda {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.estructuras.tienda to javafx.fxml;
    exports co.edu.uniquindio.estructuras.tienda;
}
