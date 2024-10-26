package Dao;

import java.util.List;

import Dominio.Pais;

public interface iPaisDao {

	Pais getPaisConId(int id_pais);
	List<Pais> getListaPaises();
}
