package co.edu.uniquindio.estructuras.tienda.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class GestionClientesController {

	@FXML
	private BorderPane root;

	@FXML
	private Button btnNuevo, btnActualizar, btnVer;

	@FXML
	void actualizarClienteEvent(ActionEvent event) {
		actualizarClienteAction();
	}

	@FXML
	void nuevoClienteEvent(ActionEvent event) {
		nuevoClienteAction();
	}

	@FXML
	void verClientesEvent(ActionEvent event) {

	}

	private void actualizarClienteAction() {
		GestionClientesLogicController.getInstance().irAActualizar(root, btnActualizar);
	}

	private void nuevoClienteAction() {
		GestionClientesLogicController.getInstance().irAAgregar(root, btnNuevo);
	}

}
