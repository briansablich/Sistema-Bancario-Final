package Dao;

import java.util.List;

import Dominio.Localidad;

public interface iLocalidadDao {
	Localidad getLocalidadConId(int id_localidad);
	List<Localidad> getLocalidadesPorProvincia(int id_provincia);
	List<Localidad> getTodasLasLocalidades();
}
