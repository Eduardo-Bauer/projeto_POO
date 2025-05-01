package Principal;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
	private String cartaoCredito;
	private Dado dado;
	private List<Pedido> pedido;
	
	public Cliente() {
		
		dado = Funcionalidades.preencherDados(false);
		pedido = new ArrayList<Pedido>();
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
