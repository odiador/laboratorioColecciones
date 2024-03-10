package co.edu.uniquindio.estructuras.tienda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.CarritoLogicController;
import co.edu.uniquindio.estructuras.tienda.services.ICloseableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class CarritoController implements ICloseableController, Initializable {
	@FXML
	private Label lblCarritoVacio;

	@FXML
	private SVGPath svgShoppingCard;

	@FXML
	private VBox vboxCarrito;

	private Runnable r;

	@FXML
	void cerrarEvent(ActionEvent event) {
		cerrarAction();
	}

	@FXML
	void almacenarCarritoEvent(ActionEvent event) {
		CarritoLogicController.getInstance().almacenarCarritoAction();
	}

	@FXML
	void comprarEvent(ActionEvent event) {
		CarritoLogicController.getInstance().comprarAction();
	}

	@FXML
	void vaciarCarritoEvent(ActionEvent event) {
		CarritoLogicController.getInstance().vaciarCarrito();
	}

	private void cerrarAction() {
		r.run();
	}

	@Override
	public void setCloseMethod(Runnable r) {
		this.r = r;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CarritoLogicController.getInstance().agregarListenerCarrito(vboxCarrito, lblCarritoVacio);
	}

}
