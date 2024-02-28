package co.edu.uniquindio.estructuras.tienda.controllers;

import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.services.IClientViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ClientViewController implements IClientViewController {

	@FXML
	private Button btnPrincipal;

	@FXML
	private Label lblIdentificacion, lblNombre;

	@FXML
	private BorderPane root;

	@FXML
	void clientePresionadoEvent(ActionEvent event) {
		clientePresionadoAction();
	}

	private void clientePresionadoAction() {
		ClientViewLogicController.getInstance().clientePresionadoAction();
	}

	@Override
	public void setCliente(Cliente c) {
		ClientViewLogicController.getInstance().setCliente(c);
	}

	public void cargarLogica(Label lblIdentificacion, Label lblNombre) {
		ClientViewLogicController.getInstance().cargarLogica(lblIdentificacion, lblNombre);
	}

}
