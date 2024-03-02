package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import co.edu.uniquindio.estructuras.tienda.exceptions.CampoVacioException;
import co.edu.uniquindio.estructuras.tienda.logiccontrollers.ModelFactoryController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AgregarClienteLogicController {
	public static AgregarClienteLogicController instance;

	public static AgregarClienteLogicController getInstance() {
		if (instance == null)
			instance = new AgregarClienteLogicController();
		return instance;
	}

	public void agregarAction(String id, String direccion, String nombre) {
		try {
			ModelFactoryController.getInstance().agregarCliente(id, direccion, nombre);
		} catch (CampoVacioException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}
}
