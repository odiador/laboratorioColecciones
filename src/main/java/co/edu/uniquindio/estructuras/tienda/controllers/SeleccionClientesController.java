package co.edu.uniquindio.estructuras.tienda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.ClientSelectionLogicController;
import co.edu.uniquindio.estructuras.tienda.services.ICloseableController;
import co.edu.uniquindio.estructuras.tienda.services.ISeleccionClientesController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SeleccionClientesController implements Initializable, ISeleccionClientesController, ICloseableController {
	@FXML
	private Label lblVista;

	@FXML
	private VBox listaClientes;

	@FXML
	private BorderPane root;

	private TipoMetodo tipoMetodo;

	private Runnable r;

	@FXML
	void cerrarEvent(ActionEvent event) {
		r.run();
	}

	@FXML
	void derechaEvent(ActionEvent event) {
		ClientSelectionLogicController.getInstance().moverAdelante();
	}

	@FXML
	void izquierdaEvent(ActionEvent event) {
		ClientSelectionLogicController.getInstance().moverAtras();
	}

	@FXML
	void seleccionarEvent(ActionEvent event) {
		ClientSelectionLogicController.getInstance().seleccionarAction(getTipoMetodo());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientSelectionLogicController.getInstance().cargarInfoPaneles(listaClientes, lblVista);
		ClientSelectionLogicController.getInstance().regenerarLista();
		ClientSelectionLogicController.getInstance().actualizarPagina();
	}

	@Override
	public void setTipoMetodo(TipoMetodo tipoMetodo) {
		this.tipoMetodo = tipoMetodo;
	}

	@Override
	public TipoMetodo getTipoMetodo() {
		return tipoMetodo;
	}

	@Override
	public void setCloseMethod(Runnable r) {
		this.r = r;
	}

}
