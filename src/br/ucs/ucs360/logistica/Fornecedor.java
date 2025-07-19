package br.ucs.ucs360.logistica;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.ucs.ucs360.usuarios.informacoes.Pessoa;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Fornecedor extends Pessoa{
	private String descricao;
	private List<Produto> listaProdutos;
	private static int ultimoFornecedor = 0;
	
	public Fornecedor() {
		listaProdutos = new ArrayList<>();
	}
	
	public String getDescricao() {
		return descricao;	
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public static int getUltimoFornecedor() {
		return ultimoFornecedor;
	}

	public static void setUltimoFornecedor(int ultimoFornecedor) {
		Fornecedor.ultimoFornecedor = ultimoFornecedor;
	}
	
	public String toString() {
		return "Fornecedor: " + this.getId() + " | nome: " + this.getNome() + " | descrição: " + this.getDescricao();
	}
}