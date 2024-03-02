package co.edu.uniquindio.estructuras.tienda.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Venta implements Serializable {
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

	private LocalDateTime fechaVenta;

	public Map<Producto, Integer> verProductosCantidad() {
		Map<Producto, Integer> map = new HashMap<Producto, Integer>();
		lstDetalleVentas
				.forEach(detalle -> map.put(detalle.getProducto(), map.getOrDefault(detalle.getProducto(), 0) + 1));
		return map;
	}

	@Builder
	public Venta(String codigo, Cliente cliente, List<DetalleVenta> lstDetalleVentas) {
		this.codigo = codigo;
		this.cliente = cliente;
		this.lstDetalleVentas = lstDetalleVentas;
		this.fechaVenta = LocalDateTime.now();
	}
}
