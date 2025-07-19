package br.ucs.ucs360.usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.ucs.ucs360.logistica.ItemPedido;
import br.ucs.ucs360.logistica.Pedido;
import br.ucs.ucs360.usuarios.informacoes.Dado;
import br.ucs.ucs360.usuarios.informacoes.Pessoa;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Cliente extends Pessoa{
	private String cartaoCredito;
	private Dado dado;
	private List<Pedido> pedidos;
	private static int ultimoCliente = 0;
	private List<ItemPedido> carrinho;
	
	public Cliente() {
		dado = new Dado();
		pedidos = new ArrayList<Pedido>();
		carrinho = new ArrayList<ItemPedido>();
	}
	
	public Pedido consultarIdPedido(String id) {
		String idPedido;
		
		for(Pedido pedido : pedidos) {
			idPedido = Integer.toString(pedido.getNumero());
			if(id.equals(idPedido)) {
				return pedido;
			}
		}
		return null;
	}
	
	public List<Pedido> consultarDataPedido(LocalDate[] datas){
		List<Pedido> listaPedidoEncontrado  = new ArrayList<>();
		for(Pedido pedido : pedidos) {
			if(pedido.getDataPedido().isAfter(datas[0]) && pedido.getDataPedido().isBefore(datas[1])){
				listaPedidoEncontrado.add(pedido);
			}
		}
		return listaPedidoEncontrado;
	}
	
	public void adicionarPedido(Pedido pedido) {
		pedidos.add(pedido);
	}
	
	public void adicionarProdutoCarrinho(ItemPedido itemPedido) {
		carrinho.add(itemPedido);
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

	public List<ItemPedido> getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(List<ItemPedido> carrinho) {
		this.carrinho = carrinho;
	}
}
