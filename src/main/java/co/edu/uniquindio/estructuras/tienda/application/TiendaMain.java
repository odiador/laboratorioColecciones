package co.edu.uniquindio.estructuras.tienda.application;

import java.io.IOException;

import co.edu.uniquindio.estructuras.tienda.model.Producto;
import co.edu.uniquindio.estructuras.tienda.services.IProductoController;
import co.edu.uniquindio.estructuras.tienda.utils.FxmlPerspective;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class TiendaMain extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		FxmlPerspective perspective = FxmlPerspective.loadPerspective("menuprincipal");
//		IProductoController controller = (IProductoController) perspective.getController();
//		Producto producto = Producto.builder().codigo("1234").precio(10000).cantidad(10).nombre("Arroz con Huevo").build();
//		producto.setImagen(new Image("imagen.jpg"));
//		controller.setProducto(producto);
		scene = new Scene(perspective.getPerspective());
		stage.setMinWidth(720);
		stage.setMinHeight(405);
		stage.setScene(scene);
		stage.show();
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(FxmlPerspective.loadPerspective(fxml).getPerspective());
	}

	public static void main(String[] args) {
		launch();
	}

}