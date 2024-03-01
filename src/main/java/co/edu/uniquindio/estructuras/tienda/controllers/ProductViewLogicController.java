package co.edu.uniquindio.estructuras.tienda.controllers;

import co.edu.uniquindio.estructuras.tienda.model.Producto;
import co.edu.uniquindio.estructuras.tienda.utils.ImgUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class ProductViewLogicController {

	private SimpleObjectProperty<Producto> productoProperty;

	public ProductViewLogicController() {
		productoProperty = new SimpleObjectProperty<Producto>();
	}

	public void setProducto(Producto p) {
		productoProperty.setValue(p);
	}

	public void cargarLabels(Label lblNombre, Label lblPrecio, Label lblStock, Button btnOrdenar, BorderPane root) {
		productoProperty.addListener((observable, oldValue, newValue) -> {
			if (newValue != null)
				setValues(lblNombre, lblPrecio, lblStock, newValue, btnOrdenar, root);
		});
		if (productoProperty.getValue() != null)
			setValues(lblNombre, lblPrecio, lblStock, productoProperty.getValue(), btnOrdenar, root);
	}

	private void setValues(Label lblNombre, Label lblPrecio, Label lblStock, Producto producto, Button btnOrdenar,
			BorderPane root) {
		lblNombre.setText(producto.getNombre());
		lblPrecio.setText(String.format("$%.2f C/U", producto.getPrecio()));
		lblStock.setText(String.format("%d disponibles", producto.getCantidad()));
		btnOrdenar.setDisable(producto.getCantidad() <= 0);
		root.setLeft(new ImageView(ImgUtils.cropNormal(producto.getImage(0, 80, true, true), 20)));
	}
}
