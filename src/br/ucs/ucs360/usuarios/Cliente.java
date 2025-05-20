package br.ucs.ucs360.usuarios;

import java.util.ArrayList;
import java.util.List;

import br.ucs.ucs360.logistica.Pedido;
import br.ucs.ucs360.usuarios.informacoes.Dado;
import br.ucs.ucs360.usuarios.informacoes.Pessoa;

public class Cliente extends Pessoa{
	private String cartaoCredito;
	private Dado dado;
	private List<Pedido> pedidos;
	private static int ultimoCliente = 0;
	
	public Cliente() {
		dado = new Dado();
		pedidos = new ArrayList<Pedido>();
	}
	
	private boolean conferirSenha(Cliente cliente) {
		if(cliente.getDado().getSenha().equals(this.getDado().getSenha())) {
			return true;
		}
		return false;
	}
	
	private boolean conferirLogin(Cliente cliente) {
		if(cliente.getDado().getLogin().equals(this.getDado().getLogin())) {
			return true;
		}
		return false;
	}
	
	public int conferirEntrada(List<Cliente> clientes) {
		for(int i = 0; i < clientes.size(); i++) {
			if(conferirSenha(clientes.get(i)) && conferirLogin(clientes.get(i))) {
				return i;
			}
		}
		return -1;
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
		return pedidos;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedidos = pedido;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public static int getUltimoCliente() {
		return ultimoCliente;
	}

	public static void setUltimoCliente(int ultimoCliente) {
		Cliente.ultimoCliente = ultimoCliente;
	}
}
