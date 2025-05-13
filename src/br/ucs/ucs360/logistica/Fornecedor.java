package br.ucs.ucs360.logistica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ucs.ucs360.informacoes.Pessoa;

public class Fornecedor extends Pessoa{
	private String descricao;
	private List<Produto> listaProdutos;
	private static List<Fornecedor> listaFornecedores = new ArrayList<>();
	private static int ultimoFornecedor = 0;
	private Scanner sc;
	
	public Fornecedor() {
		sc = new Scanner(System.in);
	}
	
	private void preencherDadosFornecedor() {
		System.out.print("Digite a Descrição do fornecedor: ");
		this.setDescricao(sc.nextLine());
	}
	
	private void adicionarFornecedor() {
		for(Fornecedor fornecedor : Fornecedor.listaFornecedores) {
			if(fornecedor.getNome().equals(this.getNome())) {
				System.out.println("Fornecedor já cadastrado");
				return;
			}
		}
		System.out.println("Fornecedor cadastrado com sucesso!");
		listaFornecedores.add(this);
		ultimoFornecedor++;
	}
	
	public void cadastrarFornecedor() {
		preencherDados(ultimoFornecedor);
		preencherDadosFornecedor();
		listaProdutos = new ArrayList<>();
		adicionarFornecedor();
	}
	
	public void adicionarProdutoFornecedor(Produto produto) {
		this.listaProdutos.add(produto);
	}
	
	public void consultarFornecedores() {
		System.out.println("---------------------------------------------");
		System.out.println("Lista de fornecedores:");
		for(int i = 0; i < Fornecedor.getListaFornecedores().size(); i++) {
			System.out.println(Fornecedor.getListaFornecedores().get(i));
		}
		System.out.println("---------------------------------------------");
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

	public static List<Fornecedor> getListaFornecedores() {
		return listaFornecedores;
	}

	public static void setListaFornecedores(List<Fornecedor> listaFornecedores) {
		Fornecedor.listaFornecedores = listaFornecedores;
	}

	public static int getUltimoFornecedor() {
		return ultimoFornecedor;
	}

	public static void setUltimoFornecedor(int ultimoFornecedor) {
		Fornecedor.ultimoFornecedor = ultimoFornecedor;
	}
	
	public String toString() {
		return "numero: " + this.getId() + " | nome: " + this.getNome() + " | descrição: " + this.getDescricao();
	}
}