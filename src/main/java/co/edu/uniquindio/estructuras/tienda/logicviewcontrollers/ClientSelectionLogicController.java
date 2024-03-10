package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.estructuras.tienda.logiccontrollers.ModelFactoryController;
import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.services.IClienteViewDetalle;
import co.edu.uniquindio.estructuras.tienda.services.ICloseableController;
import co.edu.uniquindio.estructuras.tienda.services.ISeleccionClientesController.TipoMetodo;
import co.edu.uniquindio.estructuras.tienda.utils.FxmlPerspective;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ClientSelectionLogicController {
	private int page = 0;
	private VBox listaClientes;
	private Label lblVista;
	private ArrayList<Cliente> listaClientesModel;
	private Cliente clienteSeleccionado;
	private static ClientSelectionLogicController instance;
	private Parent perspSeleccionada;

	public static ClientSelectionLogicController getInstance() {
		if (instance == null)
			instance = new ClientSelectionLogicController();
		return instance;
	}

	public void cargarInfoPaneles(VBox listaClientes, Label lblVista) {
		this.listaClientes = listaClientes;
		this.lblVista = lblVista;
	}

	public void regenerarLista() {
		listaClientesModel = ModelFactoryController.getInstance().getListClientes();
		listaClientesModel.sort((c1, c2) -> c1.getNombre().compareTo(c2.getNombre()));
		clienteSeleccionado = null;
	}

	public void moverAtras() {
		if (page <= 0)
			return;
		page--;
		if (page * 5 + 5 >= listaClientesModel.size()) {
			page++;
			return;
		}
		actualizarPagina();
	}

	public void moverAdelante() {
		page++;
		if (listaClientesModel.size() <= page * 5) {
			page--;
			return;
		}
		actualizarPagina();
	}

	public void actualizarPagina() {
		MenuPrincipalLogicController.getInstance().ejecutarProceso(() -> {
			Platform.runLater(() -> {
				listaClientes.getChildren().clear();
				if (listaClientesModel.size() == 0) {
					Platform.runLater(() -> lblVista.setText("0 - 0 de 0"));
					return;
				}
				for (int i = 5 * page; i < 5 + 5 * page && i < listaClientesModel.size(); i++) {
					try {
						FxmlPerspective persp = FxmlPerspective.loadPerspective("clienteSeleccion");
						final Cliente cliente = listaClientesModel.get(i);
						ICloseableController controller = (ICloseableController) persp.getController();
						IClienteViewDetalle controllerCliente = (IClienteViewDetalle) persp.getController();
						controllerCliente.setCliente(cliente);
						controller.setCloseMethod(() -> {
							this.clienteSeleccionado = cliente;
							if (perspSeleccionada != null)
								perspSeleccionada.setDisable(false);
							perspSeleccionada = persp.getPerspective();
							perspSeleccionada.setDisable(true);
						});
						Platform.runLater(() -> listaClientes.getChildren().add(persp.getPerspective()));
					} catch (IOException e) {
					}
				}
			});
			int numFinal = 5 * page + 5 <= listaClientesModel.size() ? 5 * page + 5 : listaClientesModel.size();
			Platform.runLater(() -> lblVista
					.setText(String.format("%d - %d de %d", 5 * page + 1, numFinal, listaClientesModel.size())));
		});

	}

	public void seleccionarAction(TipoMetodo tipoMetodo) {
		if (clienteSeleccionado == null) {
			new Alert(AlertType.WARNING, "Recuerda seleccionar un cliente").show();
			return;
		}
		switch (tipoMetodo) {
		case COMPRAR:
			comprarProductos();
			break;
		case GUARDAR_CARRITO:
			guardarCarrito();
			break;
		}
	}

	private void comprarProductos() {
		try {
			ModelFactoryController.getInstance().agregarVenta(ModelFactoryController.getInstance().getCarrito(),
					clienteSeleccionado);
			MenuPrincipalLogicController.getInstance().limpiarCentro();
			new Alert(AlertType.INFORMATION,
					String.format("La venta se ha agregado con exito a %s", clienteSeleccionado.getNombre())).show();
		} catch (Exception e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	private void guardarCarrito() {
		try {
			ModelFactoryController.getInstance()
					.agregarCarritoCliente(ModelFactoryController.getInstance().getCarrito(), clienteSeleccionado);
			MenuPrincipalLogicController.getInstance().limpiarCentro();
			new Alert(AlertType.INFORMATION, String.format("El carrito se ha guardado con exito en el cliente %s",
					clienteSeleccionado.getNombre())).show();

		} catch (Exception e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

}
