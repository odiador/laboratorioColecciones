package co.edu.uniquindio.estructuras.tienda.model;

import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarritoCompras {
	@NonNull
	@EqualsAndHashCode.Include
	private String codigo;
	@NonNull
	private HashSet<DetalleCarrito> lstDetalleCarritos;

}
