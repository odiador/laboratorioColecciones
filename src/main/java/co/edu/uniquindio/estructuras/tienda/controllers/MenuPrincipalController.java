package co.edu.uniquindio.estructuras.tienda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.estructuras.tienda.logiccontrollers.ModelFactoryController;
import co.edu.uniquindio.estructuras.tienda.logicviewcontrollers.MenuPrincipalLogicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;

public class MenuPrincipalController implements Initializable {

	@FXML
	private SVGPath svgShoppingCard;

	@FXML
	private SVGPath svg1, svg2;

	@FXML
	private BorderPane loadingLayer, mainLayer, searchLayer;

	@FXML
	private TextField tfBusqueda;

	@FXML
	private ScrollPane scrollLoading;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MenuPrincipalLogicController.getInstance().cargarTransicionCargando(svg1, svg2);
		MenuPrincipalLogicController.getInstance().cargarAnimacionCargando(scrollLoading);
		MenuPrincipalLogicController.getInstance().cargarMenuCentral(mainLayer);
		MenuPrincipalLogicController.getInstance().inicializarPerspectivas();
		MenuPrincipalLogicController.getInstance().inicializarListeners(svgShoppingCard);
		ModelFactoryController.getInstance().cargarCarrito();

	}

	@FXML
	void buscarEvent(ActionEvent event) {
		buscarAction();
	}

	@FXML
	void clientesEvent(ActionEvent event) {
		clientesAction();
	}

	@FXML
	void helpEvent(ActionEvent event) {
		helpAction();
	}

	@FXML
	void productsEvent(ActionEvent event) {
		productsAction();
	}

	@FXML
	void profileEvent(ActionEvent event) {
		profileAction();
	}

	@FXML
	void shoppingCardEvent(ActionEvent event) {
		shoppingCardAction();
	}

	@FXML
	void ventasEvent(ActionEvent event) {
		ventasAction();
	}

	@FXML
	void cancelarEvent(ActionEvent event) {
		MenuPrincipalLogicController.getInstance().cancelarAction(searchLayer, tfBusqueda);
	}

	@FXML
	void buscarProcuctoEvent(ActionEvent event) {
		MenuPrincipalLogicController.getInstance().buscarProductoAction(searchLayer, tfBusqueda);
	}

	@FXML
	void buscarClienteEvent(ActionEvent event) {
		MenuPrincipalLogicController.getInstance().buscarClienteAction(searchLayer, tfBusqueda);
	}

	private void clientesAction() {
		MenuPrincipalLogicController.getInstance().irAClientes();
	}

	private void buscarAction() {
		MenuPrincipalLogicController.getInstance().mostrarCapaBuscar(searchLayer, tfBusqueda);
	}

	private void helpAction() {
		MenuPrincipalLogicController.getInstance().mostrarInfo();
	}

	private void productsAction() {
		MenuPrincipalLogicController.getInstance().irAProductos();
	}

	private void profileAction() {
	}

	private void shoppingCardAction() {
		MenuPrincipalLogicController.getInstance().mostrarOcultarCarrito();
	}

	private void ventasAction() {
		MenuPrincipalLogicController.getInstance().irAVentas();
	}
}
