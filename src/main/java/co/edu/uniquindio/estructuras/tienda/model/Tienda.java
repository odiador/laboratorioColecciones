package co.edu.uniquindio.estructuras.tienda.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoDuplicadoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNuloException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ObjetoNoEncontradoException;
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
	
	public boolean eliminarProducto(Producto producto) throws ElementoNuloException, ObjetoNoEncontradoException {
		if(producto!=null) {
			return eliminarProductoAux(producto);
		}else {
			throw new ElementoNuloException("El producto es nulo");
		}
	}
	
	
	private boolean eliminarProductoAux(Producto producto) throws ObjetoNoEncontradoException {
		if(!treeProductos.remove(producto)) {
			throw new ObjetoNoEncontradoException("No se ha encontrado la venta");
		}
		return true;
	}
	
	public Producto buscarProducto(String codigo) throws ObjetoNoEncontradoException {
		Iterator <Producto> iterador= treeProductos.iterator();
		while (iterador.hasNext()) {
				Producto produ= iterador.next();
				if(produ.getCodigo().equals(codigo));
				return produ;
			}
		throw new ObjetoNoEncontradoException("Producto No Enontrado");	
	}
	
	
	public void actualizarProducto(Producto producto) throws ElementoNuloException, ObjetoNoEncontradoException {
		if(producto!=null) {
			actualizarProductoAux(producto);
		}else {
			throw new ElementoNuloException("EL producto no existe");
			
		}
	}
	private void actualizarProductoAux(Producto producto) throws ObjetoNoEncontradoException {
		Iterator <Producto> iterador= treeProductos.iterator();
		while(iterador.hasNext()) {
			Producto product= iterador.next();
			if(product.equals(producto)) {
				treeProductos.remove(product);
				treeProductos.add(producto);
				return;
			}
		}
		throw new ObjetoNoEncontradoException("No se ha encontrado el producto a actualizar");
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
	
	public boolean eliminarVenta(Venta venta) throws ElementoNuloException, ObjetoNoEncontradoException {
		if(venta!=null) {
			return  eliminarVentaAux(venta);
		}else {
			throw new ElementoNuloException("La venta es nula");
		}
	}
	private boolean eliminarVentaAux(Venta venta) throws ObjetoNoEncontradoException {
		
		if(!historicoVentas.remove(venta)) {
			throw new ObjetoNoEncontradoException("No se ha encontrado la venta");
		}
		return true;
	}
	
	public Venta buscarVenta(String codigo) throws ObjetoNoEncontradoException {
		Iterator <Venta> iterador= historicoVentas.iterator();
		while(iterador.hasNext()) {
			Venta ventaAux= iterador.next();
			if(ventaAux.getCodigo().equals(codigo)) {
				return ventaAux;
			}
		}
		throw new ObjetoNoEncontradoException("No se ha encontrado la venta");
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
	
	
	
	
	

	

}
