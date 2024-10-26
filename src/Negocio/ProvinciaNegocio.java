package Negocio;

import java.util.List;

import Dao.ProvinciaDao;
import Dao.iProvinciaDao;
import Dominio.Provincia;

public class ProvinciaNegocio implements iProvinciaNegocio {

	private iProvinciaDao iPDao;
	
	ProvinciaNegocio(){
		this.iPDao = new ProvinciaDao();
	}
	@Override
	public Provincia getProvinciaConId(int id_provincia) {
		return iPDao.getProvinciaConId(id_provincia);
	}

	@Override
	public List<Provincia> getListaProvinciasConCantidadDeClientes() {
		return iPDao.getListaProvinciasConCantidadDeClientes();
	}

	@Override
	public List<Provincia> getListaProvincias() {
		return iPDao.getListaProvincias();
	}

}
