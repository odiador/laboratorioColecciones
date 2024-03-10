package co.edu.uniquindio.estructuras.tienda.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

import co.edu.uniquindio.estructuras.tienda.exceptions.CantidadSeleccionadaNoEncajaException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
public class CarritoCompras implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NonNull
	@EqualsAndHashCode.Include
	private String codigo;
	@NonNull
	@Default
	private HashSet<DetalleCarrito> lstDetalleCarritos = new HashSet<DetalleCarrito>();

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
		if (lstDetalleCarritos.remove(detallerCarrito))
			return;
		throw new ElementoNoEncontradoException("No se ha encontrado el detalle a eliminar");
	}

	public boolean estaVacio() {
		return lstDetalleCarritos.isEmpty();
	}

	public double obtenerSubtotal() {
		double sub = 0.0;
		Iterator<DetalleCarrito> iterator = lstDetalleCarritos.iterator();
		while (iterator.hasNext()) {
			DetalleCarrito detalleAux = iterator.next();
			sub += (detalleAux.getCantSeleccionada() * detalleAux.getProducto().getPrecio());
		}
		return sub;
	}

	public String obtenerProductosString() {
		Iterator<DetalleCarrito> it = lstDetalleCarritos.iterator();
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			DetalleCarrito detalle = it.next();
			sb.append(detalle.getProducto().getNombre());
			if (it.hasNext())
				sb.append(", ");
		}
		return sb.toString();
	}
}
