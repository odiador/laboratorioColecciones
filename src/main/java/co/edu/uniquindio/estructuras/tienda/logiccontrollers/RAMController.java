package co.edu.uniquindio.estructuras.tienda.logiccontrollers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import co.edu.uniquindio.estructuras.tienda.model.CarritoCompras;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RAMController {

	private static RAMController instance;
	private List<Consumer<CarritoCompras>> lstConsumers = new ArrayList<Consumer<CarritoCompras>>();

	public static RAMController getInstance() {
		if (instance == null)
			instance = new RAMController();
		return instance;
	}

	public void actualizarCarrito(CarritoCompras c) {
		lstConsumers.forEach(consumer -> consumer.accept(c));
	}

	public void addCarritoListener(Consumer<CarritoCompras> consumer) {
		lstConsumers.add(consumer);
	}

}
