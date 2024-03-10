package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import co.edu.uniquindio.estructuras.tienda.exceptions.CampoInvalidoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.CampoVacioException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoDuplicadoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNuloException;
import co.edu.uniquindio.estructuras.tienda.logiccontrollers.ModelFactoryController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class AgregarClienteLogicController {
	public static AgregarClienteLogicController instance;

	public static AgregarClienteLogicController getInstance() {
		if (instance == null)
			instance = new AgregarClienteLogicController();
		return instance;
	}

	public void agregarAction(String id, String direccion, String nombre) {
		try {
			ModelFactoryController.getInstance().agregarCliente(id, direccion, nombre,new Image("imagen.jpg"));
			new Alert(AlertType.CONFIRMATION, String.format("El cliente %s ha sido registrado con éxito", nombre)).show();
		} catch (CampoVacioException | ElementoNuloException | ElementoDuplicadoException | CampoInvalidoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}
}
