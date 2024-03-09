package co.edu.uniquindio.estructuras.tienda.controllers;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.ProductViewLogicController;
import co.edu.uniquindio.estructuras.tienda.model.DetalleCarrito;
import co.edu.uniquindio.estructuras.tienda.model.Producto;
import co.edu.uniquindio.estructuras.tienda.services.IDetalleCarritoController;
import co.edu.uniquindio.estructuras.tienda.services.IProductoController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;

public class ProductViewController implements IProductoController, IDetalleCarritoController {
	private ProductViewLogicController logicController;

	@FXML
	private Label lblNombre, lblPrecio, lblStock, lblHover;

	@FXML
	private SVGPath svgHover;

	@FXML
	private BorderPane layerAgregar, grayLayer, imagePane;

	@FXML
	private StackPane mainRoot;

	@FXML
	void mouseClickedEvent(MouseEvent event) {
		logicController.irAAgregarAction();
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
	private BorderPane root;

	@Override
	public void setProducto(Producto p) {
		if (logicController == null)
			logicController = new ProductViewLogicController();
		logicController.cargarProductoLabels(lblNombre, lblPrecio, lblStock, imagePane, lblHover, svgHover);
		logicController.setProducto(p);
	}

	@Override
	public void setDetalleCarrito(DetalleCarrito d) {
		if (logicController == null)
			logicController = new ProductViewLogicController();
		logicController.cargarDetailLabels(lblNombre, lblPrecio, lblStock, imagePane, lblHover, svgHover);
		logicController.setDetalleCarrito(d);
	}

}
