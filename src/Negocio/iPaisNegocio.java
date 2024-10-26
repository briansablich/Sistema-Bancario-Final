package Negocio;

import java.util.List;

import Dominio.Pais;

public interface iPaisNegocio {
	
	Pais getPaisConId(int id_pais);
	List<Pais> getListaPaises();

}
