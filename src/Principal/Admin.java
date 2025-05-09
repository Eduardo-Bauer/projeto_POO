package Principal;

import java.util.ArrayList;
import java.util.List;

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
	
	public boolean conferirEntrada() {
		dado = new Dado(true);
		for(Admin admin : Admin.admins) {
			if(admin.getDado().getClass() == this.getDado().getClass()) {
				System.out.println("Continuar");
				return true;
			}
		}
		System.out.println("Login ou senha incorretos!");
		return false;
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
