package co.edu.uniquindio.estructuras.tienda.services;

import co.edu.uniquindio.estructuras.tienda.model.Cliente;

public interface IClientViewController {
	public void setCliente(Cliente c);

	public void setOpenMethod(Runnable r);
}
