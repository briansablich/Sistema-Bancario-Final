package Negocio;

import Dao.TipoMovimientoDao;
import Dao.iTipoMovimientoDao;
import Dominio.TipoMovimiento;

public class TipoMovimientoNegocio implements iTipoMovimientoNegocio {

	private iTipoMovimientoDao iTMD;
	
	public TipoMovimientoNegocio() {
		this.iTMD = new TipoMovimientoDao();
	}
	
	@Override
	public TipoMovimiento getTipoMovimiento(int idTipoMovimiento) {
		return iTMD.getTipoMovimiento(idTipoMovimiento);
	}

}
