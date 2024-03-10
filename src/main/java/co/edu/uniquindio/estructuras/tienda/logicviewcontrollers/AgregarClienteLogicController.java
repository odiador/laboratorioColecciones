package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import co.edu.uniquindio.estructuras.tienda.application.TiendaMain;
import co.edu.uniquindio.estructuras.tienda.exceptions.CampoInvalidoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.CampoVacioException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoDuplicadoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNuloException;
import co.edu.uniquindio.estructuras.tienda.logiccontrollers.ModelFactoryController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class AgregarClienteLogicController {
	public static AgregarClienteLogicController instance;
	private Image image;

	public static AgregarClienteLogicController getInstance() {
		if (instance == null)
			instance = new AgregarClienteLogicController();
		return instance;
	}

	public void agregarAction(String id, String direccion, String nombre) {
		try {
			ModelFactoryController.getInstance().agregarCliente(id, direccion, nombre, image);
			new Alert(AlertType.CONFIRMATION, String.format("El cliente %s ha sido registrado con éxito", nombre))
					.show();
		} catch (CampoVacioException | ElementoNuloException | ElementoDuplicadoException | CampoInvalidoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public void seleccionarImgAction(ImageView imgviewCliente) {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(TiendaMain.getCurrentStage());
		if (selectedFile != null) {
			try {
				InputStream targetStream = new DataInputStream(new FileInputStream(selectedFile));
				image = new Image(targetStream);
				imgviewCliente.setImage(image);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
	}

	public void quitarSeleccionImg() {
		image = null;
	}
}
