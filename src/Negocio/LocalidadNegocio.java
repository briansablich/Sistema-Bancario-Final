package Negocio;

import java.util.List;

import Dao.LocalidadDao;
import Dao.iLocalidadDao;
import Dominio.Localidad;

public class LocalidadNegocio implements iLocalidadNegocio {
	
	private iLocalidadDao iLDao;
	
	public LocalidadNegocio(){
		this.iLDao = new LocalidadDao();
	}

	@Override
	public Localidad getLocalidadConId(int id_localidad) {
		return iLDao.getLocalidadConId(id_localidad);
	}

	@Override
	public List<Localidad> getLocalidadesPorProvincia(int id_provincia) {
		return iLDao.getLocalidadesPorProvincia(id_provincia);
	}

	@Override
	public List<Localidad> getTodasLasLocalidades() {
		return iLDao.getTodasLasLocalidades();
	}

}
