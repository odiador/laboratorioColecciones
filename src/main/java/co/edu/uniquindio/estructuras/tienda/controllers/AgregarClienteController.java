package co.edu.uniquindio.estructuras.tienda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.AgregarClienteLogicController;
import co.edu.uniquindio.estructuras.tienda.services.IAddClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AgregarClienteController implements IAddClientController, Initializable {

	@FXML
	private Button btnSeleccionar;

	@FXML
	private TextField tfIdentificacion, tfDireccion, tfNombre;

	@FXML
	private ImageView imgviewCliente;

	private Runnable runnable;

	@Override
	public void configurarVolver(Runnable runnable) {
		this.runnable = runnable;
	}

	@FXML
	void agregarEvent(ActionEvent event) {
		AgregarClienteLogicController.getInstance().agregarAction(tfIdentificacion.getText(), tfDireccion.getText(),
				tfNombre.getText());
	}

	@FXML
	void volverEvent(ActionEvent event) {
		volverAction();
	}

	private void volverAction() {
		runnable.run();
	}

	@FXML
	void seleccionarImgEvent(ActionEvent event) {
		AgregarClienteLogicController.getInstance().seleccionarImgAction(imgviewCliente);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AgregarClienteLogicController.getInstance().quitarSeleccionImg();		
	}
}
