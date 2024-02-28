package co.edu.uniquindio.estructuras.tienda.controllers;

import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;

public class ClientViewLogicController {

	private SimpleObjectProperty<Cliente> productoProperty;

	public ClientViewLogicController() {
		productoProperty = new SimpleObjectProperty<Cliente>();
	}

	private static ClientViewLogicController instance;

	public static ClientViewLogicController getInstance() {
		if (instance == null)
			instance = new ClientViewLogicController();
		return instance;
	}

	public void clientePresionadoAction() {
		System.out.println(productoProperty.getValue());
	}

	public void setCliente(Cliente c) {
		productoProperty.setValue(c);
	}

	public void cargarLogica(Label lblIdentificacion, Label lblNombre) {
		productoProperty.addListener((observableValue, oldValue, newValue) -> {
			actualizarInfo(lblIdentificacion, lblNombre, newValue);
		});
		actualizarInfo(lblIdentificacion, lblNombre, productoProperty.getValue());
	}

	private void actualizarInfo(Label lblIdentificacion, Label lblNombre, Cliente newValue) {
		if (newValue != null) {
			lblIdentificacion.setText(newValue.getIdentificacion());
			lblNombre.setText(newValue.getNombre());
		}
	}

}
