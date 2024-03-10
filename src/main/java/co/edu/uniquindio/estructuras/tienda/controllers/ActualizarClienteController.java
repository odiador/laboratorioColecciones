package co.edu.uniquindio.estructuras.tienda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.ActualizarClienteLogicController;
import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.services.IClienteViewDetalle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ActualizarClienteController implements IClienteViewDetalle, Initializable {
	@FXML
	private Button btnSeleccionar;

	@FXML
	private Label lblIdentificacion;

	@FXML
	private TextField tfDireccion, tfNombre;

	@FXML
	private ImageView imgviewCliente;

	private Runnable r;

	@FXML
	void actualizarEvent(ActionEvent event) {
		ActualizarClienteLogicController.getInstance().actualizarAction(lblIdentificacion.getText(),
				tfDireccion.getText(), tfNombre.getText(), r);
	}

	@FXML
	void volverEvent(ActionEvent event) {
		r.run();
	}

	@FXML
	void seleccionarImgEvent(ActionEvent event) {
		ActualizarClienteLogicController.getInstance().seleccionarImgAction(imgviewCliente);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ActualizarClienteLogicController.getInstance().quitarSeleccionImg();
	}

	@Override
	public void setCliente(Cliente c) {
		ActualizarClienteLogicController.getInstance().setCliente(c, lblIdentificacion, tfDireccion, tfNombre,
				imgviewCliente);
	}

	@Override
	public void setCloseMethod(Runnable r) {
		this.r = r;
	}

}
