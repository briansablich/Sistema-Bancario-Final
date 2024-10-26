package Dao;

import java.util.List;

import Dominio.ClienteSaldo;

public interface iClienteSaldoDao {
	
	List<ClienteSaldo> obtenerClientesConSaldoMayor(float saldoAComparar, boolean esMayor);
}
