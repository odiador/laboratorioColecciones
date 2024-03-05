package co.edu.uniquindio.estructuras.tienda.logiccontrollers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
import co.edu.uniquindio.estructuras.tienda.services.Imagenable;
import javafx.scene.image.Image;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelFactoryController {

	public TreeSet<Producto> getProductos() {
		if (treeSet != null)
			return treeSet;
		treeSet = new TreeSet<Producto>();

		try {
			treeSet.add(new Producto("1234", "Empanada", 1000d, 5, Imagenable.getImageBytes(new Image("imagen.jpg"))));
			treeSet.add(new Producto("1235", "Fresas con Crema", 7000d, 100,
					Imagenable.getImageBytes(new Image("imagen.jpg"))));
			treeSet.add(new Producto("1236", "Jugo Hit", 2900d, 2, Imagenable.getImageBytes(new Image("imagen.jpg"))));
			treeSet.add(new Producto("1237", "Gaseosa Manzana", 2000d, 5,
					Imagenable.getImageBytes(new Image("imagen.jpg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return treeSet;
	}

	public Map<String, Cliente> getClientes() {
		HashMap<String, Cliente> map = new HashMap<String, Cliente>();

		try {
			map.put("1234", new Cliente("1234", "Juan Odiador", "Calle 13", new ArrayList<Venta>(),
					Imagenable.getImageBytes(new Image("imagen.jpg"))));
			map.put("1235", new Cliente("1235", "Alejito Sanchez", "Calle 13", new ArrayList<Venta>(),
					Imagenable.getImageBytes(new Image("imagen.jpg"))));
			map.put("1236", new Cliente("1236", "Santaigo Quintas", "Calle 13", new ArrayList<Venta>(),
					Imagenable.getImageBytes(new Image("imagen.jpg"))));
			map.put("1237", new Cliente("1237", "Pepe Cardenas", "Calle 13", new ArrayList<Venta>(),
					Imagenable.getImageBytes(new Image("imagen.jpg"))));
			map.put("1238", new Cliente("1237", "Harbyyyyyy", "Calle 13", new ArrayList<Venta>(),
					Imagenable.getImageBytes(new Image("imagen.jpg"))));
			map.put("1239", new Cliente("1237", "Jacobo Bocas", "Calle 13", new ArrayList<Venta>(),
					Imagenable.getImageBytes(new Image("imagen.jpg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
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

}
