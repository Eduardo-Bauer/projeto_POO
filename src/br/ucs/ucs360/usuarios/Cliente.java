package br.ucs.ucs360.usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ucs.ucs360.logistica.Pedido;
import br.ucs.ucs360.usuarios.informacoes.Dado;
import br.ucs.ucs360.usuarios.informacoes.Pessoa;

public class Cliente extends Pessoa{
	private String cartaoCredito;
	private Dado dado;
	private List<Pedido> pedidos;
	private static List<Cliente> clientes = new ArrayList<>();
	private static int ultimoCliente = 0;
	private Scanner sc;
	
	public Cliente() {
		sc = new Scanner(System.in);
	}
	
	private void preencherDadosCliente() {
		System.out.print("Digite o numero do cartão de crédito");
		this.setCartaoCredito(sc.nextLine());
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
		ultimoCliente++;
	}
	
	public void cadastrarCliente() {
		preencherDados(ultimoCliente);
		preencherDadosCliente();
		dado = new Dado(false);
		pedidos = new ArrayList<Pedido>();
		adicionarCliente();
	}
	
	public int conferirEntrada() {
		dado = new Dado(false);
		for(int i = 0; i < clientes.size(); i++) {
			if(clientes.get(i).getDado().getLogin().equals(this.getDado().getLogin()) && clientes.get(i).getDado().getSenha().equals(this.getDado().getSenha())) {
				System.out.println("Continuar");
				return i;
			}
		}
		System.out.println("Login ou senha incorretos!");
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

	public static List<Cliente> getClientes() {
		return clientes;
	}

	public static void setClientes(List<Cliente> clientes) {
		Cliente.clientes = clientes;
	}

	public static int getUltimoCliente() {
		return ultimoCliente;
	}

	public static void setUltimoCliente(int ultimoCliente) {
		Cliente.ultimoCliente = ultimoCliente;
	}
}
