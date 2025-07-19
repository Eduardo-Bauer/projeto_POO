package br.ucs.ucs360.comparadores;

import java.util.Comparator;

import br.ucs.ucs360.logistica.Pedido;

public class PedidoPrecoComparator implements Comparator<Pedido>{
	public int compare(Pedido o1, Pedido o2) {
		return (int) (o1.getPrecoTotal() - o2.getPrecoTotal());
	}
}
