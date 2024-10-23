package Dominio;

public class Telefono {
	
	private int id;
	private String telefono;
	
	public Telefono() {
		super();
	}
	
	public Telefono(int id, String telefono) {
		this.id = id;
		this.telefono = telefono;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return getTelefono();
	}
	
}
