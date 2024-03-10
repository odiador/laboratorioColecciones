package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import java.io.IOException;

import co.edu.uniquindio.estructuras.tienda.logiccontrollers.ModelFactoryController;
import co.edu.uniquindio.estructuras.tienda.logiccontrollers.RAMController;
import co.edu.uniquindio.estructuras.tienda.model.CarritoCompras;
import co.edu.uniquindio.estructuras.tienda.services.ICloseableController;
import co.edu.uniquindio.estructuras.tienda.services.IDetalleCarritoController;
import co.edu.uniquindio.estructuras.tienda.services.ISeleccionClientesController;
import co.edu.uniquindio.estructuras.tienda.services.ISeleccionClientesController.TipoMetodo;
import co.edu.uniquindio.estructuras.tienda.utils.FxmlPerspective;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CarritoLogicController {
	private static CarritoLogicController instance;

	public static CarritoLogicController getInstance() {
		if (instance == null)
			instance = new CarritoLogicController();
		return instance;
	}

	public void agregarListenerCarrito(VBox vboxCarrito, Label lblCarritoVacio) {
		RAMController.getInstance().addCarritoListener(carrito -> {
			MenuPrincipalLogicController.getInstance().ejecutarProceso(() -> {
				Platform.runLater(() -> vboxCarrito.getChildren().clear());
				if (carrito != null && !carrito.estaVacio()) {
					lblCarritoVacio.setDisable(true);
					lblCarritoVacio.setOpacity(0);
					agregarDetalles(carrito, vboxCarrito);
				} else {
					lblCarritoVacio.setDisable(false);
					lblCarritoVacio.setOpacity(1);
				}
			});
		});
	}

	public void agregarDetalles(CarritoCompras carrito, VBox vboxCarrito) {
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

	public void vaciarCarrito() {
		ModelFactoryController.getInstance().vaciarCarrito();
	}

	public void comprarAction() {
		try {
			if (ModelFactoryController.getInstance().carritoEstaVacio()) {
				new Alert(AlertType.WARNING, "El carrito esta vacío").show();
				return;
			}
			FxmlPerspective perspective = FxmlPerspective.loadPerspective("seleccionCliente");
			ISeleccionClientesController iSeleccionClientesController = (ISeleccionClientesController) perspective
					.getController();
			ICloseableController iCloseableController = (ICloseableController) perspective.getController();
			iSeleccionClientesController.setTipoMetodo(TipoMetodo.COMPRAR);
			iCloseableController.setCloseMethod(() -> {
				MenuPrincipalLogicController.getInstance().limpiarCentro();
			});
			MenuPrincipalLogicController.getInstance().cambiarPerspectiva(perspective);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void almacenarCarritoAction() {
		try {
			if (ModelFactoryController.getInstance().carritoEstaVacio()) {
				new Alert(AlertType.WARNING, "El carrito esta vacío").show();
				return;
			}
			FxmlPerspective perspective = FxmlPerspective.loadPerspective("seleccionCliente");
			ISeleccionClientesController iSeleccionClientesController = (ISeleccionClientesController) perspective
					.getController();
			ICloseableController iCloseableController = (ICloseableController) perspective.getController();
			iSeleccionClientesController.setTipoMetodo(TipoMetodo.GUARDAR_CARRITO);
			iCloseableController.setCloseMethod(() -> {
				MenuPrincipalLogicController.getInstance().limpiarCentro();
			});
			MenuPrincipalLogicController.getInstance().cambiarPerspectiva(perspective);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
