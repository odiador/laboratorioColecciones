package co.edu.uniquindio.estructuras.tienda.controllers;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.tienda.logiccontrollers.ModelFactoryController;
import co.edu.uniquindio.estructuras.tienda.model.Venta;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HistorialVentasController implements Initializable {

	@FXML
	private TableColumn<Venta, String> colCodigo, colFecha, colHora, colTotal;

	@FXML
	private TableView<Venta> tblVentas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colCodigo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCodigo()));
		colFecha.setCellValueFactory(e -> new ReadOnlyStringWrapper(
				e.getValue().getFechaVenta().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));
		colHora.setCellValueFactory(e -> new ReadOnlyStringWrapper(
				e.getValue().getFechaVenta().toLocalTime().format(DateTimeFormatter.ISO_TIME)));
		colTotal.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getTotal() + ""));
		tblVentas.setItems(FXCollections.observableList(ModelFactoryController.getInstance().getVentas()));
	}

}
