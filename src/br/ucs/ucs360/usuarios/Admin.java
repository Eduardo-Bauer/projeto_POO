package br.ucs.ucs360.usuarios;

import java.util.ArrayList;
import java.util.List;

import br.ucs.ucs360.logistica.Pedido;
import br.ucs.ucs360.usuarios.informacoes.Dado;

public class Admin {
	private Dado dado;
	private List<Pedido> pedidos;
	
	public Admin() {
		dado = new Dado();
		pedidos = new ArrayList<Pedido>();
	}
	
	private boolean conferirSenha(Admin admin) {
		if(admin.getDado().getSenha().equals(this.getDado().getSenha())) {
			return true;
		}
		return false;
	}
	
	private boolean conferirLogin(Admin admin) {
		if(admin.getDado().getLogin().equals(this.getDado().getLogin())) {
			return true;
		}
		return false;
	}
	
	public int conferirEntrada(List<Admin> admins) {
		for(int i = 0; i < admins.size(); i++) {
			if(conferirSenha(admins.get(i)) && conferirLogin(admins.get(i))) {
				return i;
			}
		}
		return -1;
	}
	
	public Dado getDado() {
		return dado;
	}

	public void setDado(Dado dado) {
		this.dado = dado;
	}

	public List<Pedido> getPedido() {
		return pedidos;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedidos = pedido;
	}
}
