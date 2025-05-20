package br.ucs.ucs360.logistica;

public class Produto {
	private int id;
	private String nome;
	private String descricao;
	private Fornecedor fornecedor;
	private Estoque estoque;
	private static int ultimoProduto = 0;
	
	public Produto() {
		
	}
	
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
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public static int getUltimoProduto() {
		return ultimoProduto;
	}

	public static void setUltimoProduto(int ultimoProduto) {
		Produto.ultimoProduto = ultimoProduto;
	}
	
	public String toString() {
		return "Produto: " + this.getId() + " | nome: " + this.getNome() + " | descrição: " + this.getDescricao();
	}
}