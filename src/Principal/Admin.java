package Principal;

import java.util.ArrayList;
import java.util.List;

public class Admin {
	private Dado dado;
	private List<Pedido> pedido;
	
	public Admin() {
		dado = Funcionalidades.preencherDados(true);
		pedido = new ArrayList<Pedido>();
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
