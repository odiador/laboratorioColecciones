package co.edu.uniquindio.estructuras.tienda.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.estructuras.tienda.model.CarritoCompras;
import lombok.Getter;

public class CarritoDao {

	private static CarritoDao instance;
	@Getter
	private static final String RUTA = "src/main/resources/co/edu/uniquindio/estructuras/tienda/data/carrito.dat";

	public static CarritoDao getInstance() {
		if (instance == null) {
			instance = new CarritoDao();
		}
		return instance;
	}

	public void saveData(CarritoCompras carrito) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getRUTA()))) {
			oos.writeObject(carrito);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CarritoCompras loadData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getRUTA()))) {
			Object objeto = ois.readObject();
			ois.close();
			return (CarritoCompras) objeto;
		} catch (IOException | ClassNotFoundException e) {
			CarritoCompras carrito = new CarritoCompras();
			saveData(carrito);
			return carrito;
		}
	}

}
