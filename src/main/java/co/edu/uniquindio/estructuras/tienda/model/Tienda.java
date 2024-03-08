package co.edu.uniquindio.estructuras.tienda.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoDuplicadoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNuloException;
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

	public boolean agregarProducto(Producto producto) throws ElementoNuloException {
		if (producto != null) {
			return agregarProductoAux(producto);

		} else {
			throw new ElementoNuloException("El producto es nulo");
		}
	}

	private boolean agregarProductoAux(Producto producto) {
		return treeProductos.add(producto);
	}

	public boolean eliminarProducto(Producto producto) throws ElementoNuloException, ElementoNoEncontradoException {
		if (producto != null) {
			return eliminarProductoAux(producto);
		} else {
			throw new ElementoNuloException("El producto es nulo");
		}
	}

	private boolean eliminarProductoAux(Producto producto) throws ElementoNoEncontradoException {
		if (!treeProductos.remove(producto)) {
			throw new ElementoNoEncontradoException("No se ha encontrado la venta");
		}
		return true;
	}

	public Producto buscarProducto(String codigo) throws ElementoNoEncontradoException {
		Iterator<Producto> iterador = treeProductos.iterator();
		while (iterador.hasNext()) {
			Producto produ = iterador.next();
			if (produ.getCodigo().equals(codigo))
				;
			return produ;
		}
		throw new ElementoNoEncontradoException("Producto No Enontrado");
	}

	public void actualizarProducto(Producto producto) throws ElementoNuloException, ElementoNoEncontradoException {
		if (producto != null) {
			actualizarProductoAux(producto);
		} else {
			throw new ElementoNuloException("EL producto no existe");

		}
	}

	private void actualizarProductoAux(Producto producto) throws ElementoNoEncontradoException {
		Iterator<Producto> iterador = treeProductos.iterator();
		while (iterador.hasNext()) {
			Producto product = iterador.next();
			if (product.equals(producto)) {
				treeProductos.remove(product);
				treeProductos.add(producto);
				return;
			}
		}
		throw new ElementoNoEncontradoException("No se ha encontrado el producto a actualizar");
	}

	public String leerProuctos() {
		return treeProductos.toString();
	}

	public boolean agregarVenta(Venta venta) throws ElementoNuloException, ElementoDuplicadoException {
		if (venta != null) {
			return agregarVentaAux(venta);
		} else {
			throw new ElementoNuloException("La venta es nula");
		}
	}

	private boolean agregarVentaAux(Venta venta) throws ElementoDuplicadoException {
		if (!historicoVentas.contains(venta)) {
			return historicoVentas.add(venta);
		} else {
			throw new ElementoDuplicadoException("La venta ya se encuentra registrada");
		}
	}

	public boolean eliminarVenta(Venta venta) throws ElementoNuloException, ElementoNoEncontradoException {
		if (venta != null) {
			return eliminarVentaAux(venta);
		} else {
			throw new ElementoNuloException("La venta es nula");
		}
	}

	private boolean eliminarVentaAux(Venta venta) throws ElementoNoEncontradoException {

		if (!historicoVentas.remove(venta)) {
			throw new ElementoNoEncontradoException("No se ha encontrado la venta");
		}
		return true;
	}

	public Venta buscarVenta(String codigo) throws ElementoNoEncontradoException {
		Iterator<Venta> iterador = historicoVentas.iterator();
		while (iterador.hasNext()) {
			Venta ventaAux = iterador.next();
			if (ventaAux.getCodigo().equals(codigo)) {
				return ventaAux;
			}
		}
		throw new ElementoNoEncontradoException("No se ha encontrado la venta");
	}

	public void actualizarVenta(Venta venta) {
		Iterator<Venta> iterador = historicoVentas.iterator();
		while (iterador.hasNext()) {
			Venta ventaAux = iterador.next();
			if (venta.equals(ventaAux)) {
				historicoVentas.remove(ventaAux);
				historicoVentas.add(venta);
			}
		}
	}

	public String leerVenta() {
		return historicoVentas.toString();
	}

	public void agregarCliente(Cliente cliente) throws ElementoNuloException, ElementoDuplicadoException {
		if (cliente != null) {
			agregarClienteAux(cliente);
		}
		throw new ElementoNuloException("El cliente es nulo");
	}

	private void agregarClienteAux(Cliente cliente) throws ElementoDuplicadoException {
		if (!mapClientes.containsKey(cliente.getIdentificacion())) {
			mapClientes.put(cliente.getIdentificacion(), cliente);
			return;
		}
		throw new ElementoDuplicadoException("El cliente ya existe en la tienda");

	}

	public void eliminarCliente(Cliente cliente) throws ElementoNuloException, ElementoNoEncontradoException {
		if (cliente != null) {
			eliminarClienteAux(cliente);
		}
		throw new ElementoNuloException("El cliente a eliminar es nulo");
	}

	private void eliminarClienteAux(Cliente cliente) throws ElementoNoEncontradoException {
		if (mapClientes.containsKey(cliente.getIdentificacion())) {
			mapClientes.remove(cliente.getIdentificacion());
			return;
		}
		throw new ElementoNoEncontradoException("No se ha encontrado el cliente a eliminar");

	}

	public Cliente buscarCliente(String cedula) throws ElementoNoEncontradoException {

		Cliente cliente = mapClientes.get(cedula);
		if (cliente == null) {
			throw new ElementoNoEncontradoException("El cliente no a sido encontrado");
		}
		return cliente;
	}

	public void actualizarCliente(Cliente cliente) throws ElementoNuloException, ElementoNoEncontradoException {
		if (cliente != null) {
			actualizarClienteAux(cliente);
		}
		throw new ElementoNuloException("El cliente a actualizar es nulo");
	}

	private void actualizarClienteAux(Cliente cliente) throws ElementoNoEncontradoException {
		Cliente clienteAux = mapClientes.get(cliente.getIdentificacion());
		if (clienteAux != null) {
			mapClientes.remove(clienteAux.getIdentificacion());
			mapClientes.put(cliente.getIdentificacion(), cliente);
			return;
		}
		throw new ElementoNoEncontradoException("No se ha encontrado el cliente a actualizar");

	}

	public String leerClientes() {
		return mapClientes.toString();
	}

}
