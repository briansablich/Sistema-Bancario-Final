package Negocio;

import Dao.TiposCuentaDao;
import Dao.iTiposCuentaDao;
import Dominio.TiposCuenta;

public class TiposCuentaNegocio implements iTiposCuentaNegocio {

	private iTiposCuentaDao iTCDao;
	
	public TiposCuentaNegocio() {
		this.iTCDao = new TiposCuentaDao();
	}
	@Override
	public TiposCuenta getTipoCuenta(int idTipoCuenta) {
		return iTCDao.getTipoCuenta(idTipoCuenta);
	}

}
