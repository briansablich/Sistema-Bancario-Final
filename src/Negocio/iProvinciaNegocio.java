package Negocio;

import java.util.List;

import Dominio.Provincia;

public interface iProvinciaNegocio {
	
	Provincia getProvinciaConId(int id_provincia);
	List<Provincia> getListaProvinciasConCantidadDeClientes();
	List<Provincia> getListaProvincias();
}
