package co.edu.uniquindio.estructuras.tienda.services;

public interface ISeleccionClientesController {

	public void setTipoMetodo(TipoMetodo tipoMetodo);

	public TipoMetodo getTipoMetodo();

	public static enum TipoMetodo {
		COMPRAR, GUARDAR_CARRITO;
	}
}
