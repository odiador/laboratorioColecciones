package co.edu.uniquindio.estructuras.tienda.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.LinkedList;

import co.edu.uniquindio.estructuras.tienda.model.Venta;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VentaDao {

	public static VentaDao instance;
	@Getter
	private static final String RUTA = "src/main/resources/co/edu/uniquindio/estructuras/tienda/data/ventas.dat";

	public static VentaDao getInstance() {
		if (instance == null)
			instance = new VentaDao();
		return instance;
	}

	public void saveData(LinkedList<Venta> ventas) {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getRUTA()))) {
			oos.writeObject(ventas);
			oos.close();
		} catch (IOException e) {
		}
	}

	@SuppressWarnings("unchecked")
	public LinkedList<Venta> loadData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getRUTA()))) {
			Object objeto = ois.readObject();
			ois.close();
			return (LinkedList<Venta>) objeto;

		} catch (IOException | ClassNotFoundException e) {
			LinkedList<Venta> lista = new LinkedList<Venta>();
			saveData(lista);
			return lista;
		}
	}

}
