package co.edu.uniquindio.estructuras.tienda.model;

import java.io.IOException;

import co.edu.uniquindio.estructuras.tienda.services.Imagenable;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto implements Comparable<Producto>, Imagenable {
	@NonNull
	@EqualsAndHashCode.Include
	private String codigo;
	@NonNull
	private String nombre;
	private double precio;
	private int cantidad;
	private byte[] imgBytes;

	public void setImagen(Image image) throws IOException {
		imgBytes = Imagenable.getImageBytes(image);
	}

	@Override
	public int compareTo(Producto o) {
		return cantidad - o.cantidad;
	}

	@Override
	public byte[] getImgBytes() {
		return imgBytes;
	}
}
