package co.edu.uniquindio.estructuras.tienda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleCarrito {

	@EqualsAndHashCode.Include
	@NonNull
	private Producto producto;
	private int cantSeleccionada;

	public double calcPrecioParcial() {
		return cantSeleccionada * producto.getPrecio();
	}
}
