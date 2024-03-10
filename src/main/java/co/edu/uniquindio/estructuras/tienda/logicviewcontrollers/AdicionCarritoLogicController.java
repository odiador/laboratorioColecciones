package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import co.edu.uniquindio.estructuras.tienda.exceptions.CantidadSeleccionadaNoEncajaException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNuloException;
import co.edu.uniquindio.estructuras.tienda.logiccontrollers.ModelFactoryController;
import co.edu.uniquindio.estructuras.tienda.model.Producto;
import co.edu.uniquindio.estructuras.tienda.utils.InputConfigurations;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AdicionCarritoLogicController {
	private int cantMax;
	private static AdicionCarritoLogicController instance;
	private Producto producto;
	private TextField tf;

	public static AdicionCarritoLogicController getInstance() {
		if (instance == null)
			instance = new AdicionCarritoLogicController();
		return instance;
	}

	public void configTfValues(TextField tf) {
		this.tf = tf;
		tf.setText("0");
	}

	public void menosAction() {
		try {
			int num = Integer.parseInt(tf.getText());
			tf.setText((num - 1) + "");
		} catch (NumberFormatException e) {
			tf.setText("0");
		}
	}

	public void masAction() {
		try {
			int num = Integer.parseInt(tf.getText());
			tf.setText((num + 1) + "");
		} catch (NumberFormatException e) {
			tf.setText("0");
		}
	}

	public void agregarAction() {
		int num = 0;
		try {
			num = Integer.parseInt(tf.getText());
		} catch (NumberFormatException e) {
		}
		try {
			ButtonType resultado = new Alert(AlertType.INFORMATION, "¿Deseas agregar el producto al carrito?",
					ButtonType.YES, ButtonType.CANCEL).showAndWait().orElse(null);
			if (resultado != ButtonType.YES)
				return;
			ModelFactoryController.getInstance().agregarDetalleCarrito(num, producto);
			MenuPrincipalLogicController.getInstance().mostrarCarrito();
		} catch (CantidadSeleccionadaNoEncajaException | ElementoNuloException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}

	}

	public void cargarInfo(Producto p, Label lblNombre, Label lblPrecio, Label lblStock, ImageView imgProducto) {
		this.producto = p;
		this.cantMax = p.getCantidad();
		InputConfigurations.setAsIntegerTextfield(tf, cantMax);
		lblNombre.setText(p.getNombre());
		lblPrecio.setText(String.format("$%.2f C/U", p.getPrecio()));
		lblStock.setText(String.format("%d disponibles", p.getCantidad()));
		imgProducto.setImage(p.getImage(1000, 0, true, true));
	}

}
