package co.edu.uniquindio.estructuras.tienda.controllers;

import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import javafx.beans.property.SimpleObjectProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RAMController {

	@Getter
	private final SimpleObjectProperty<Cliente> clienteActualProperty = new SimpleObjectProperty<Cliente>();

	private static RAMController instance;

	public static RAMController getInstance() {
		if (instance == null)
			instance = new RAMController();
		return instance;
	}
}
