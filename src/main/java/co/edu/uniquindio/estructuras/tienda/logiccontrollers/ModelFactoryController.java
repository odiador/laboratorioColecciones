package co.edu.uniquindio.estructuras.tienda.logiccontrollers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import co.edu.uniquindio.estructuras.tienda.exceptions.CampoInvalidoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.CampoVacioException;
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

	public TreeSet<Producto> getProductos() {
		return DataService.getInstance().listarProductos();
	}

	public HashMap<String, Cliente> getClientes() {
		return DataService.getInstance().listarClientes();
	}

	public ArrayList<Cliente> getListClientes() {
		return getClientes().values().stream().collect(Collectors.toCollection(ArrayList::new));
	}

	public LinkedList<Venta> getVentas() {
		return DataService.getInstance().listarVentas();
	}

	public static ModelFactoryController getInstance() {
		if (instance == null)
			instance = new ModelFactoryController();
		return instance;
	}

	public void agregarCliente(String id, String direccion, String nombre, Image image)
			throws CampoVacioException, ElementoNuloException, ElementoDuplicadoException, CampoInvalidoException {
		if (id.trim().isBlank() || direccion.trim().isBlank() || nombre.trim().isBlank())
			throw new CampoVacioException("Rellena todos los campos");
		if (image == null)
			throw new CampoVacioException("Recuerda seleccionar la imagen");
		try {
			Cliente cliente = Cliente.builder().direccion(direccion).identificacion(id).nombre(nombre)
					.imgBytes(Imagenable.getImageBytes(image)).build();
			DataService.getInstance().agregarCliente(cliente);
		} catch (IOException e) {
			throw new CampoInvalidoException("Recuerda seleccionar la imagen");
		}

	}

	public void agregarDetalleCarrito(int cant, @NonNull Producto producto)
			throws CantidadSeleccionadaNoEncajaException, ElementoNuloException {
		DetalleCarrito detalleCarrito = DetalleCarrito.builder().cantSeleccionada(cant).producto(producto).build();
		CarritoCompras carrito = DataService.getInstance().agregarDetalleCarrito(detalleCarrito);
		RAMController.getInstance().actualizarCarrito(carrito);
	}

	public void eliminarDetalleCarrito(DetalleCarrito detalleCarrito) throws ElementoNoEncontradoException {
		CarritoCompras carrito = DataService.getInstance().eliminarDetalleCarrito(detalleCarrito);
		RAMController.getInstance().actualizarCarrito(carrito);
	}

	public void vaciarCarrito() {
		RAMController.getInstance().actualizarCarrito(DataService.getInstance().vaciarCarrito());
	}

	public void cargarCarrito() {
		RAMController.getInstance().actualizarCarrito(DataService.getInstance().leerCarrito());
	}

	public void agregarProducto(@NonNull String codigo, @NonNull String nombre, @NonNull String precio,
			@NonNull String cantidad, @NonNull Image imagen)
			throws CampoInvalidoException, IOException, ElementoNuloException {
		StringBuilder sb = new StringBuilder();
		requerirCampoString(sb, codigo, "El codigo no puede estar vacio");
		requerirCampoString(sb, nombre, "El nombre no puede estar vacio");
		int cantidadAux = requerirCampoInt(sb, cantidad, "La cantidad no puede estar vacia");
		double precioAux = requerirCampoDouble(sb, precio, "El precio no puede estar vacio");
		lanzarCamposInvalidosException(sb);
		Producto producto = Producto.builder().codigo(codigo).nombre(nombre).precio(precioAux).cantidad(cantidadAux)
				.imgBytes(Imagenable.getImageBytes(imagen)).build();
		DataService.getInstance().agregarProducto(producto);
	}

	public void agregarVenta(@NonNull CarritoCompras carrito, @NonNull Cliente cliente)
			throws ElementoNuloException, ElementoDuplicadoException, ElementoNoEncontradoException,
			CarritoNoFuncionaException, VentaNoFuncionaException {
		Cliente clienteBuscado = buscarCliente(cliente.getIdentificacion());
		DataService.getInstance().verificarCarrito(carrito);
		Venta venta = Venta.builder().carrito(carrito).cliente(cliente).build();
		DataService.getInstance().agregarVenta(venta);
		clienteBuscado.agregarVenta(venta);
		actualizarClienteCompleto(clienteBuscado);
		vaciarCarrito();
	}

	public void eliminarProducto(Producto producto) throws ElementoNuloException, ElementoNoEncontradoException {
		DataService.getInstance().eliminarProducto(producto);
	}

	public void eliminarCliente(Cliente cliente) throws ElementoNuloException, ElementoNoEncontradoException {
		DataService.getInstance().eliminarCliente(cliente);
	}

	public void eliminarVenta(Venta venta) throws ElementoNuloException, ElementoNoEncontradoException {
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

	public void actualizarVenta(Venta venta) {
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
		if (numero == null || numero.isBlank()) {
			sb.append(msg);
			sb.append("\n");
			return null;
		}
		Double numeroAux = null;

		try {
			numeroAux = Double.parseDouble(numero);
		} catch (Exception e) {
			sb.append(msg);
			sb.append("\n");
			return null;
		}
		if (numeroAux < 0.0) {
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

	public CarritoCompras getCarrito() {
		return DataService.getInstance().leerCarrito();
	}

	public void agregarCarritoCliente(@NonNull CarritoCompras carrito, @NonNull Cliente cliente)
			throws ElementoNuloException, ElementoNoEncontradoException {
		Cliente clienteBuscado = buscarCliente(cliente.getIdentificacion());
		clienteBuscado.agregarCarrito(carrito);
		actualizarClienteCompleto(clienteBuscado);
		vaciarCarrito();
	}

	private void actualizarClienteCompleto(Cliente cliente)
			throws ElementoNoEncontradoException, ElementoNuloException {
		DataService.getInstance().actualizarClienteCompleto(cliente);

	}

	public void setCarrito(CarritoCompras carritoCompras) {
		DataService.getInstance().actualizarCarrito(carritoCompras);
		RAMController.getInstance().actualizarCarrito(carritoCompras);
	}

	public Cliente eliminarCarritoCliente(Cliente cliente, CarritoCompras carrito)
			throws ElementoNuloException, ElementoNoEncontradoException {
		Cliente buscarCliente = buscarCliente(cliente.getIdentificacion());
		buscarCliente.borrarCarrito(carrito);
		actualizarClienteCompleto(buscarCliente);
		return buscarCliente;
	}

	public boolean carritoEstaVacio() {
		return DataService.getInstance().leerCarrito().estaVacio();
	}

	public List<Cliente> getListClientesFiltro(String filtro) {
		return getListClientes().stream()
				.filter(cliente -> cliente.getIdentificacion().toLowerCase().contains(filtro.toLowerCase()))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public TreeSet<Producto> getProductosFiltro(String filtro) {
		return getProductos().stream()
				.filter(producto -> producto.getNombre().toLowerCase().contains(filtro.toLowerCase()))
				.collect(Collectors.toCollection(TreeSet::new));
	}

	public void actualizarCliente(String identificacion, String direccion, String nombre, Image image)
			throws ElementoNuloException, ElementoNoEncontradoException, IOException {
		actualizarCliente(Cliente.builder().nombre(nombre).identificacion(identificacion).direccion(direccion)
				.imgBytes(Imagenable.getImageBytes(image)).build());
	}

}
