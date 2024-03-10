package co.edu.uniquindio.estructuras.tienda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.InventarioLogicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class InventarioController implements Initializable {

	@FXML
	private BorderPane root;

	@FXML
	private VBox vboxInventario;

	@FXML
	void mostrarInventarioEvent(ActionEvent event) {
		InventarioLogicController.getInstance().mostrarInventarioAction();
	}

	@FXML
	void ordenarAlfabeticoEvent(ActionEvent event) {
		InventarioLogicController.getInstance().ordenarAlfabeticoAction();
	}

	@FXML
	void ordenarCantidadEvent(ActionEvent event) {
		InventarioLogicController.getInstance().ordenarCantidadAction();
	}

	@FXML
	void agregarEvent(ActionEvent event) {
		InventarioLogicController.getInstance().irAAgregarEvent();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		InventarioLogicController.getInstance().configLista(vboxInventario, root);
	}
}
