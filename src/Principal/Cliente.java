package Principal;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
	private String cartaoCredito;
	private Dado dado;
	private List<Pedido> pedido;
	private static List<Cliente> clientes = new ArrayList<>();
	
	private void addCliente() {
		for(int i = 0; i < clientes.size(); i++) {
			if(clientes.get(i) != null && (clientes.get(i).getNome().equals(this.getNome()) || clientes.get(i).getDado().getLogin().equals(this.getDado().getLogin()))) {
				System.out.println("Usuario já cadastrado");
				return;
			}
		}
		System.out.println("Cliente cadastrado com sucesso!");
		clientes.add(this);
	}
	
	public Cliente() {
		dado = new Dado(false);
		pedido = new ArrayList<Pedido>();
		addCliente();
	}

	public String getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(String cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public Dado getDado() {
		return dado;
	}

	public void setDado(Dado dado) {
		this.dado = dado;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}
}
