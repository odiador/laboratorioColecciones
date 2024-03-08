package co.edu.uniquindio.estructuras.tienda.model;

import java.util.HashSet;

import co.edu.uniquindio.estructuras.tienda.exceptions.CantidadSeleccionadaNoEncajaException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNoEncontradoException;
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

	public void agregarDetalleCarrito(DetalleCarrito detalleCarrito) throws CantidadSeleccionadaNoEncajaException {

		if (lstDetalleCarritos.add(detalleCarrito))
			return;
		for (DetalleCarrito eachDetalle : lstDetalleCarritos) {
			if (eachDetalle.equals(detalleCarrito)) {
				int sum = eachDetalle.getCantSeleccionada() + detalleCarrito.getCantSeleccionada();
				DetalleCarrito newDetalle = DetalleCarrito.builder().producto(detalleCarrito.getProducto())
						.cantSeleccionada(sum).build();
				lstDetalleCarritos.remove(newDetalle);
				lstDetalleCarritos.add(newDetalle);
				return;
			}
		}
	}
	
	public void eliminarDetalleCarrito(DetalleCarrito detallerCarrito) throws ElementoNoEncontradoException {
		if(lstDetalleCarritos.remove(detallerCarrito)) {
			return;
		}else {
			throw new ElementoNoEncontradoException("No se ha encontrado el detalle a eliminar");
		}
	}
}
