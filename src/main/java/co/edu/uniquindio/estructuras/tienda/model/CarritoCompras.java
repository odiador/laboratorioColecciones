package co.edu.uniquindio.estructuras.tienda.model;

import java.util.HashSet;

import co.edu.uniquindio.estructuras.tienda.exceptions.NoCantidadException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CarritoCompras {
	@NonNull
	@EqualsAndHashCode.Include
	private String codigo;
	@NonNull
	private HashSet<DetalleCarrito> lstDetalleCarritos;

	public void agregarDetalleCarrito(DetalleCarrito detalleCarrito) throws NoCantidadException {
		if (detalleCarrito.getCantSeleccionada() <= 0)
			throw new NoCantidadException("La cantidad seleccionada no puede ser 0");
		DetalleCarrito newDetalle = DetalleCarrito.builder().producto(detalleCarrito.getProducto()).build();
		if (lstDetalleCarritos.add(detalleCarrito))
			return;
		for (DetalleCarrito eachDetalle : lstDetalleCarritos) {
			if (eachDetalle.equals(detalleCarrito)) {
				int sum = eachDetalle.getCantSeleccionada() + detalleCarrito.getCantSeleccionada();
				newDetalle.setCantSeleccionada(sum);
				lstDetalleCarritos.remove(newDetalle);
				lstDetalleCarritos.add(newDetalle);
				return;
			}
		}
	}
}
