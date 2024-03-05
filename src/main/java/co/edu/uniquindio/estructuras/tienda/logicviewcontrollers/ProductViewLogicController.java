package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import java.io.IOException;

import co.edu.uniquindio.estructuras.tienda.model.Producto;
import co.edu.uniquindio.estructuras.tienda.services.ICloseableController;
import co.edu.uniquindio.estructuras.tienda.services.IProductoController;
import co.edu.uniquindio.estructuras.tienda.utils.FxmlPerspective;
import co.edu.uniquindio.estructuras.tienda.utils.ImgUtils;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ProductViewLogicController {

	private SimpleObjectProperty<Producto> productoProperty;

	public ProductViewLogicController() {
		productoProperty = new SimpleObjectProperty<Producto>();
	}

	public void setProducto(Producto p) {
		Platform.runLater(() -> productoProperty.setValue(p));
	}

	public void cargarLabels(Label lblNombre, Label lblPrecio, Label lblStock, BorderPane root) {
		productoProperty.addListener((observable, oldValue, newValue) -> {
			if (newValue != null)
				setValues(lblNombre, lblPrecio, lblStock, newValue, root);
		});

		if (productoProperty.getValue() != null)
			setValues(lblNombre, lblPrecio, lblStock, productoProperty.getValue(), root);
	}

	private void setValues(Label lblNombre, Label lblPrecio, Label lblStock, Producto producto, BorderPane root) {
		lblNombre.setText(producto.getNombre());
		lblPrecio.setText(String.format("$%.2f C/U", producto.getPrecio()));
		lblStock.setText(String.format("%d disponibles", producto.getCantidad()));
		root.setCenter(new ImageView(ImgUtils.cropNormal(producto.getImage(0, 80, true, true), 20)));
	}

	public void unhoverAction(Pane layerAgregar) {
		FadeTransition fade = new FadeTransition(Duration.millis(150), layerAgregar);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.playFromStart();
	}

	public void hoverAction(Pane layerAgregar) {
		FadeTransition fade = new FadeTransition(Duration.millis(150), layerAgregar);
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.playFromStart();
	}

	public void irAAgregarAction() {
		try {
			FxmlPerspective perspective = FxmlPerspective.loadPerspective("addCarritoCant");
			((IProductoController) perspective.getController()).setProducto(productoProperty.getValue());
			((ICloseableController) perspective.getController()).setCloseMethod(() -> {
				InventarioLogicController.getInstance().irAInventario();
			});
			MenuPrincipalLogicController.getInstance().cambiarPerspectiva(perspective);
		} catch (IOException e) {
		}
	}
}
