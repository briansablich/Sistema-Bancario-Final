package Negocio;

import java.util.List;

import Dao.ClienteSaldoDao;
import Dao.iClienteSaldoDao;
import Dominio.ClienteSaldo;

public class ClienteSaldoNegocio implements iClienteSaldoNegocio {

	private iClienteSaldoDao iCSN;
	
	public ClienteSaldoNegocio(){
		this.iCSN = new ClienteSaldoDao();
	}
	@Override
	public List<ClienteSaldo> obtenerClientesConSaldoMayor(float saldoAComparar, boolean esMayor) {
		return iCSN.obtenerClientesConSaldoMayor(saldoAComparar, esMayor);
	}

}
