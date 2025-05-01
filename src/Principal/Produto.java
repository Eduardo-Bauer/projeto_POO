package Principal;

import java.util.ArrayList;
import java.util.List;

public class Produto {
	private String nome;
	private String descricao;
	private static List<Produto> listaProdutos = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public static void setListaProdutos(List<Produto> listaProdutos) {
		Produto.listaProdutos = listaProdutos;
	}
}
