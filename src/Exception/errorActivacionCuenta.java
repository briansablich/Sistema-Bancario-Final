package Exception;

public class errorActivacionCuenta extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	
	public errorActivacionCuenta() {
		super();
	}



	@Override
	public String getMessage() {
		System.out.println("Error en la activación de la cuenta...");
		return "Error en la activación de la cuenta...";	
	}
	
}
