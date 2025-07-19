package br.ucs.ucs360.comparadores;

import java.util.Comparator;

import br.ucs.ucs360.logistica.Pedido;

public class PedidoDataComparator implements Comparator<Pedido>{
	public int compare(Pedido o1, Pedido o2) {
		return o1.getDataPedido().compareTo(o2.getDataPedido());
	}
}
