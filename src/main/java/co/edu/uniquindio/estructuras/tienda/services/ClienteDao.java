package co.edu.uniquindio.estructuras.tienda.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClienteDao {

	public static ClienteDao instance;
	@Getter
	private static final String RUTA = "src/main/resources/co/edu/uniquindio/estructuras/tienda/data/clientes.dat";

	public static ClienteDao getIntance() {
		if (instance == null)
			instance = new ClienteDao();
		return instance;
	}

	public void saveData(HashMap<String, Cliente> clientes) {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getRUTA()))) {
			oos.writeObject(clientes);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Cliente> loadData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getRUTA()))) {
			Object objeto = ois.readObject();
			ois.close();
			return (HashMap<String, Cliente>) objeto;

		} catch (IOException | ClassNotFoundException e) {
			HashMap<String, Cliente> hashMap = new HashMap<String, Cliente>();
			saveData(hashMap);
			return hashMap;
		}
	}

}
