package co.edu.uniquindio.estructuras.tienda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.SingleClientSelecionLogicController;
import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.services.IClienteViewDetalle;
import co.edu.uniquindio.estructuras.tienda.services.ICloseableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class SingleClientSelecionController implements Initializable, ICloseableController, IClienteViewDetalle {

	@FXML
	private Button btnPrincipal;

	@FXML
	private Label lblIdentificacion;

	@FXML
	private Label lblNombre;

	@FXML
	private BorderPane root;

	private Runnable r;

	@FXML
	void clientePresionadoEvent(ActionEvent event) {
		r.run();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@Override
	public void setCloseMethod(Runnable r) {
		this.r = r;
	}

	@Override
	public void setCliente(Cliente c) {
		SingleClientSelecionLogicController.actualizarInfo(c, lblIdentificacion, lblNombre, root);
	}
}
