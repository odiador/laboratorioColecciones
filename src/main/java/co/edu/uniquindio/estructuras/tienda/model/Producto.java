package co.edu.uniquindio.estructuras.tienda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto implements Comparable<Producto> {
	@NonNull
	@EqualsAndHashCode.Include
	private String codigo;
	@NonNull
	private String nombre;
	private double precio;
	private int cantidad;

	@Override
	public int compareTo(Producto o) {
		return cantidad - o.cantidad;
	}
}
