package Exception;

public class failLoginException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public failLoginException()
	{
		
	}

	@Override
	public String getMessage() {
		
		return "Usuario o contraseña incorrectos...";
		
	}
	

}
