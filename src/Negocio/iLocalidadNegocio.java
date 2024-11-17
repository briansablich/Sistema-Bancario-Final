package Negocio;

import java.util.List;

import Dominio.Localidad;

public interface iLocalidadNegocio {
	Localidad getLocalidadConId(int id_localidad);
	List<Localidad> getLocalidadesPorProvincia(int id_provincia);
	List<Localidad> getTodasLasLocalidades();
}
