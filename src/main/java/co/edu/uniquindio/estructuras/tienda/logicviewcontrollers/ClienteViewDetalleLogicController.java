package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import java.time.format.DateTimeFormatter;

import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNuloException;
import co.edu.uniquindio.estructuras.tienda.logiccontrollers.ModelFactoryController;
import co.edu.uniquindio.estructuras.tienda.model.CarritoCompras;
import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.model.Venta;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class ClienteViewDetalleLogicController {

	private final SimpleObjectProperty<Cliente> clientProperty = new SimpleObjectProperty<Cliente>();

	private static ClienteViewDetalleLogicController instance;

	public static ClienteViewDetalleLogicController getInstance() {
		if (instance == null)
			instance = new ClienteViewDetalleLogicController();
		return instance;
	}

	public void setCliente(Cliente c) {
		clientProperty.setValue(c);
	}

	public void configValues(Label lblDireccion, Label lblIdentificacion, Label lblNombre,
			TableColumn<CarritoCompras, String> colProductosCarrito,
			TableColumn<CarritoCompras, String> colSubtotalProductoCarrito,
			TableColumn<Venta, String> colProductosVentas, TableColumn<Venta, String> colFechaVentas,
			TableColumn<Venta, String> colHoraVentas, TableColumn<Venta, String> colTotalVentas,
			TableView<Venta> tableVentas, TableView<CarritoCompras> tableCarritos, ImageView imgCliente) {
		configCliente(lblIdentificacion, lblNombre, lblDireccion, imgCliente, tableCarritos, tableVentas);
		configColCarrito(colProductosCarrito, colSubtotalProductoCarrito);
		configColVentas(colProductosVentas, colFechaVentas, colHoraVentas, colTotalVentas);

	}

	private void configColVentas(TableColumn<Venta, String> colProductosVentas,
			TableColumn<Venta, String> colFechaVentas, TableColumn<Venta, String> colHoraVentas,
			TableColumn<Venta, String> colTotalVentas) {
		colProductosVentas
				.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().obtenerProductosVendidosString()));
		colFechaVentas.setCellValueFactory(e -> new ReadOnlyStringWrapper(
				e.getValue().getFechaVenta().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));
		colHoraVentas.setCellValueFactory(e -> new ReadOnlyStringWrapper(
				e.getValue().getFechaVenta().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss"))));
		colTotalVentas.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getTotal() + ""));
	}

	private void configColCarrito(TableColumn<CarritoCompras, String> colProductosCarrito,
			TableColumn<CarritoCompras, String> colSubtotalProductoCarrito) {
		colProductosCarrito.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().obtenerProductosString()));
		colSubtotalProductoCarrito
				.setCellValueFactory(e -> new ReadOnlyStringWrapper((e.getValue().obtenerSubtotal() + "")));
	}

	private void configCliente(Label lblIdentificacion, Label lblNombre, Label lblDireccion, ImageView imgCliente,
			TableView<CarritoCompras> tableCarritos, TableView<Venta> tableVentas) {
		clientProperty.addListener((observableValue, oldValue, newValue) -> {
			actualizarInfo(lblIdentificacion, lblNombre, newValue, lblDireccion, imgCliente, tableCarritos,
					tableVentas);
		});
		actualizarInfo(lblIdentificacion, lblNombre, clientProperty.getValue(), lblDireccion, imgCliente, tableCarritos,
				tableVentas);
	}

	private void actualizarInfo(Label lblIdentificacion, Label lblNombre, Cliente newValue, Label lblDireccion,
			ImageView imgCliente, TableView<CarritoCompras> tableCarritos, TableView<Venta> tableVentas) {
		if (newValue != null) {
			lblDireccion.setText(newValue.getDireccion());
			lblIdentificacion.setText(newValue.getIdentificacion());
			lblNombre.setText(newValue.getNombre());
			imgCliente.setImage(newValue.getImage());
			tableCarritos.setItems(FXCollections.observableArrayList(newValue.getCarrito()));
			tableVentas.setItems(FXCollections.observableArrayList(newValue.getLstVentas()));
		}
	}

	public void importarCarritoAction(TableView<CarritoCompras> tableCarritos) {
		CarritoCompras item = tableCarritos.getSelectionModel().getSelectedItem();
		if (item == null) {
			new Alert(AlertType.WARNING, "Selecciona un carrito de compras").show();
			return;
		}
		CarritoCompras carritoActual = ModelFactoryController.getInstance().getCarrito();
		if (!carritoActual.estaVacio()) {
			ButtonType resultado = new Alert(AlertType.WARNING, "¿Deseas sobreescribir el carrito actual?",
					ButtonType.YES, ButtonType.NO).showAndWait().orElse(null);
			if (resultado != ButtonType.YES)
				return;
		}
		ModelFactoryController.getInstance().setCarrito(item);
		MenuPrincipalLogicController.getInstance().mostrarCarrito();
	}

	public void eliminarCarritoAction(TableView<CarritoCompras> tableCarritos) {
		CarritoCompras item = tableCarritos.getSelectionModel().getSelectedItem();
		if (item == null) {
			new Alert(AlertType.WARNING, "Selecciona un carrito de compras").show();
			return;
		}
		ButtonType resultado = new Alert(AlertType.WARNING, "¿Deseas eliminar el carrito seleccionado?", ButtonType.YES,
				ButtonType.NO).showAndWait().orElse(null);
		if (resultado != ButtonType.YES)
			return;
		try {
			Cliente nuevoCliente = ModelFactoryController.getInstance()
					.eliminarCarritoCliente(clientProperty.getValue(), item);
			clientProperty.setValue(null);
			clientProperty.setValue(nuevoCliente);
		} catch (ElementoNuloException | ElementoNoEncontradoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public void editarAction() {
		GestionClientesLogicController.getInstance().irAEditarCliente(clientProperty.getValue());
	}

	public void eliminarAction() {
		try {
			ModelFactoryController.getInstance().eliminarCliente(clientProperty.getValue());
			MenuPrincipalLogicController.getInstance().limpiarCentro();
			new Alert(AlertType.INFORMATION, "El cliente se ha eliminado con exito").show();
		} catch (ElementoNuloException | ElementoNoEncontradoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}
}
