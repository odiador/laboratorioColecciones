package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import java.time.format.DateTimeFormatter;

import co.edu.uniquindio.estructuras.tienda.model.CarritoCompras;
import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.model.Venta;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
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
			TableColumn<CarritoCompras, String> colCodigoCarrito,
			TableColumn<CarritoCompras, String> colTiposProductoCarrito, TableColumn<Venta, String> colCodigoVentas,
			TableColumn<Venta, String> colFechaVentas, TableColumn<Venta, String> colHoraVentas,
			TableColumn<Venta, String> colTotalVentas, TableView<Venta> tableVentas,
			TableView<CarritoCompras> tableCarritos, ImageView imgCliente) {
		configCliente(lblIdentificacion, lblNombre, lblDireccion, imgCliente);
		configColCarrito(colCodigoCarrito, colTiposProductoCarrito);
		colCodigoVentas.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCodigo()));
		colFechaVentas.setCellValueFactory(e -> new ReadOnlyStringWrapper(
				e.getValue().getFechaVenta().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));

	}

	private void configColCarrito(TableColumn<CarritoCompras, String> colCodigoCarrito,
			TableColumn<CarritoCompras, String> colTiposProductoCarrito) {
		colCodigoCarrito.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCodigo()));
		colTiposProductoCarrito.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCodigo()));
	}

	private void configCliente(Label lblIdentificacion, Label lblNombre, Label lblDireccion, ImageView imgCliente) {
		clientProperty.addListener((observableValue, oldValue, newValue) -> {
			actualizarInfo(lblIdentificacion, lblNombre, newValue, lblDireccion, imgCliente);
		});
		actualizarInfo(lblIdentificacion, lblNombre, clientProperty.getValue(), lblDireccion, imgCliente);
	}

	private void actualizarInfo(Label lblIdentificacion, Label lblNombre, Cliente newValue, Label lblDireccion,
			ImageView imgCliente) {
		if (newValue != null) {
			lblDireccion.setText(newValue.getDireccion());
			lblIdentificacion.setText(newValue.getIdentificacion());
			lblNombre.setText(newValue.getNombre());
			imgCliente.setImage(newValue.getImage());
		}
	}
}
