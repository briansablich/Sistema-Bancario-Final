package Negocio;

import java.util.List;

import Dao.PaisDao;
import Dao.iPaisDao;
import Dominio.Pais;

public class PaisNegocio implements iPaisNegocio {
	
	private iPaisDao iPDao;
	
	public PaisNegocio() {
		this.iPDao = new PaisDao();
	}

	@Override
	public Pais getPaisConId(int id_pais) {
		return iPDao.getPaisConId(id_pais);
	}

	@Override
	public List<Pais> getListaPaises() {
		return iPDao.getListaPaises();
	}

}
