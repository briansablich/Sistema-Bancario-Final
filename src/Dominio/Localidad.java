package Dominio;

public class Localidad {
	private int id_localidad;
	private Provincia provincia;
	private String nombreLocalidad;
	
	
	public int getId_localidad() {
		return id_localidad;
	}
	public void setId_localidad(int id_localidad) {
		this.id_localidad = id_localidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}
	public void setNombreLocalidad(String localidad) {
		this.nombreLocalidad = localidad;
	}
	@Override
	public String toString() {
		return nombreLocalidad;
	}
}
