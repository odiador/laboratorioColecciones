package co.edu.uniquindio.estructuras.tienda.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GestionClientesController {

	@FXML
	private VBox listaClientes;

	@FXML
	private BorderPane root;

	@FXML
	void nuevoClienteEvent(ActionEvent event) {
		nuevoClienteAction();
	}

	@FXML
	void verClientesEvent(ActionEvent event) {

	}

	private void nuevoClienteAction() {
		GestionClientesLogicController.getInstance().irAAgregar(root);
	}

}
