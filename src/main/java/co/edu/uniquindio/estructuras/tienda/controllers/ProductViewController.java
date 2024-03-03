package co.edu.uniquindio.estructuras.tienda.controllers;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.ProductViewLogicController;
import co.edu.uniquindio.estructuras.tienda.model.Producto;
import co.edu.uniquindio.estructuras.tienda.services.IProductoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class ProductViewController implements IProductoController {
	private ProductViewLogicController logicController;

	@FXML
	private Label lblNombre, lblPrecio, lblStock;

	@FXML
	private BorderPane layerAgregar, grayLayer, imagePane;

	@FXML
	private StackPane mainRoot;

	@FXML
	void mouseClickedEvent(MouseEvent event) {
		logicController.ordenarAction();
	}

	@FXML
	void mouseEnteredEvent(MouseEvent event) {
		logicController.hoverAction(layerAgregar);
		logicController.hoverAction(grayLayer);
	}

	@FXML
	void mouseExitedEvent(MouseEvent event) {
		logicController.unhoverAction(layerAgregar);
		logicController.unhoverAction(grayLayer);
	}

	@FXML
	void masEvent(ActionEvent event) {
		logicController.masAction();
	}

	@FXML
	void menosEvent(ActionEvent event) {
		logicController.menosAction();
	}

	@FXML
	private BorderPane root;

	@Override
	public void setProducto(Producto p) {
		if (logicController == null)
			cargarLogica();
		logicController.setProducto(p);
	}

	public void cargarLogica() {
		logicController = new ProductViewLogicController();
		logicController.cargarLabels(lblNombre, lblPrecio, lblStock, imagePane);
	}

}
