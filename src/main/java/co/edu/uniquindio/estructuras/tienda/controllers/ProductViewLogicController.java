package co.edu.uniquindio.estructuras.tienda.controllers;

import co.edu.uniquindio.estructuras.tienda.model.Producto;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
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
		root.setLeft(new ImageView(cropNormal(producto.getImage(0, 80, true, true), 20)));
	}

	public static Image cropSquareCenter(Image img, double r) {
		double d = Math.min(img.getWidth(), img.getHeight());
		double x = (d - img.getWidth()) / 2;
		double y = (d - img.getHeight()) / 2;
		Canvas canvas = new Canvas(d, d);
		GraphicsContext g = canvas.getGraphicsContext2D();
		g.fillRoundRect(0, 0, d, d, r, r);
		g.setGlobalBlendMode(BlendMode.SRC_ATOP);
		g.drawImage(img, x, y);
		return canvas.snapshot(null, null);
	}

	public static Image cropNormal(Image img, double r) {
		Canvas canvas = new Canvas(img.getWidth(), img.getHeight());
		GraphicsContext g = canvas.getGraphicsContext2D();
		g.fillRoundRect(0, 0, img.getWidth(), img.getHeight(), r, r);
		g.setGlobalBlendMode(BlendMode.SRC_ATOP);
		g.drawImage(img, 0, 0);
		return canvas.snapshot(null, null);
	}
}
