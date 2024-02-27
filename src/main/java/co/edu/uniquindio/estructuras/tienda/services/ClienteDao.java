package co.edu.uniquindio.estructuras.tienda.services;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
public class ClienteDao {
	
	
	
	public static ClienteDao instance;
	@Getter
	public static final String RUTA="src/main/resources/co/edu/uniquindio/estructuras/tienda/data/clientes.dat";
	
	public static ClienteDao getIntance (){
		if(instance==null)
			instance = new ClienteDao();
		return instance;
	}
	
	
	public void saveData (HashMap<String,Cliente> clientes) {
		
		try(ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(getR))
		
	}

}
