package co.edu.uniquindio.estructuras.tienda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.GestionClientesLogicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GestionClientesController implements Initializable {

	@FXML
	private Label lblVista;

	@FXML
	private VBox barraClientes, listaClientes;

	@FXML
	private BorderPane root;

	@FXML
	void derechaEvent(ActionEvent event) {
		GestionClientesLogicController.getInstance().moverAdelante();
	}

	@FXML
	void izquierdaEvent(ActionEvent event) {
		GestionClientesLogicController.getInstance().moverAtras();
	}

	@FXML
	void nuevoClienteEvent(ActionEvent event) {
		nuevoClienteAction();
	}

	private void nuevoClienteAction() {
		GestionClientesLogicController.getInstance().nuevoClienteAction();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GestionClientesLogicController.getInstance().cargarInfoPaneles(listaClientes, lblVista, barraClientes, root);
	}

}
