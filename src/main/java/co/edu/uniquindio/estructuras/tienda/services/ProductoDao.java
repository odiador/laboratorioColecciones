package co.edu.uniquindio.estructuras.tienda.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;

import co.edu.uniquindio.estructuras.tienda.model.Producto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductoDao {

	public static ProductoDao instance;
	@Getter
	private static final String RUTA = "src/main/resources/co/edu/uniquindio/estructuras/tienda/data/productos.dat";

	public static ProductoDao getInstance() {
		if (instance == null)
			instance = new ProductoDao();
		return instance;
	}

	public void saveData(TreeSet<Producto> productos) {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getRUTA()))) {
			oos.writeObject(productos);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public TreeSet<Producto> loadData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getRUTA()))) {
			Object objeto = ois.readObject();
			ois.close();
			return (TreeSet<Producto>) objeto;

		} catch (IOException | ClassNotFoundException e) {
			TreeSet<Producto> arbol = new TreeSet<Producto>();
			saveData(arbol);
			return arbol;
		}
	}

}
