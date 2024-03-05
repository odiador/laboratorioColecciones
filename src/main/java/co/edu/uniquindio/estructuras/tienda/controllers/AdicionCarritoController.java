package co.edu.uniquindio.estructuras.tienda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.AdicionCarritoLogicController;
import co.edu.uniquindio.estructuras.tienda.model.Producto;
import co.edu.uniquindio.estructuras.tienda.services.ICloseableController;
import co.edu.uniquindio.estructuras.tienda.services.IProductoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AdicionCarritoController implements IProductoController, Initializable, ICloseableController {

	@FXML
	private ImageView imgProducto;

	@FXML
	private Label lblNombre, lblPrecio, lblStock;

	@FXML
	private TextField tfCantidad;

	private Runnable r;

	@FXML
	void agregarEvent(ActionEvent event) {
		AdicionCarritoLogicController.getInstance().agregarAction();
	}

	@FXML
	void cancelarEvent(ActionEvent event) {
		r.run();
	}

	@FXML
	void masEvent(ActionEvent event) {
		AdicionCarritoLogicController.getInstance().masAction();
	}

	@FXML
	void menosEvent(ActionEvent event) {
		AdicionCarritoLogicController.getInstance().menosAction();
	}

	@Override
	public void setProducto(Producto p) {
		AdicionCarritoLogicController.getInstance().cargarInfo(p, lblNombre, lblPrecio, lblStock, imgProducto);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AdicionCarritoLogicController.getInstance().configTfValues(tfCantidad);
	}

	@Override
	public void setCloseMethod(Runnable r) {
		this.r = r;
	}

}
