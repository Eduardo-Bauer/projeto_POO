package br.ucs.ucs360.logistica;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class ItemPedido{
	private int quantidade;
	private double preco;
	private Produto produto;
	
	public int getQuantidade(){
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
	
	public String toString() {
		return "Produto: " + this.getProduto().getNome() + " | Quantidade: " + this.getQuantidade() + " | Preço: " + this.getPreco();
	}
}
