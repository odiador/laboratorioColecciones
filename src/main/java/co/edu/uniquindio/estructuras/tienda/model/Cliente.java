package co.edu.uniquindio.estructuras.tienda.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoDuplicadoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.estructuras.tienda.exceptions.ElementoNuloException;
import co.edu.uniquindio.estructuras.tienda.services.Imagenable;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Builder
@ToString
public class Cliente implements Imagenable ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@NonNull
	private String identificacion;
	@NonNull
	private String nombre, direccion;
	@NonNull
	@Builder.Default
	private ArrayList<Venta> lstVentas= new ArrayList<Venta>();
	@ToString.Exclude
	private byte[] imgBytes;
	@NonNull
	@Builder.Default 
	private ArrayList<CarritoCompras> carrito= new ArrayList<CarritoCompras>() ;

	public void setImagen(Image image) throws IOException {
		imgBytes = Imagenable.getImageBytes(image);
	}

	@Override
	public byte[] getImgBytes() {
		return imgBytes;
	}
	public boolean agregarCarrito (CarritoCompras carritos) throws ElementoNuloException, ElementoNoEncontradoException {
		if(carritos!=null) {
			return agregarCarritoAux(carritos);
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
	
	public String leerCarritos() {
		return carrito.toString();
	}
	

	
	public void agregarVenta(Venta venta) throws ElementoDuplicadoException {
		if(lstVentas.contains(venta)) {
			throw new ElementoDuplicadoException("La venta ya se encuentra registrada");
		}else {
			lstVentas.add(venta);
		}
	}

}
