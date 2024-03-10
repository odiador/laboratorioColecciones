package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.utils.ImgUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class SingleClientSelecionLogicController {

	public static void actualizarInfo(Cliente c, Label lblIdentificacion, Label lblNombre, BorderPane root) {
		if (c == null)
			return;
		lblIdentificacion.setText(c.getIdentificacion());
		lblNombre.setText(c.getNombre());
		Image img = ImgUtils.cropSquareCenter(c.getImage(1024, 1024, true, true));
		root.setLeft(ImgUtils.createCircleWImage(img));
	}
}
