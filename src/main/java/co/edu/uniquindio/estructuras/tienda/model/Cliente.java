package co.edu.uniquindio.estructuras.tienda.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Builder
public class Cliente {

	@EqualsAndHashCode.Include
	@NonNull
	private String identificacion;
	@NonNull
	private String nombre, direccion;
	@NonNull
	private ArrayList<Venta> lstVentas;
}
