package co.edu.uniquindio.estructuras.tienda.logiccontrollers;

import java.util.function.Consumer;

import co.edu.uniquindio.estructuras.tienda.model.CarritoCompras;
import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import javafx.beans.property.SimpleObjectProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RAMController {

	private final SimpleObjectProperty<Cliente> clienteActualProperty = new SimpleObjectProperty<Cliente>();
	private final SimpleObjectProperty<CarritoCompras> carritoActualProperty = new SimpleObjectProperty<CarritoCompras>();

	private static RAMController instance;

	public static RAMController getInstance() {
		if (instance == null)
			instance = new RAMController();
		return instance;
	}

	public void setCarrito(CarritoCompras c) {
		carritoActualProperty.setValue(c);
	}

	public void addCarritoListener(Consumer<CarritoCompras> consumer) {
		carritoActualProperty.addListener((observable, oldValue, newValue) -> consumer.accept(newValue));
	}

	public void addClienteListener(Consumer<Cliente> consumer) {
		clienteActualProperty.addListener((observable, oldValue, newValue) -> consumer.accept(newValue));
	}
}
