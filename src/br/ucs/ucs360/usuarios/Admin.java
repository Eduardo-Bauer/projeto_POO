package br.ucs.ucs360.usuarios;

import java.util.ArrayList;
import java.util.List;

import br.ucs.ucs360.informacoes.Dado;
import br.ucs.ucs360.logistica.Pedido;

public class Admin {
	private Dado dado;
	private List<Pedido> pedidos;
	private static List<Admin> admins = new ArrayList<>();
	
	public Admin() {
		
	}
	
	private void adicionarAdmin() {
		for(Admin admin : Admin.admins) {
			if(admin.getDado().getLogin().equals(this.getDado().getLogin())) {
				System.out.println("Admin já cadastrado, tente fazer o login!");
				return;
			}
		}
		System.out.println("Admin cadastrado com sucesso!");
		admins.add(this);
	}
	
	public void cadastrarAdmin() {
		dado = new Dado(true);
		pedidos = new ArrayList<Pedido>();
		adicionarAdmin();
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
	
	public int conferirEntrada() {
		dado = new Dado(true);
		for(int i = 0; i < admins.size(); i++) {
			if(conferirSenha(admins.get(i)) && conferirLogin(admins.get(i))) {
				System.out.println("Continuar");
				return i;
			}
		}
		System.out.println("Login ou senha incorretos!");
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
