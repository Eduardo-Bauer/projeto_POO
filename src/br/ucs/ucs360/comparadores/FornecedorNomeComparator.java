package br.ucs.ucs360.comparadores;

import java.util.Comparator;

import br.ucs.ucs360.logistica.Fornecedor;

public class FornecedorNomeComparator implements Comparator<Fornecedor>{
	public int compare(Fornecedor o1, Fornecedor o2) {
		return o1.getNome().compareTo(o2.getNome());
	}
}
