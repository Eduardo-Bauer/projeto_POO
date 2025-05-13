package br.ucs.ucs360.logistica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Estoque {
	private int id;
	private int quantidade;
	private double preco;
	private Produto produto;
	private static List<Estoque> listaEstoque = new ArrayList<>();
	private static int ultimoEstoque = 0;
	private Scanner sc;
	
	public Estoque() {
		sc = new Scanner(System.in);
	}
	
	private int escolherProduto() {
		int numero = -1;
		boolean valido = false;
		
		if(Produto.getListaProdutos().size() == 0) {
			System.out.println("É necessário cadastrar um produto antes");
			return numero;
		}
		do {
			new Produto().consultarProdutos();
		
			System.out.print("Digite o numero do produto: ");
			numero = sc.nextInt();
			sc.nextLine();
			
			if(numero < 0 || numero > Produto.getUltimoProduto() - 1) {
				System.out.println("Opção inválida");
				continue;
			}
			valido = true;
			
		} while(!valido);
		return numero;
	}
	
	private void preencherDados(int produto) {
		System.out.print("Digite a quantidade: ");
		this.setQuantidade(sc.nextInt());
		
		System.out.print("Digite o preco do produto: ");
		this.setPreco(sc.nextDouble());
		
		this.setId(ultimoEstoque++);
		
		this.setProduto(Produto.getListaProdutos().get(produto));
		
		System.out.println("Estoque atualizado com sucesso!");
	}
	
	public void cadastrarEstoque() {
		int produto = escolherProduto();
		
		if(produto != -1) {
			preencherDados(produto);
			Produto.getListaProdutos().get(produto).setEstoque(this);
		}
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

	public static List<Estoque> getListaEstoque() {
		return listaEstoque;
	}

	public static void setListaEstoque(List<Estoque> listaEstoque) {
		Estoque.listaEstoque = listaEstoque;
	}

	public static int getUltimoEstoque() {
		return ultimoEstoque;
	}

	public static void setUltimoEstoque(int ultimoEstoque) {
		Estoque.ultimoEstoque = ultimoEstoque;
	}
	
	public String toString() {
		return "Quantidade: " + this.getQuantidade() + " | preço: " + this.getPreco();
	}
}
