package Negocio;

import java.util.List;

import Dominio.ClienteSaldo;

public interface iClienteSaldoNegocio {
	
	List<ClienteSaldo> obtenerClientesConSaldoMayor(float saldoAComparar, boolean esMayor);

}
