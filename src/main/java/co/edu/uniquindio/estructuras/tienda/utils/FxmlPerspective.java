package co.edu.uniquindio.estructuras.tienda.utils;

import java.io.IOException;

import co.edu.uniquindio.estructuras.tienda.application.TiendaMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FxmlPerspective {
	private Object controller;
	private Parent perspective;

	public static FxmlPerspective loadPerspective(String fxml) throws IOException {
		System.out.println(fxml);
		FXMLLoader fxmlLoader = new FXMLLoader(
				TiendaMain.class.getResource("/co/edu/uniquindio/estructuras/tienda/fxml/" + fxml + ".fxml"));
		Parent load = fxmlLoader.load();
		return new FxmlPerspective(fxmlLoader.getController(), load);
	}
}
