package co.edu.uniquindio.estructuras.tienda.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.model.Producto;
import co.edu.uniquindio.estructuras.tienda.model.Venta;
import co.edu.uniquindio.estructuras.tienda.services.Imagenable;
import co.edu.uniquindio.estructuras.tienda.utils.ImgUtils;
import javafx.scene.image.Image;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelFactoryController {

	public TreeSet<Producto> getProductos() {
		TreeSet<Producto> treeSet = new TreeSet<Producto>();

		try {
			treeSet.add(new Producto("1234", "Empanada", 1000d, 5,
					Imagenable.getImageBytes(ImgUtils.cropNormal(new Image("imagen.jpg"), 5))));
			treeSet.add(new Producto("1235", "Fresas con Crema", 7000d, 100,
					Imagenable.getImageBytes(ImgUtils.cropNormal(new Image("imagen.jpg"), 5))));
			treeSet.add(new Producto("1236", "Jugo Hit", 2900d, 2,
					Imagenable.getImageBytes(ImgUtils.cropNormal(new Image("imagen.jpg"), 5))));
			treeSet.add(new Producto("1237", "Gaseosa Manzana", 2000d, 5,
					Imagenable.getImageBytes(ImgUtils.cropNormal(new Image("imagen.jpg"), 5))));
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

	public static ModelFactoryController getInstance() {
		if (instance == null)
			instance = new ModelFactoryController();
		return instance;
	}
}
