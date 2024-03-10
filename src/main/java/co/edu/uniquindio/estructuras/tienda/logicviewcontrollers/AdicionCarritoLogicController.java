package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import co.edu.uniquindio.estructuras.tienda.exceptions.CantidadSeleccionadaNoEncajaException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNuloException;
import co.edu.uniquindio.estructuras.tienda.logiccontrollers.ModelFactoryController;
import co.edu.uniquindio.estructuras.tienda.model.Producto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AdicionCarritoLogicController {
	private int cantMax;
	private static AdicionCarritoLogicController instance;
	private Producto producto;
	private TextField tf;
	private static ChangeListener<String> listener;

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
			ModelFactoryController.getInstance().agregarDetalleCarrito(num, producto);
		} catch (CantidadSeleccionadaNoEncajaException | ElementoNuloException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}

	}

	public void cargarInfo(Producto p, Label lblNombre, Label lblPrecio, Label lblStock, ImageView imgProducto) {
		this.producto = p;
		this.cantMax = p.getCantidad();
		setAsIntegerTextfield(tf, cantMax);
		lblNombre.setText(p.getNombre());
		lblPrecio.setText(String.format("$%.2f C/U", p.getPrecio()));
		lblStock.setText(String.format("%d disponibles", p.getCantidad()));
		imgProducto.setImage(p.getImage(1000, 0, true, true));
	}

	private static void setAsIntegerTextfield(TextField tf, int value) {
		if (listener != null)
			tf.textProperty().removeListener(listener);
		listener = new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.isEmpty())
					return;
				try {
					int num = Integer.parseInt(tf.getText());
					if (num > value) {
						observable.removeListener(this);
						tf.setText(value + "");
						observable.addListener(this);
					} else if (num < 0) {
						throw new Exception();
					} else {
						observable.removeListener(this);
						tf.setText(num + "");
						observable.addListener(this);
					}
				} catch (Exception e) {
					observable.removeListener(this);
					tf.setText(oldValue);
					observable.addListener(this);
				}
			}
		};
		tf.textProperty().addListener(listener);
	}

}
