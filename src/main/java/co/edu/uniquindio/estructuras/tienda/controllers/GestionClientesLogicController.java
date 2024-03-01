package co.edu.uniquindio.estructuras.tienda.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.services.IAddClientController;
import co.edu.uniquindio.estructuras.tienda.services.IClientViewController;
import co.edu.uniquindio.estructuras.tienda.services.IClienteViewDetalle;
import co.edu.uniquindio.estructuras.tienda.utils.FxmlPerspective;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GestionClientesLogicController {

	private int page = 0;

	private static GestionClientesLogicController instance;
	private VBox listaClientes;
	private Label lblVista;
	private VBox barraClientes;
	private List<Cliente> listaClientesModel;
	private BorderPane root;

	public static GestionClientesLogicController getInstance() {
		if (instance == null)
			instance = new GestionClientesLogicController();
		return instance;
	}

	public void cargarInfoPaneles(VBox listaClientes, Label lblVista, VBox barraClientes, BorderPane root) {
		this.listaClientes = listaClientes;
		this.lblVista = lblVista;
		this.barraClientes = barraClientes;
		this.root = root;
	}

	public void moverPagina() {
		MenuPrincipalLogicController.getInstance().ejecutarProceso(() -> {
			Platform.runLater(() -> {
				listaClientes.getChildren().clear();
				for (int i = 5 * page; i < 5 + 5 * page && i < listaClientesModel.size(); i++) {
					try {
						FxmlPerspective persp = FxmlPerspective.loadPerspective("cliente");
						IClientViewController controller = (IClientViewController) persp.getController();
						Cliente cliente = listaClientesModel.get(i);
						controller.setCliente(cliente);
						controller.setOpenMethod(() -> {
							openClientMethod(cliente);
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

	private void openClientMethod(Cliente cliente) {

		try {
			FxmlPerspective perspDetail = FxmlPerspective.loadPerspective("clienteDetail");
			IClienteViewDetalle controllerDetail = (IClienteViewDetalle) perspDetail.getController();
			controllerDetail.setCliente(cliente);
			controllerDetail.setCloseMethod(() -> {
				Platform.runLater(() -> {
					if (root.getLeft() == barraClientes) {
						root.setLeft(null);
						root.setCenter(barraClientes);
					}
				});
			});
			Platform.runLater(() -> {
				root.setCenter(perspDetail.getPerspective());
				if (root.getLeft() != barraClientes)
					root.setLeft(barraClientes);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void regenerarLista() {
		listaClientesModel = ModelFactoryController.getInstance().getClientes().values().stream()
				.collect(Collectors.toCollection(ArrayList::new));
		listaClientesModel.sort((c1, c2) -> c1.getNombre().compareTo(c2.getNombre()));
	}

	public void moverAtras() {
		if (page <= 0)
			return;
		page--;
		if (page * 5 + 5 >= listaClientesModel.size()) {
			page++;
			return;
		}
		moverPagina();
	}

	public void moverAdelante() {
		page++;
		if (listaClientesModel.size() <= page * 5) {
			page--;
			return;
		}
		moverPagina();
	}

	public void nuevoClienteAction() {
		MenuPrincipalLogicController.getInstance().ejecutarProceso(() -> {
			try {

				System.out.println("GestionClientesLogicController.nuevoClienteAction()");
				FxmlPerspective perspective = FxmlPerspective.loadPerspective("agregarCliente");
				IAddClientController controller = (IAddClientController) perspective.getController();
				final Node nodeCenter = root.getCenter();
				final Node nodeLeft = root.getLeft();
				controller.configurarVolver(() -> {
					Platform.runLater(() -> {
						root.setLeft(nodeLeft);
						root.setCenter(nodeCenter);
					});
				});
				Platform.runLater(() -> {
					root.setCenter(perspective.getPerspective());
					root.setLeft(null);
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
