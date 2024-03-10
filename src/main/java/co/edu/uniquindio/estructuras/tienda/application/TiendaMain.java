package co.edu.uniquindio.estructuras.tienda.application;

import java.io.IOException;

import co.edu.uniquindio.estructuras.tienda.utils.FxmlPerspective;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * JavaFX App
 */
public class TiendaMain extends Application {

	private static Stage stage;

	@Override
	public void start(Stage stage) throws IOException {
		TiendaMain.stage = stage;
		FxmlPerspective perspective = FxmlPerspective.loadPerspective("menuprincipal");
		Scene scene = new Scene(perspective.getPerspective());
		stage.setMinWidth(720);
		stage.setMinHeight(405);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

	public static Window getCurrentStage() {
		return stage;
	}

}