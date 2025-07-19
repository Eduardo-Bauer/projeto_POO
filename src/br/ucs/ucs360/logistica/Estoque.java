package br.ucs.ucs360.logistica;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.ucs.ucs360.execoes.QuantidadeInsuficienteException;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Estoque {
	private int id;
	private int quantidade;
	private double preco;
	private Produto produto;
	private static int ultimoEstoque = 0;
	
	public Estoque() {
		
	}
	
	public void diminuirQuantidade(int quantidade) throws QuantidadeInsuficienteException {
		if(quantidade > this.quantidade) {
			throw new QuantidadeInsuficienteException();
		}
		this.quantidade -= quantidade;
	}
	
	public void aumentarQuantidade(int quantidade) {
		this.quantidade += quantidade;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public static int getUltimoEstoque() {
		return ultimoEstoque;
	}

	public static void setUltimoEstoque(int ultimoEstoque) {
		Estoque.ultimoEstoque = ultimoEstoque;
	}
	
	public String toString() {
		return "Estoque: " + this.getId() + " | Quantidade: " + this.getQuantidade() + " | preço: " + this.getPreco();
	}
}
