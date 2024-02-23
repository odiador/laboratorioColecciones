package co.edu.uniquindio.estructuras.tienda.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Tienda {
	@NonNull
	private String nombre, direccion, nit;
	@NonNull
	private TreeSet<Producto> treeProductos;
	@NonNull
	private LinkedList<Venta> historicoVentas;
	@NonNull
	private HashMap<String, Cliente> mapClientes;

}
