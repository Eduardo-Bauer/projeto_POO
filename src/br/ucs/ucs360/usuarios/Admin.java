package br.ucs.ucs360.usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.ucs.ucs360.logistica.Pedido;
import br.ucs.ucs360.usuarios.informacoes.Dado;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Admin {
	private Dado dado;
	private List<Pedido> pedidos;
	
	public Admin() {
		dado = new Dado();
		pedidos = new ArrayList<Pedido>();
	}
	
	public Pedido consultarIdPedido(String id) {
		String idPedido;
		
		for(Pedido pedido : this.pedidos) {
			idPedido = Integer.toString(pedido.getNumero());
			if(id.equals(idPedido)) {
				return pedido;
			}
		}
		return null;
	}
	
	public List<Pedido> consultarDataPedido(LocalDate[] datas){
		List<Pedido> listaPedidoEncontrado  = new ArrayList<>();
		for(Pedido pedido : this.pedidos) {
			if(pedido.getDataPedido().isAfter(datas[0]) && pedido.getDataPedido().isBefore(datas[1])){
				listaPedidoEncontrado.add(pedido);
			}
		}
		return listaPedidoEncontrado;
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
	
	public int conferirEntrada(List<Admin> admins) {
		for(int i = 0; i < admins.size(); i++) {
			if(conferirSenha(admins.get(i)) && conferirLogin(admins.get(i))) {
				return i;
			}
		}
		return -1;
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

	public void setPedidos(List<Pedido> pedido) {
		this.pedidos = pedido;
	}
}
