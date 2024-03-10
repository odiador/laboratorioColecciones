package co.edu.uniquindio.estructuras.tienda.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
	public Venta(Cliente cliente, CarritoCompras carrito) {
		this.codigo = UUID.randomUUID().toString();
		this.cliente = cliente;
		this.fechaVenta = LocalDateTime.now();
		this.lstDetalleVentas = carrito.getLstDetalleCarritos().stream()
				.map(detalleCarrito -> DetalleVenta.builder().cantVendida(detalleCarrito.getCantSeleccionada())
						.producto(detalleCarrito.getProducto()).build())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public double getTotal() {
		double total = 0;
		for (DetalleVenta detalleVenta : lstDetalleVentas)
			total += detalleVenta.calcPrecioParcial();

		return total;
	}
	
	public String obtenerProductosVendidosString () {
		StringBuilder sb = new StringBuilder(); 
		Iterator<DetalleVenta> iterator = lstDetalleVentas.iterator();
		while(iterator.hasNext()) {
			DetalleVenta detalleAux= iterator.next();
			sb.append(detalleAux.getProducto().getNombre());
			if(iterator.hasNext()) {
				sb.append(", ");
			}
			
		}
		return sb.toString();
	}
}
