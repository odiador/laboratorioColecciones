package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

public class CarritoLogicController {
	private static CarritoLogicController instance;

	public static CarritoLogicController getInstance() {
		if (instance == null)
			instance = new CarritoLogicController();
		return instance;
	}
}
