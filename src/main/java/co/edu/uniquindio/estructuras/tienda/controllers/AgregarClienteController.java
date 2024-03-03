package co.edu.uniquindio.estructuras.tienda.controllers;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.AgregarClienteLogicController;
import co.edu.uniquindio.estructuras.tienda.services.IAddClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AgregarClienteController implements IAddClientController {

	@FXML
	private Button btnAgregar;

	@FXML
	private TextField tfIdentificacion, tfDireccion, tfNombre;

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

}
