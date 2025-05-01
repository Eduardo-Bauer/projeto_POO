package Principal;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
	private int quantidade;
	private double preco;
	 private static List<Estoque> listaEstoque = new ArrayList<>();
	
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

	public static List<Estoque> getListaEstoque() {
		return listaEstoque;
	}

	public static void setListaEstoque(List<Estoque> listaEstoque) {
		Estoque.listaEstoque = listaEstoque;
	}
}
