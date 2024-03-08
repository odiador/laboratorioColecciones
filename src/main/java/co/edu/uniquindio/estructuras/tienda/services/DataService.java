package co.edu.uniquindio.estructuras.tienda.services;

import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoDuplicadoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNuloException;
import co.edu.uniquindio.estructuras.tienda.model.Cliente;
import co.edu.uniquindio.estructuras.tienda.model.Producto;
import co.edu.uniquindio.estructuras.tienda.model.Tienda;
import co.edu.uniquindio.estructuras.tienda.model.Venta;

public class DataService {
	
	
	private DataService instance;
	private Tienda tienda;
	
	public DataService getInstance() {
		if(instance==null)
			return instance= new DataService();
		return instance;
	}
	
	private DataService() {
		this.tienda= new Tienda();
	}
	
	public void leerProductos() {
		tienda.setTreeProductos(ProductoDao.getInstance().loadData());;
	}
	public void leerHistoricoVentas() {
		tienda.setHistoricoVentas(VentaDao.getInstance().loadData());
	}
	public void leerMapClientes () {
		tienda.setMapClientes(ClienteDao.getIntance().loadData());
	}
	
	public void agregarProducto(Producto producto) {
		leerProductos();
		try {
			tienda.agregarProducto(producto);
			ProductoDao.getInstance().saveData(tienda.getTreeProductos());
		} catch (ElementoNuloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void agregarVenta (Venta venta) {
		leerHistoricoVentas();
		try {
			tienda.agregarVenta(venta);
			VentaDao.getInstance().saveData(tienda.getHistoricoVentas());
		} catch (ElementoNuloException | ElementoDuplicadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void agregarCliente(Cliente cliente) {
		leerMapClientes();
		try {
			tienda.agregarCliente(cliente);
			ClienteDao.getIntance().saveData(tienda.getMapClientes());
		} catch (ElementoNuloException | ElementoDuplicadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void eliminarProducto(Producto producto) {
		leerProductos();
		try {
			tienda.eliminarProducto(producto);
			ProductoDao.getInstance().saveData(tienda.getTreeProductos());
		} catch (ElementoNuloException | ElementoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarVenta (Venta venta) {
		leerHistoricoVentas();
		try {
			tienda.eliminarVenta(venta);
			VentaDao.getInstance().saveData(tienda.getHistoricoVentas());
		} catch (ElementoNuloException | ElementoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void eliminarCliente(Cliente cliente) {
		leerMapClientes();
		try {
			tienda.eliminarCliente(cliente);
			ClienteDao.getIntance().saveData(tienda.getMapClientes());
		} catch (ElementoNuloException | ElementoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
