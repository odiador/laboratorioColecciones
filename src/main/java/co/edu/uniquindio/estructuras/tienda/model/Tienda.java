package co.edu.uniquindio.estructuras.tienda.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import co.edu.uniquindio.estructuras.tienda.exceptions.CarritoNoFuncionaException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoDuplicadoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNuloException;
import co.edu.uniquindio.estructuras.tienda.exceptions.VentaNoFuncionaException;
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
		if (producto != null)
			return treeProductos.add(producto);
		throw new ElementoNuloException("El producto es nulo");
	}

	public boolean eliminarProducto(Producto producto) throws ElementoNuloException, ElementoNoEncontradoException {
		if (producto != null)
			return eliminarProductoAux(producto);
		throw new ElementoNuloException("El producto es nulo");
	}

	private boolean eliminarProductoAux(Producto producto) throws ElementoNoEncontradoException {
		if (!treeProductos.remove(producto))
			throw new ElementoNoEncontradoException("No se ha encontrado la venta");
		return true;
	}

	public Producto buscarProducto(String codigo) throws ElementoNoEncontradoException {
		Iterator<Producto> iterador = treeProductos.iterator();
		while (iterador.hasNext()) {
			Producto produ = iterador.next();
			if (produ.getCodigo().equals(codigo))
				return produ;
		}
		throw new ElementoNoEncontradoException("Producto No Enontrado");
	}

	public void actualizarProducto(Producto producto) throws ElementoNuloException, ElementoNoEncontradoException {
		if (producto == null)
			throw new ElementoNuloException("EL producto no existe");
		actualizarProductoAux(producto);
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

	public void agregarVenta(Venta venta) throws ElementoNuloException, ElementoDuplicadoException,
			VentaNoFuncionaException, ElementoNoEncontradoException {
		if (venta == null)
			throw new ElementoNuloException("La venta es nula");
		if (historicoVentas.contains(venta))
			new ElementoDuplicadoException("La venta ya se encuentra registrada");
		verificarVenta(venta);
		for (DetalleVenta detalleVenta : venta.getLstDetalleVentas())
			venderProductoAux(detalleVenta.getProducto(), detalleVenta.getCantVendida());
		historicoVentas.add(venta);

	}

	private void venderProductoAux(@NonNull Producto producto, int cantVendida)
			throws ElementoNuloException, ElementoNoEncontradoException {
		producto.venderCantidad(cantVendida);
		actualizarProducto(producto);
	}

	public boolean eliminarVenta(Venta venta) throws ElementoNuloException, ElementoNoEncontradoException {
		if (venta != null)
			return eliminarVentaAux(venta);
		throw new ElementoNuloException("La venta es nula");
	}

	private boolean eliminarVentaAux(Venta venta) throws ElementoNoEncontradoException {
		if (!historicoVentas.remove(venta))
			throw new ElementoNoEncontradoException("No se ha encontrado la venta");
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

	public void agregarCliente(Cliente cliente) throws ElementoNuloException, ElementoDuplicadoException {
		if (cliente == null)
			throw new ElementoNuloException("El cliente es nulo");
		agregarClienteAux(cliente);

	}

	private void agregarClienteAux(Cliente cliente) throws ElementoDuplicadoException {
		if (!mapClientes.containsKey(cliente.getIdentificacion())) {
			mapClientes.put(cliente.getIdentificacion(), cliente);
			return;
		}
		throw new ElementoDuplicadoException("El cliente ya existe en la tienda");

	}

	public void eliminarCliente(Cliente cliente) throws ElementoNuloException, ElementoNoEncontradoException {
		if (cliente == null)
			throw new ElementoNuloException("Recuerda seleccionar un cliente");
		eliminarClienteAux(cliente);
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
		if (cliente == null)
			throw new ElementoNoEncontradoException("El cliente no ha sido encontrado");
		return cliente;
	}

	public void actualizarCliente(Cliente cliente) throws ElementoNuloException, ElementoNoEncontradoException {
		if (cliente == null)
			throw new ElementoNuloException("El cliente a actualizar es nulo");
		Cliente clienteAux = mapClientes.get(cliente.getIdentificacion());
		if (clienteAux == null)
			throw new ElementoNoEncontradoException("No se ha encontrado el cliente a actualizar");
		clienteAux.setDireccion(cliente.getDireccion());
		clienteAux.setNombre(cliente.getNombre());
		clienteAux.setImgBytes(cliente.getImgBytes());
		mapClientes.put(cliente.getIdentificacion(), clienteAux);
		return;

	}

	public void actualizarClienteCompleto(Cliente cliente) throws ElementoNoEncontradoException, ElementoNuloException {
		if (cliente == null)
			throw new ElementoNuloException("El cliente a actualizar es nulo");
		Cliente clienteAux = mapClientes.get(cliente.getIdentificacion());
		if (clienteAux == null)
			throw new ElementoNoEncontradoException("No se ha encontrado el cliente a actualizar");
		mapClientes.put(cliente.getIdentificacion(), cliente);
		return;

	}

	public void agregarCarritoCliente(String cedula, CarritoCompras carrito)
			throws ElementoNuloException, ElementoNoEncontradoException {
		buscarCliente(cedula).agregarCarrito(carrito);
	}

	public String leerClientes() {
		return mapClientes.toString();
	}

	public void verificarCarrito(CarritoCompras carrito) throws CarritoNoFuncionaException {
		for (DetalleCarrito detalle : carrito.getLstDetalleCarritos())
			if (!verificarDetalle(detalle))
				throw new CarritoNoFuncionaException("El carrito contiene productos que no tienen stock");
	}

	private boolean verificarDetalle(DetalleCarrito detalle) {
		Producto productoBuscar = detalle.getProducto();
		for (Producto producto : treeProductos) {
			if (producto.equals(productoBuscar)) {
				return producto.verificarCantidad(detalle.getCantSeleccionada());
			}
		}
		return false;
	}

	public void verificarVenta(Venta venta) throws VentaNoFuncionaException {
		for (DetalleVenta detalle : venta.getLstDetalleVentas())
			if (!verificarDetalleVenta(detalle))
				throw new VentaNoFuncionaException("La venta contiene productos que no tienen stock");
	}

	private boolean verificarDetalleVenta(DetalleVenta detalle) {
		Producto productoBuscar = detalle.getProducto();
		for (Producto producto : treeProductos)
			if (producto.equals(productoBuscar))
				return producto.verificarCantidad(detalle.getCantVendida());
		return false;
	}

}
