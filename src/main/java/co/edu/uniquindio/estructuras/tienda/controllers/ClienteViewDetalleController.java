package co.edu.uniquindio.estructuras.tienda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.ClienteViewDetalleLogicController;
import co.edu.uniquindio.estructuras.tienda.model.CarritoCompras;
import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.model.Venta;
import co.edu.uniquindio.estructuras.tienda.services.IClienteViewDetalle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class ClienteViewDetalleController implements IClienteViewDetalle, Initializable {

	@FXML
	private ImageView imgCliente;

	@FXML
	private TableColumn<CarritoCompras, String> colProductosCarrito, colSubtotalProductoCarrito;

	@FXML
	private Label lblDireccion, lblIdentificacion, lblNombre;

	@FXML
	private TableView<CarritoCompras> tableCarritos;

	@FXML
	private TableColumn<Venta, String> colProductosVentas, colFechaVentas, colHoraVentas, colTotalVentas;

	@FXML
	private TableView<Venta> tableVentas;

	private Runnable r;

	@Override
	public void setCliente(Cliente c) {
		ClienteViewDetalleLogicController.getInstance().setCliente(c);

	}

	@Override
	public void setCloseMethod(Runnable r) {
		this.r = r;
	}

	@FXML
	void cerrarEvent(ActionEvent event) {
		cerrarAction();
	}

	@FXML
	void editarEvent(ActionEvent event) {
		ClienteViewDetalleLogicController.getInstance().editarAction();
	}

	@FXML
	void eliminarEvent(ActionEvent event) {
		ClienteViewDetalleLogicController.getInstance().eliminarAction();
	}

	@FXML
	void eliminarCarritoEvent(ActionEvent event) {
		ClienteViewDetalleLogicController.getInstance().eliminarCarritoAction(tableCarritos);
	}

	@FXML
	void importarCarritoEvent(ActionEvent event) {
		ClienteViewDetalleLogicController.getInstance().importarCarritoAction(tableCarritos);
	}

	private void cerrarAction() {
		r.run();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClienteViewDetalleLogicController.getInstance().configValues(lblDireccion, lblIdentificacion, lblNombre,
				colProductosCarrito, colSubtotalProductoCarrito, colProductosVentas, colFechaVentas, colHoraVentas,
				colTotalVentas, tableVentas, tableCarritos, imgCliente);
	}

}
