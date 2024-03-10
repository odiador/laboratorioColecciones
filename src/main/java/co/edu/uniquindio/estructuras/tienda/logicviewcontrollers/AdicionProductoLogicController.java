package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

import co.edu.uniquindio.estructuras.tienda.application.TiendaMain;
import co.edu.uniquindio.estructuras.tienda.logiccontrollers.ModelFactoryController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import lombok.NonNull;

public class AdicionProductoLogicController {
	private static AdicionProductoLogicController instance;

	public static AdicionProductoLogicController getInstance() {
		if (instance == null)
			instance = new AdicionProductoLogicController();
		return instance;
	}

	private @NonNull Image image;

	public void agregarAction(String nombre, String precio, String cantidad) {
		try {
			ModelFactoryController.getInstance().agregarProducto(UUID.randomUUID().toString(), nombre.trim(), precio,
					cantidad, image);
			new Alert(AlertType.CONFIRMATION, String.format("El producto %s ha sido registrado con éxito", nombre))
					.show();
		} catch (Exception e) {
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
}
