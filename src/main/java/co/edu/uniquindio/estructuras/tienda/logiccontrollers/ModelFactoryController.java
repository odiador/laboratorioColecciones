package co.edu.uniquindio.estructuras.tienda.logiccontrollers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;

import co.edu.uniquindio.estructuras.tienda.exceptions.CampoInvalidoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.CampoVacioException;
import co.edu.uniquindio.estructuras.tienda.exceptions.CantidadSeleccionadaNoEncajaException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoDuplicadoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNuloException;
import co.edu.uniquindio.estructuras.tienda.model.CarritoCompras;
import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.model.DetalleCarrito;
import co.edu.uniquindio.estructuras.tienda.model.Producto;
import co.edu.uniquindio.estructuras.tienda.model.Venta;
import co.edu.uniquindio.estructuras.tienda.services.DataService;
import co.edu.uniquindio.estructuras.tienda.services.Imagenable;
import javafx.scene.image.Image;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelFactoryController {

	private static ModelFactoryController instance;
	private TreeSet<Producto> treeSet;
	private CarritoCompras carritoCompras;

	public TreeSet<Producto> getProductos() {
		return DataService.getInstance().listarProductos();
	}

	public HashMap<String, Cliente> getClientes() {
		return DataService.getInstance().listarClientes();
	}

	public LinkedList<Venta> getVentas() {
		return DataService.getInstance().listarVentas();
	}

	public static ModelFactoryController getInstance() {
		if (instance == null)
			instance = new ModelFactoryController();
		return instance;
	}

	public void agregarCliente(String id, String direccion, String nombre) throws CampoVacioException {
		if (id.trim().isBlank() || direccion.trim().isBlank() || nombre.trim().isBlank())
			throw new CampoVacioException("Rellena todos los campos");
	}

	public void agregarCarrito(int cant, @NonNull Producto producto) throws CantidadSeleccionadaNoEncajaException {
		cargarCarrito();
		DetalleCarrito detalleCarrito = DetalleCarrito.builder().cantSeleccionada(cant).producto(producto).build();
		carritoCompras.agregarDetalleCarrito(detalleCarrito);
		RAMController.getInstance().setCarrito(carritoCompras);
		System.out.println(carritoCompras.getLstDetalleCarritos().toString());
	}

	public CarritoCompras cargarCarrito() {
		if (carritoCompras != null)
			return carritoCompras;
		HashSet<DetalleCarrito> setDetalles = new HashSet<DetalleCarrito>();
		carritoCompras = CarritoCompras.builder().codigo(UUID.randomUUID().toString()).lstDetalleCarritos(setDetalles)
				.build();
		return carritoCompras;
	}

	public void agregarProducto(@NonNull String codigo, @NonNull String nombre, @NonNull String precio,
			@NonNull String cantidad, @NonNull Image imagen) throws CampoInvalidoException, IOException, ElementoNuloException {
		StringBuilder sb = new StringBuilder();
		requerirCampoString(sb, codigo, "El codigo no puede estar vacio");
		requerirCampoString(sb, nombre, "El nombre no puede estar vacio");
		int cantidadAux=requerirCampoInt(sb, cantidad, "La cantidad no puede estar vacia");
		double precioAux= requerirCampoDouble(sb, precio, "El precio no puede estar vacio");
		lanzarCamposInvalidosException(sb);
		Producto producto= Producto.builder().codigo(codigo).nombre(nombre).precio(precioAux)
				.cantidad(cantidadAux).imgBytes(Imagenable.getImageBytes(imagen)).build();
		DataService.getInstance().agregarProducto(producto);
	}
	
	public void agregarCliente (@NonNull String identificacion, @NonNull String nombre,
			@NonNull String direccion, @NonNull Image image) throws CampoInvalidoException, IOException, ElementoNuloException, ElementoDuplicadoException {
		StringBuilder sb= new StringBuilder();
		requerirCampoString(sb, identificacion, "La identificacion no puede estar vacia");
		requerirCampoString(sb, nombre, "El nombre no puede estar vacio");
		requerirCampoString(sb, direccion, "La direccion no puede estar vacia");
		lanzarCamposInvalidosException(sb);
		Cliente cliente= Cliente.builder().identificacion(identificacion).nombre(nombre).direccion(direccion).imgBytes(Imagenable.getImageBytes(image)).build();
		DataService.getInstance().agregarCliente(cliente);
	}
	
	public void agregarVenta(@NonNull CarritoCompras carrito, @NonNull Cliente cliente) throws ElementoNuloException, ElementoDuplicadoException {
		Venta venta= Venta.builder().carrito(carrito).cliente(cliente).build();
		DataService.getInstance().agregarVenta(venta);
		
	}
	
	public void eliminarProducto(Producto producto) throws ElementoNuloException, ElementoNoEncontradoException {
		DataService.getInstance().eliminarProducto(producto);
	}
	
	public void eliminarCliente (Cliente cliente) throws ElementoNuloException, ElementoNoEncontradoException {
		DataService.getInstance().eliminarCliente(cliente);
	}
	public void eliminarVenta (Venta venta) throws ElementoNuloException, ElementoNoEncontradoException {
		DataService.getInstance().eliminarVenta(venta);
	}

	public void requerirCampoString(StringBuilder sb, String cadena, String msg) {
		if (cadena == null || cadena.isBlank()) {
			sb.append(msg);
			sb.append("\n");
		}
	}
	
	public void actualizarProducto(Producto producto) throws ElementoNuloException, ElementoNoEncontradoException {
		DataService.getInstance().actualizarProducto(producto);
	}
	public void actualizarCliente(Cliente cliente) throws ElementoNuloException, ElementoNoEncontradoException {
		DataService.getInstance().actualizarCliente(cliente);
	}
	public void actualizarVenta (Venta venta) {
		DataService.getInstance().actualizarVenta(venta);
	}
	
	public Producto buscarProducto(String codigo) throws ElementoNoEncontradoException {
		return DataService.getInstance().buscarProducto(codigo);
	}
	public Cliente buscarCliente(String cedula) throws ElementoNoEncontradoException {
		return DataService.getInstance().buscarCliente(cedula);
	}

	public Integer requerirCampoInt(StringBuilder sb, String numero, String msg) {
		if (numero == null || numero.isBlank()) {
			sb.append(msg);
			sb.append("\n");
			return null;
		}
		Integer numeroAux = null;
		try {
			numeroAux = Integer.parseInt(numero);
		} catch (Exception e) {
			sb.append(msg);
			sb.append("\n");
			return null;
		}
		if (numeroAux < 0) {
			sb.append(msg);
			sb.append("\n");
		}
		return numeroAux;

	}
	public Double requerirCampoDouble(StringBuilder sb, String numero, String msg) {
		if(numero==null||numero.isBlank()) {
			sb.append(msg);
			sb.append("\n");
			return null;
		}
		Double numeroAux= null; 
				
		try {
			numeroAux= Double.parseDouble(numero);
		}catch (Exception e) {
			sb.append(msg);
			sb.append("\n");
			return null;
		}
		if(numeroAux<0.0) {
			sb.append(msg);
			sb.append("\n");
		}
		return numeroAux;
	}

	public void lanzarCamposInvalidosException(StringBuilder sb) throws CampoInvalidoException {
		if (!sb.isEmpty()) {
			sb.deleteCharAt(sb.length() - 1);
			throw new CampoInvalidoException(sb.toString());
		}
	}

	public CarritoCompras agregarDetalleCarrito(@NonNull Producto producto, @NonNull String cantidad)
			throws CantidadSeleccionadaNoEncajaException, ElementoNuloException, CampoInvalidoException {
		StringBuilder sb = new StringBuilder();
		int cantidadAux = requerirCampoInt(sb, cantidad, "La cantidad no es valida");
		lanzarCamposInvalidosException(sb);
		DetalleCarrito detalle = DetalleCarrito.builder().producto(producto).cantSeleccionada(cantidadAux).build();
		return DataService.getInstance().agregarDetalleCarrito(detalle);
	}

}
