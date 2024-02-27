package co.edu.uniquindio.estructuras.tienda.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Venta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NonNull
	@EqualsAndHashCode.Include
	private String codigo;
	@NonNull
	private Cliente cliente;
	@NonNull
	private List<DetalleVenta> lstDetalleVentas;

	public Map<Producto, Integer> verProductosCantidad() {
		Map<Producto, Integer> map = new HashMap<Producto, Integer>();
		lstDetalleVentas
				.forEach(detalle -> map.put(detalle.getProducto(), map.getOrDefault(detalle.getProducto(), 0) + 1));
		return map;
	}
}
