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
			map.put("1234", Cliente.builder().identificacion("1234").nombre("Juan Odiador").direccion("Calle 13")
					.imgBytes(Imagenable.getImageBytes(new Image("imagen.jpg"))).build());
			map.put("1235", Cliente.builder().identificacion("1235").nombre("Alejito Sanchez").direccion("Calle 13")
					.imgBytes(Imagenable.getImageBytes(new Image("imagen.jpg"))).build());
			map.put("1236", Cliente.builder().identificacion("1236").nombre("Santiago Quintas").direccion("Calle 13")
					.imgBytes(Imagenable.getImageBytes(new Image("imagen.jpg"))).build());
			map.put("1237", Cliente.builder().identificacion("1237").nombre("Pepe Cardenas").direccion("Calle 13")
					.imgBytes(Imagenable.getImageBytes(new Image("imagen.jpg"))).build());
			map.put("1238", Cliente.builder().identificacion("1238").nombre("Harby").direccion("Calle 13")
					.imgBytes(Imagenable.getImageBytes(new Image("imagen.jpg"))).build());
			map.put("1239", Cliente.builder().identificacion("1239").nombre("Jacobo Bocas").direccion("Calle 13")
					.imgBytes(Imagenable.getImageBytes(new Image("imagen.jpg"))).build());
	
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

}
