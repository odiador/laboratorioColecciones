package co.edu.uniquindio.estructuras.tienda.services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.UUID;

import co.edu.uniquindio.estructuras.tienda.exceptions.CantidadSeleccionadaNoEncajaException;
import co.edu.uniquindio.estructuras.tienda.exceptions.CarritoNoFuncionaException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoDuplicadoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNuloException;
import co.edu.uniquindio.estructuras.tienda.exceptions.VentaNoFuncionaException;
import co.edu.uniquindio.estructuras.tienda.model.CarritoCompras;
import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.model.DetalleCarrito;
import co.edu.uniquindio.estructuras.tienda.model.Producto;
import co.edu.uniquindio.estructuras.tienda.model.Tienda;
import co.edu.uniquindio.estructuras.tienda.model.Venta;

public class DataService {

	private static DataService instance;
	private Tienda tienda;

	public static DataService getInstance() {
		if (instance == null)
			return instance = new DataService();
		return instance;
	}

	private DataService() {
		this.tienda = new Tienda();
	}

	public void leerProductos() {
		tienda.setTreeProductos(ProductoDao.getInstance().loadData());
		;
	}

	public void leerHistoricoVentas() {
		tienda.setHistoricoVentas(VentaDao.getInstance().loadData());
	}

	public void leerMapClientes() {
		tienda.setMapClientes(ClienteDao.getIntance().loadData());
	}

	public void agregarProducto(Producto producto) throws ElementoNuloException {
		leerProductos();
		tienda.agregarProducto(producto);
		ProductoDao.getInstance().saveData(tienda.getTreeProductos());

	}

	public void agregarVenta(Venta venta) throws ElementoNuloException, ElementoDuplicadoException,
			VentaNoFuncionaException, ElementoNoEncontradoException {
		leerHistoricoVentas();
		tienda.agregarVenta(venta);
		VentaDao.getInstance().saveData(tienda.getHistoricoVentas());
		ProductoDao.getInstance().saveData(tienda.getTreeProductos());
	}

	public void agregarCliente(Cliente cliente) throws ElementoNuloException, ElementoDuplicadoException {
		leerMapClientes();
		tienda.agregarCliente(cliente);
		ClienteDao.getIntance().saveData(tienda.getMapClientes());
	}

	public void eliminarProducto(Producto producto) throws ElementoNuloException, ElementoNoEncontradoException {
		leerProductos();
		tienda.eliminarProducto(producto);
		ProductoDao.getInstance().saveData(tienda.getTreeProductos());
	}

	public void eliminarVenta(Venta venta) throws ElementoNuloException, ElementoNoEncontradoException {
		leerHistoricoVentas();
		tienda.eliminarVenta(venta);
		VentaDao.getInstance().saveData(tienda.getHistoricoVentas());
	}

	public void eliminarCliente(Cliente cliente) throws ElementoNuloException, ElementoNoEncontradoException {
		leerMapClientes();
		tienda.eliminarCliente(cliente);
		ClienteDao.getIntance().saveData(tienda.getMapClientes());
	}

	public void actualizarProducto(Producto producto) throws ElementoNuloException, ElementoNoEncontradoException {
		leerProductos();
		tienda.actualizarProducto(producto);
		ProductoDao.getInstance().saveData(tienda.getTreeProductos());
	}

	public void actualizarVenta(Venta venta) {
		leerHistoricoVentas();
		tienda.actualizarVenta(venta);
		VentaDao.getInstance().saveData(tienda.getHistoricoVentas());
	}

	public void actualizarCliente(Cliente cliente) throws ElementoNuloException, ElementoNoEncontradoException {
		leerMapClientes();
		tienda.actualizarCliente(cliente);
		ClienteDao.getIntance().saveData(tienda.getMapClientes());
	}

	public Producto buscarProducto(String codigo) throws ElementoNoEncontradoException {
		leerProductos();
		Producto productoEncontrado = tienda.buscarProducto(codigo);
		return productoEncontrado;

	}

	public Venta buscarVenta(String codigo) throws ElementoNoEncontradoException {
		leerHistoricoVentas();
		Venta ventaEncontrada = tienda.buscarVenta(codigo);
		return ventaEncontrada;
	}

	public Cliente buscarCliente(String cedula) throws ElementoNoEncontradoException {
		leerMapClientes();
		Cliente clienteEncontrado = tienda.buscarCliente(cedula);
		return clienteEncontrado;
	}

	public TreeSet<Producto> listarProductos() {
		leerProductos();
		return tienda.getTreeProductos();
	}

	public LinkedList<Venta> listarVentas() {
		leerHistoricoVentas();
		return tienda.getHistoricoVentas();
	}

	public HashMap<String, Cliente> listarClientes() {
		leerMapClientes();
		return tienda.getMapClientes();
	}

	public CarritoCompras leerCarrito() {
		return CarritoDao.getInstance().loadData();
	}

	public CarritoCompras agregarDetalleCarrito(DetalleCarrito detalleCarrito)
			throws CantidadSeleccionadaNoEncajaException, ElementoNuloException {
		if (detalleCarrito == null)
			throw new ElementoNuloException("El detalle carrito es nulo");
		CarritoCompras carrito = leerCarrito();
		carrito.agregarDetalleCarrito(detalleCarrito);
		CarritoDao.getInstance().saveData(carrito);
		return carrito;
	}

	public CarritoCompras vaciarCarrito() {
		CarritoCompras carrito = CarritoCompras.builder().codigo(UUID.randomUUID().toString()).build();
		CarritoDao.getInstance().saveData(carrito);
		return carrito;
	}

	public CarritoCompras eliminarDetalleCarrito(DetalleCarrito detalleCarrito) throws ElementoNoEncontradoException {
		CarritoCompras carrito = leerCarrito();
		carrito.eliminarDetalleCarrito(detalleCarrito);
		CarritoDao.getInstance().saveData(carrito);
		return carrito;
	}

	public void actualizarCarrito(CarritoCompras carrito) {
		CarritoDao.getInstance().saveData(carrito);
	}

	public void verificarCarrito(CarritoCompras carrito) throws CarritoNoFuncionaException {
		leerProductos();
		tienda.verificarCarrito(carrito);
	}

	public void actualizarClienteCompleto(Cliente cliente) throws ElementoNoEncontradoException, ElementoNuloException {
		leerMapClientes();
		tienda.actualizarClienteCompleto(cliente);
		ClienteDao.getIntance().saveData(tienda.getMapClientes());
	}

}
