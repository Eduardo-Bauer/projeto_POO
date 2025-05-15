package br.ucs.ucs360.logistica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Produto {
	private int id;
	private String nome;
	private String descricao;
	private Fornecedor fornecedor;
	private Estoque estoque;
	private static List<Produto> listaProdutos = new ArrayList<>();
	private static int ultimoProduto = 0;
	private Scanner sc;
	
	public Produto() {
		sc = new Scanner(System.in);
	}
	
	private void preencherDados(Fornecedor fornecedor) {
		System.out.print("Digite o nome do produto: ");
		this.setNome(sc.nextLine());
		
		System.out.print("Digite a Descrição do produto: ");
		this.setDescricao(sc.nextLine());
		
		this.setFornecedor(fornecedor);
	}
	
	private void adicionarProduto() {
		for(Produto produto : Produto.listaProdutos) {
			if(produto.getNome().equals(this.getNome())) {
				System.out.println("Produto já cadastrado");
				return;
			}
		}
		listaProdutos.add(this);
		
		this.setId(ultimoProduto++);
		
		System.out.println("Produto cadastrado com sucesso!");
	}
	
	public void cadastrarProduto(Fornecedor fornecedor) {
			preencherDados(fornecedor);
			adicionarProduto();
			fornecedor.adicionarProduto(this);
	}
	
	public Produto escolherProduto() {
		int numero;
		
		if(Produto.getListaProdutos().size() == 0) {
			System.out.println("É necessário cadastrar um produto antes");
			return null;
		}
		
		while(true) {
			consultarProdutos(false);
			
			System.out.print("Digite o numero do produto: ");
			
			numero = sc.nextInt();
			sc.nextLine();
			
			for(int i = 0; i < listaProdutos.size(); i++) {
				if(listaProdutos.get(i).getId() == numero) {
					return listaProdutos.get(i);
				}
			}
			System.out.println("Opção inválida");
			continue;	
		}
	}
	private void mostrarComplementares(Produto produto) {
		System.out.println(produto.getEstoque());
		System.out.println(produto.getFornecedor());
		System.out.println("");
	}
	
	private void mostrarProduto(Produto produto, boolean admin) {
		System.out.println("---------------------------------------------");
		System.out.println("Produto encontrado:");
		System.out.println(produto);
		if(admin) {
			mostrarComplementares(produto);
		}
	}
	
	public void consultarProdutos(boolean admin) {
		System.out.println("---------------------------------------------");
		System.out.println("Lista de produtos:");
		for(Produto produto : listaProdutos) {
			System.out.println(produto);
			if(admin) {
				mostrarComplementares(produto);
			}
		}
	}
	
	public void consultarProdutoEspecifico(String[] filtro, boolean admin) {
		boolean encontrado = false;
	    String id;
	    if(filtro == null) {
	    	return;
	    }
	    
	    if(filtro[2] != null) {
	    	consultarProdutos(admin);
	    	return;
	    }
	    
		if(filtro[0] != null) {
			for(Produto produto : Produto.listaProdutos) {
				id = Integer.toString(produto.getId());
				if(filtro[0].equals(id)) {
					mostrarProduto(produto, admin);
					encontrado = true;
					break;
				}
			}
		}else{
			for(Produto produto : Produto.listaProdutos) {
				if(filtro[1].equals(produto.getNome())) {
					mostrarProduto(produto, admin);
					encontrado = true;
					break;
				}
			}
		}
		
		if(!encontrado) {
			System.out.println("produto não encontrado");
		}
	}
	
	public void removerProduto(Produto produto) {
		Estoque.getListaEstoque().remove(produto.getEstoque());
		produto.getFornecedor().getListaProdutos().remove(produto);
		listaProdutos.remove(produto);
		System.out.println("Produto removido com sucesso!");
	}
	

	public void atualizarNome() {
		System.out.print("Digite o novo nome: ");
		this.setNome(sc.nextLine());
		System.out.println("Nome atualizado com sucesso!");
	}
	
	public void atualizarDescricao() {
		System.out.print("Digite a nova descricao: ");
		this.setDescricao(sc.nextLine());
		System.out.println("Descrição atualizado com sucesso!");
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

	public static List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public static void setListaProdutos(List<Produto> listaProdutos) {
		Produto.listaProdutos = listaProdutos;
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