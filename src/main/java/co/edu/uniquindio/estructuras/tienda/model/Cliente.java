package co.edu.uniquindio.estructuras.tienda.model;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uniquindio.estructuras.tienda.services.Imagenable;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Builder

public class Cliente implements Imagenable ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@NonNull
	private String identificacion;
	@NonNull
	private String nombre, direccion;
	@NonNull
	private ArrayList<Venta> lstVentas;
	private byte[] imgBytes;

	public void setImagen(Image image) throws IOException {
		imgBytes = Imagenable.getImageBytes(image);
	}

	@Override
	public byte[] getImgBytes() {
		return imgBytes;
	}
}
