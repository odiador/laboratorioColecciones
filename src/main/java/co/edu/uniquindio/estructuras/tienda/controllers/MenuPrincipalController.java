package co.edu.uniquindio.estructuras.tienda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;

public class MenuPrincipalController implements Initializable {

	@FXML
	private SVGPath svgShoppingCard;
	@FXML
	private SVGPath svg1, svg2;

	@FXML
	private BorderPane loadingLayer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MenuPrincipalLogicController.getInstance().cargarTransicionCargando(svg1, svg2);
	}

	@FXML
	void buscarEvent(ActionEvent event) {
		buscarAction();
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

	private void buscarAction() {
	}

	private void helpAction() {
	}

	private void productsAction() {
	}

	private void profileAction() {
	}

	private void shoppingCardAction() {

	}

	private void ventasAction() {
	}

	private MenuPrincipalController instance;

	public MenuPrincipalController getInstance() {
		return instance;
	}

	public MenuPrincipalController() {
		instance = this;
	}

}
