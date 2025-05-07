package Principal;

import java.util.ArrayList;
import java.util.List;

public class Admin {
	private Dado dado;
	private List<Pedido> pedidos;
	private static List<Admin> admins = new ArrayList<>();
	
	private void addAdmin() {
		for(int i = 0; i < admins.size(); i++) {
			if(admins.get(i) != null && admins.get(i).getDado().getLogin().equals(this.dado.getLogin())) {
				System.out.println("Admin já criado");
				return;
			}
		}
		System.out.println("Admin cadastrado com sucesso!");
		admins.add(this);
	}
	
	public Admin() {
		dado = new Dado(true);
		pedidos = new ArrayList<Pedido>();
		addAdmin();
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
