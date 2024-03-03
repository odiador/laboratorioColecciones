package co.edu.uniquindio.estructuras.tienda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.ClientViewLogicController;
import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.services.IClientViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ClientViewController implements IClientViewController, Initializable {

	@FXML
	private Button btnPrincipal;

	@FXML
	private Label lblIdentificacion, lblNombre;

	@FXML
	private BorderPane root;

	private ClientViewLogicController logica;

	private Runnable openMethod;

	public ClientViewController() {
		this.logica = new ClientViewLogicController();
	}

	@FXML
	void clientePresionadoEvent(ActionEvent event) {
		clientePresionadoAction();
	}

	private void clientePresionadoAction() {
		logica.clientePresionadoAction(openMethod);
	}

	@Override
	public void setCliente(Cliente c) {
		logica.setCliente(c);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logica.cargarLogica(lblIdentificacion, lblNombre, root);
	}

	@Override
	public void setOpenMethod(Runnable r) {
		this.openMethod = r;
	}

}
