package co.edu.uniquindio.estructuras.tienda.controllers;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.AdicionProductoLogicController;
import co.edu.uniquindio.estructuras.tienda.services.ICloseableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AdicionProductoController implements ICloseableController {

	@FXML
	private ImageView imgviewCliente;

	@FXML
	private TextField tfCantidad;

	@FXML
	private TextField tfNombre;

	@FXML
	private TextField tfPrecio;

	private Runnable r;

	@FXML
	void agregarEvent(ActionEvent event) {
		AdicionProductoLogicController.getInstance().agregarAction(tfNombre.getText(), tfPrecio.getText(),
				tfCantidad.getText());
	}

	@FXML
	void cancelarEvent(ActionEvent event) {
		r.run();
	}

	@FXML
	void seleccionarImgEvent(ActionEvent event) {
		AdicionProductoLogicController.getInstance().seleccionarImgAction(imgviewCliente);
	}

	@Override
	public void setCloseMethod(Runnable r) {
		this.r = r;

	}
}
