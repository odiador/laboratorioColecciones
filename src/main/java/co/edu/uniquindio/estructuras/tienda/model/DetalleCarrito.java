package co.edu.uniquindio.estructuras.tienda.model;

import java.io.Serializable;

import co.edu.uniquindio.estructuras.tienda.exceptions.CantidadSeleccionadaNoEncajaException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class DetalleCarrito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@NonNull
	private Producto producto;
	private int cantSeleccionada;

	public double calcPrecioParcial() {
		return cantSeleccionada * producto.getPrecio();
	}

	@Builder
	public DetalleCarrito(@NonNull Producto producto, int cantSeleccionada)
			throws CantidadSeleccionadaNoEncajaException {
		super();
		this.producto = producto;
		this.cantSeleccionada = cantSeleccionada;
		if (cantSeleccionada < 0)
			throw new CantidadSeleccionadaNoEncajaException("La cantidad seleccionada no puede ser menor a 0");
		if (cantSeleccionada == 0)
			throw new CantidadSeleccionadaNoEncajaException("La cantidad seleccionada no puede ser 0");
		if (cantSeleccionada > producto.getCantidad())
			throw new CantidadSeleccionadaNoEncajaException(
					"La cantidad seleccionada no puede mayor a la que hay en stock");
	}
}
