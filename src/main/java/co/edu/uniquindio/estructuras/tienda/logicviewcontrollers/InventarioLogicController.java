package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import co.edu.uniquindio.estructuras.tienda.logiccontrollers.ModelFactoryController;
import co.edu.uniquindio.estructuras.tienda.model.Producto;
import co.edu.uniquindio.estructuras.tienda.services.IProductoController;
import co.edu.uniquindio.estructuras.tienda.utils.FxmlPerspective;
import javafx.application.Platform;
import javafx.scene.layout.VBox;

public class InventarioLogicController {
	private static InventarioLogicController instance;

	public static InventarioLogicController getInstance() {
		if (instance == null)
			instance = new InventarioLogicController();
		return instance;
	}

	private VBox vboxInventario;

	public void configLista(VBox vboxInventario) {
		this.vboxInventario = vboxInventario;
	}

	public void cargarDatos() {
		cargarDatos(ModelFactoryController.getInstance().getProductos());
	}

	public void cargarDatos(Collection<Producto> productos) {
		MenuPrincipalLogicController.getInstance().ejecutarProceso(() -> {

			Platform.runLater(() -> vboxInventario.getChildren().clear());
			productos.forEach(producto -> {
				try {
					FxmlPerspective perspDetail = FxmlPerspective.loadPerspective("product");
					IProductoController controller = (IProductoController) perspDetail.getController();
					controller.setProducto(producto);
					Platform.runLater(() -> {
						vboxInventario.getChildren().add(perspDetail.getPerspective());
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		});
	}

	public void ordenarCantidadAction() {
		ArrayList<Producto> lista = obtenerListaProductos();
		lista.sort((o1, o2) -> o1.compareTo(o2));
		cargarDatos(lista);
	}

	public void ordenarAlfabeticoAction() {
		ArrayList<Producto> lista = obtenerListaProductos();
		lista.sort((o1, o2) -> {
			return o1.getNombre().compareTo(o2.getNombre());
		});
		cargarDatos(lista);
	}

	private ArrayList<Producto> obtenerListaProductos() {
		ArrayList<Producto> lista = ModelFactoryController.getInstance().getProductos().stream()
				.collect(Collectors.toCollection(ArrayList::new));
		return lista;
	}

	public void mostrarInventarioAction() {
		cargarDatos();
	}
}
