package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.utils.ImgUtils;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class ClientViewLogicController {

	private SimpleObjectProperty<Cliente> clientProperty;

	public ClientViewLogicController() {
		clientProperty = new SimpleObjectProperty<Cliente>();
	}

	private static ClientViewLogicController instance;

	public static ClientViewLogicController getInstance() {
		if (instance == null)
			instance = new ClientViewLogicController();
		return instance;
	}

	public void clientePresionadoAction(Runnable openMethod) {
		openMethod.run();
	}

	public void setCliente(Cliente c) {
		clientProperty.setValue(c);
	}

	public void cargarLogica(Label lblIdentificacion, Label lblNombre, BorderPane root) {
		clientProperty.addListener((observableValue, oldValue, newValue) -> {
			actualizarInfo(lblIdentificacion, lblNombre, newValue, root);
		});
		actualizarInfo(lblIdentificacion, lblNombre, clientProperty.getValue(), root);
	}

	private void actualizarInfo(Label lblIdentificacion, Label lblNombre, Cliente newValue, BorderPane root) {
		if (newValue != null) {
			Platform.runLater(() -> {
				lblIdentificacion.setText(newValue.getIdentificacion());
				lblNombre.setText(newValue.getNombre());
				Image img = ImgUtils.cropSquareCenter(newValue.getImage(1024, 1024, true, true));
				root.setLeft(ImgUtils.createCircleWImage(img));
			});
		}
	}

}
