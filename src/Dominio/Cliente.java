package Dominio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cliente {
	
	public enum ESTADO { True, False };
	public enum SEXO { Masculino, Femenino };

	private int id; 
	private String dni;
	private String cuil;
	private String nombre;
	private String apellido;
	private SEXO sexo;
	private String nacionalidad;
	private java.util.Date fechaNacimiento; 
	private String direccion;
	private String localidad;
	private String provincia;
	private String correoElectronico;
	private List<Telefono> telefonos;
	private List<Cuenta> cuentas; // List<Cuenta> cuentas = Arrays.asList(new Cuenta[3]);
	private ESTADO estado;
	
	public void setId(int id) {
		this.id = id;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public int getId() {
		return id;
	}
	public String getDni() {
		return dni;
	}
	public String getCuil() {
		return cuil;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public SEXO getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		if (sexo.equals("Masculino")) {
			this.sexo = SEXO.Masculino;		
		} else {
			this.sexo = SEXO.Femenino;
		}
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public java.util.Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(java.util.Date dateNacimiento) {
		this.fechaNacimiento = dateNacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public List<Telefono> getTelefonos() {
		return telefonos;
	}
	public Telefono getTelefonos(int index) {
		return telefonos.get(index);
	}
	public void setTelefonos(ArrayList<Telefono> telefonos) {
		this.telefonos = telefonos;
	}
	public List<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(ArrayList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	public ESTADO getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		if (estado.equals("True")) {
			this.estado = ESTADO.True;		
		} else {
			this.estado = ESTADO.False;
		}
	}
	
	public Cliente() {
		super();
		this.dni = null;
		this.cuil = null;
		this.nombre = null;
		this.apellido = null;
		this.sexo = null;
		this.nacionalidad = null;
		this.fechaNacimiento = null;
		this.direccion = null;
		this.localidad = null;
		this.provincia = null;
		this.correoElectronico = null;
		this.telefonos = Arrays.asList(new Telefono[2]); // TO DO: Arreglar esto para inicializarlo como unla lista 
		this.cuentas = Arrays.asList(new Cuenta[3]);
		this.estado = null;
	}
	
	public Cliente(String dni, String cuil, String nombre, String apellido, SEXO sexo, String nacionalidad,
			Date fechaNacimiento, String direccion, String localidad, String provincia, String correoElectronico,
			ArrayList<Telefono> telefonos, ArrayList<Cuenta> cuentas, ESTADO estado) {
		super();
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.correoElectronico = correoElectronico;
		this.telefonos = telefonos;
		this.cuentas = cuentas;
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dni=" + dni + ", cuil=" + cuil + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", sexo=" + sexo + ", nacionalidad=" + nacionalidad + ", fechaNacimiento=" + fechaNacimiento
				+ ", direccion=" + direccion + ", localidad=" + localidad + ", provincia=" + provincia
				+ ", correoElectronico=" + correoElectronico + ", telefonos=" + telefonos + ", cuentas=" + cuentas
				+ ", estado=" + estado + "]";
	}
	
	
	
}
