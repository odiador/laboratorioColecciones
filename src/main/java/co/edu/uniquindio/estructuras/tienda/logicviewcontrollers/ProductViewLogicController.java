package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import co.edu.uniquindio.estructuras.tienda.logiccontrollers.ModelFactoryController;
import co.edu.uniquindio.estructuras.tienda.model.Producto;
import co.edu.uniquindio.estructuras.tienda.utils.ImgUtils;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class ProductViewLogicController {

	private SimpleObjectProperty<Producto> productoProperty;
	private SimpleIntegerProperty cantProperty;
	private int cantMax;

	public ProductViewLogicController() {
		productoProperty = new SimpleObjectProperty<Producto>();
		cantProperty = new SimpleIntegerProperty(0);
	}

	public void setProducto(Producto p) {
		Platform.runLater(() -> productoProperty.setValue(p));
	}

	public void cargarLabels(Label lblNombre, Label lblPrecio, Label lblStock, BorderPane root, TextField tfCantidad) {
		productoProperty.addListener((observable, oldValue, newValue) -> {
			if (newValue != null)
				setValues(lblNombre, lblPrecio, lblStock, newValue, root);
		});
		tfCantidad.textProperty().bind(cantProperty.map(cantidad -> cantidad != null ? cantidad + "" : "0"));
		cantProperty.addListener((observable, oldValue, newValue) -> {
			System.out.println(newValue);
		});
		if (productoProperty.getValue() != null)

			setValues(lblNombre, lblPrecio, lblStock, productoProperty.getValue(), root);
	}

	private void setValues(Label lblNombre, Label lblPrecio, Label lblStock, Producto producto, BorderPane root) {
		lblNombre.setText(producto.getNombre());
		lblPrecio.setText(String.format("$%.2f C/U", producto.getPrecio()));
		lblStock.setText(String.format("%d disponibles", producto.getCantidad()));
		cantMax = producto.getCantidad();
		root.setLeft(new ImageView(ImgUtils.cropNormal(producto.getImage(0, 80, true, true), 20)));
	}

	public void menosAction() {
		if (cantProperty.getValue() == 0)
			return;
		cantProperty.setValue(cantProperty.getValue() - 1);

	}

	public void masAction() {
		if (cantProperty.getValue() >= cantMax - 1)
			cantProperty.set(cantMax);
		else
			cantProperty.setValue(cantProperty.getValue() + 1);

	}

	public void ordenarAction() {
		ModelFactoryController.getInstance().agregarCarrito(cantProperty.getValue(), productoProperty.getValue());
	}
}
