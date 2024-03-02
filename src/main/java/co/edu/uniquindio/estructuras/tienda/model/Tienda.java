package co.edu.uniquindio.estructuras.tienda.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoDuplicadoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNuloException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNoEncontradoException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Tienda {
	@NonNull
	private String nombre, direccion, nit;
	@NonNull
	private TreeSet<Producto> treeProductos;
	@NonNull
	private LinkedList<Venta> historicoVentas;
	@NonNull
	private HashMap<String, Cliente> mapClientes;
	
	//Se declara un tipo de coleccion de seleccion para los carritos (Desicion del profesor)
	private ArrayList <CarritoCompras> carrito;

	public boolean agregarProducto(Producto producto) throws ElementoNuloException {
		if(producto!=null) {
			return agregarProductoAux(producto);
			
		}else {
			throw new ElementoNuloException("EL producto es nulo");
		}
	}
	private boolean agregarProductoAux(Producto producto) {
		return treeProductos.add(producto);
	}
	
	public boolean eliminarProducto(Producto producto) throws ElementoNuloException, ElementoNoEncontradoException {
		if(producto!=null) {
			return eliminarProductoAux(producto);
		}else {
			throw new ElementoNuloException("El producto es nulo");
		}
	}
	
	
	private boolean eliminarProductoAux(Producto producto) throws ElementoNoEncontradoException {
		if(!treeProductos.remove(producto)) {
			throw new ElementoNoEncontradoException("No se ha encontrado la venta");
		}
		return true;
	}
	
	public Producto buscarProducto(String codigo) throws ElementoNoEncontradoException {
		Iterator <Producto> iterador= treeProductos.iterator();
		while (iterador.hasNext()) {
				Producto produ= iterador.next();
				if(produ.getCodigo().equals(codigo));
				return produ;
			}
		throw new ElementoNoEncontradoException("Producto No Enontrado");	
	}
	
	
	public void actualizarProducto(Producto producto) throws ElementoNuloException, ElementoNoEncontradoException {
		if(producto!=null) {
			actualizarProductoAux(producto);
		}else {
			throw new ElementoNuloException("EL producto no existe");
			
		}
	}
	private void actualizarProductoAux(Producto producto) throws ElementoNoEncontradoException {
		Iterator <Producto> iterador= treeProductos.iterator();
		while(iterador.hasNext()) {
			Producto product= iterador.next();
			if(product.equals(producto)) {
				treeProductos.remove(product);
				treeProductos.add(producto);
				return;
			}
		}
		throw new ElementoNoEncontradoException("No se ha encontrado el producto a actualizar");
	}
	
	public String leerProuctos() {
		return treeProductos.toString();
	}
	
	public boolean agregarVenta(Venta venta) throws ElementoNuloException, ElementoDuplicadoException {
		if(venta!=null) {
			return agregarVentaAux(venta);
		}else {
			throw new ElementoNuloException("La venta es nula");
		}
	}
	private boolean agregarVentaAux(Venta venta) throws ElementoDuplicadoException {
		if(!historicoVentas.contains(venta)) {
			return historicoVentas.add(venta);
		}else {
			throw new ElementoDuplicadoException("La venta ya se encuentra registrada");
		}
	}
	
	public boolean eliminarVenta(Venta venta) throws ElementoNuloException, ElementoNoEncontradoException {
		if(venta!=null) {
			return  eliminarVentaAux(venta);
		}else {
			throw new ElementoNuloException("La venta es nula");
		}
	}
	private boolean eliminarVentaAux(Venta venta) throws ElementoNoEncontradoException {
		
		if(!historicoVentas.remove(venta)) {
			throw new ElementoNoEncontradoException("No se ha encontrado la venta");
		}
		return true;
	}
	
	public Venta buscarVenta(String codigo) throws ElementoNoEncontradoException {
		Iterator <Venta> iterador= historicoVentas.iterator();
		while(iterador.hasNext()) {
			Venta ventaAux= iterador.next();
			if(ventaAux.getCodigo().equals(codigo)) {
				return ventaAux;
			}
		}
		throw new ElementoNoEncontradoException("No se ha encontrado la venta");
	}
	
	public void actualizarVenta(Venta venta) {
		Iterator <Venta> iterador= historicoVentas.iterator();
		while(iterador.hasNext()) {
			Venta ventaAux= iterador.next();
			if(venta.equals(ventaAux)) {
				historicoVentas.remove(ventaAux);
				historicoVentas.add(venta);
			}
		}
	}
	
	public String leerVenta() {
		return historicoVentas.toString();
	}
	
	public boolean agregarCarrito (CarritoCompras carrito) throws ElementoNuloException, ElementoNoEncontradoException {
		if(carrito!=null) {
			return agregarCarritoAux(carrito);
		}
		throw new ElementoNuloException("El carrito es nulo");
	}
	private boolean agregarCarritoAux(CarritoCompras carrito2) throws ElementoNoEncontradoException {
		if(!carrito.contains(carrito2)) {
			return carrito.add(carrito2);
		}else {
			throw new ElementoNoEncontradoException("El objeto no se ha podido encontrar");
		}
		
	}
	
	public boolean borrarCarrito (CarritoCompras carrito) throws ElementoNuloException, ElementoNoEncontradoException {
		if(carrito!=null) {
			return borrarCarritoAux(carrito);
		}
		throw new ElementoNuloException("Carrito es nulo");
	}
	private boolean borrarCarritoAux(CarritoCompras carrito2) throws ElementoNoEncontradoException {
		if(!carrito.remove(carrito2)) {
			throw new ElementoNoEncontradoException("No se ha encontrado el carrito");
		}
		return true;
	}
	
	public CarritoCompras buscarCarritos(String codigo) throws ElementoNoEncontradoException {
		Iterator<CarritoCompras> iterador = carrito.iterator();
		while (iterador.hasNext()) {
			CarritoCompras carritoAux= iterador.next();
			if(carritoAux.getCodigo().equals(codigo)) {
				return carritoAux;
			}
		}
		throw new ElementoNoEncontradoException("No se ha encontrado el carrito con este codigo");
	}
	
	public void actualizarCarrito(CarritoCompras carro) {
		Iterator <CarritoCompras> iterador = carrito.iterator();
		while (iterador.hasNext()) {
			CarritoCompras carroAux= iterador.next();
			if(carro.equals(carroAux)) {
				carrito.remove(carroAux);
				carrito.add(carro);
			}
		}
	}
	
	
	
	
	
	
	

	

}

















































































































