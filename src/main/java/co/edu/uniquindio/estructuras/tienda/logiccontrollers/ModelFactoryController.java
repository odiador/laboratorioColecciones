package co.edu.uniquindio.estructuras.tienda.logiccontrollers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;

import co.edu.uniquindio.estructuras.tienda.exceptions.CampoVacioException;
import co.edu.uniquindio.estructuras.tienda.exceptions.CantidadSeleccionadaNoEncajaException;
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

	public TreeSet<Producto> getProductos() {
		return DataService.getInstance().listarProductos();
	}

	public HashMap<String, Cliente> getClientes() {
		return DataService.getInstance().listarClientes();
	}

	public LinkedList<Venta> getVentas() {
		return DataService.getInstance().listarVentas();
	}

	private static ModelFactoryController instance;
	private TreeSet<Producto> treeSet;
	private CarritoCompras carritoCompras;

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

	public void agregarProducto(@NonNull String codigo, @NonNull String nombre, @NonNull double precio,
			@NonNull String cantidad, @NonNull Image imagen) {
		StringBuilder sb = new StringBuilder();
		requerirCampoString(sb, codigo, "El codigo no puede estar vacio");
		requerirCampoString(sb, nombre, "El nombre no puede estar vacio");
		requerirCampoInt(sb, cantidad, "La cantidad no puede estar vacia");
	}

	public void requerirCampoString(StringBuilder sb, String cadena, String msg) {
		if (cadena == null || cadena.isBlank()) {
			sb.append(msg);
			sb.append("\n");
		}
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

}
