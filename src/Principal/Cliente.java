package Principal;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
	private String cartaoCredito;
	private Dado dado;
	private List<Pedido> pedido;
	private static List<Cliente> clientes = new ArrayList<>();
	
	public Cliente() {
		
	}
	
	private boolean conferirDados() {
		for (Cliente cliente : Cliente.clientes) {
			if(cliente.getDado().getLogin().equals(this.getDado().getLogin())) {
				return true;
			}
		}
		return false;
	}
	
	private void adicionarCliente() {
		for(Cliente cliente : Cliente.clientes) {
			if(cliente.getNome().equals(this.getNome())) {
				System.out.println("Nome já em uso!");
				return;
			}
		}
		System.out.println((conferirDados() ? "Login já em uso!" : "Cliente cadastrado com sucesso!"));
		Cliente.clientes.add(this);
	}
	
	public void cadastrarCliente() {
		this.preencherDados();
		dado = new Dado(false);
		pedido = new ArrayList<Pedido>();
		adicionarCliente();
	}
	
	public boolean conferirEntrada() {
		dado = new Dado(false);
		for(Cliente cliente : Cliente.clientes) {
			if(cliente.getDado().getClass() == this.getDado().getClass()) {
				System.out.println("Continuar");
				return true;
			}
		}
		System.out.println("Login ou senha incorretos!");
		return false;
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
