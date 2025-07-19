package br.ucs.ucs360.comparadores;

import java.util.Comparator;

import br.ucs.ucs360.logistica.Fornecedor;

public class FornecedorDescrucaoComparator implements Comparator<Fornecedor>{
	public int compare(Fornecedor o1, Fornecedor o2) {
		return o1.getDescricao().compareTo(o2.getDescricao());
	}
}
