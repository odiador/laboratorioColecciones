package co.edu.uniquindio.estructuras.tienda.model;

import java.io.IOException;
import java.io.Serializable;

import co.edu.uniquindio.estructuras.tienda.services.Imagenable;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Producto implements Comparable<Producto>, Imagenable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NonNull
	@EqualsAndHashCode.Include
	private String codigo;
	private String nombre;
	private double precio;
	private int cantidad;
	@ToString.Exclude
	private byte[] imgBytes;

	public void setImagen(Image image) throws IOException {
		imgBytes = Imagenable.getImageBytes(image);
	}

	@Override
	public int compareTo(Producto o) {
		return codigo.compareTo(o.codigo);
	}

	@Override
	public byte[] getImgBytes() {
		return imgBytes;
	}

	public boolean verificarCantidad(int cantSeleccionada) {
		return this.cantidad >= cantSeleccionada;
	}

	public void venderCantidad(int cant) {
		this.cantidad -= cant;
	}
}
