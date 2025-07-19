package br.ucs.ucs360.comparadores;

import java.util.Comparator;

import br.ucs.ucs360.logistica.Pedido;

public class PedidoStatusComparator implements Comparator<Pedido>{
	public int compare(Pedido o1, Pedido o2) {
		return o1.getSituacao().compareTo(o2.getSituacao());
	}
}
