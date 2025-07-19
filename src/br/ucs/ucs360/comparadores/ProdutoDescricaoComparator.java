package br.ucs.ucs360.comparadores;

import java.util.Comparator;

import br.ucs.ucs360.logistica.Produto;

public class ProdutoDescricaoComparator implements Comparator<Produto>{
	public int compare(Produto o1, Produto o2) {
		return o1.getDescricao().compareTo(o2.getDescricao());
	}
}
