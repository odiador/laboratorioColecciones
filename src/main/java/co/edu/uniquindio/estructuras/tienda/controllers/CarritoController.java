package co.edu.uniquindio.estructuras.tienda.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.tienda.logiccontrollers.RAMController;
import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.MenuPrincipalLogicController;
import co.edu.uniquindio.estructuras.tienda.model.CarritoCompras;
import co.edu.uniquindio.estructuras.tienda.services.ICloseableController;
import co.edu.uniquindio.estructuras.tienda.services.IDetalleCarritoController;
import co.edu.uniquindio.estructuras.tienda.utils.FxmlPerspective;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class CarritoController implements ICloseableController, Initializable {
	@FXML
	private Label lblCarritoVacio;

	@FXML
	private SVGPath svgShoppingCard;

	@FXML
	private VBox vboxCarrito;

	private Runnable r;

	@FXML
	void cerrarEvent(ActionEvent event) {
		cerrarAction();
	}

	@FXML
	void almacenarCarritoEvent(ActionEvent event) {

	}

	@FXML
	void comprarEvent(ActionEvent event) {

	}

	@FXML
	void vaciarCarritoEvent(ActionEvent event) {
		vaciarCarritoAction();
	}

	private void vaciarCarritoAction() {
		lblCarritoVacio.setDisable(true);
		lblCarritoVacio.setOpacity(0);
	}

	private void cerrarAction() {
		r.run();
	}

	@Override
	public void setCloseMethod(Runnable r) {
		this.r = r;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RAMController.getInstance().addCarritoListener(carrito -> {
			MenuPrincipalLogicController.getInstance().ejecutarProceso(() -> {
				Platform.runLater(() -> vboxCarrito.getChildren().clear());
				if (carrito != null && !carrito.estaVacio()) {
					vaciarCarritoAction();
					agregarDetalles(carrito);
				} else {
					lblCarritoVacio.setDisable(false);
					lblCarritoVacio.setOpacity(1);
				}
			});
		});
	}

	private void agregarDetalles(CarritoCompras carrito) {
		carrito.getLstDetalleCarritos().forEach(detalleCarrito -> {
			try {
				FxmlPerspective perspDetail = FxmlPerspective.loadPerspective("product");
				IDetalleCarritoController controller = (IDetalleCarritoController) perspDetail.getController();
				controller.setDetalleCarrito(detalleCarrito);
				Platform.runLater(() -> {
					vboxCarrito.getChildren().add(perspDetail.getPerspective());
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
