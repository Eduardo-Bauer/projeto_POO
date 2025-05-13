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
	
	private int escolherFornecedor() {
		int numero = -1;
		boolean valido = false;
		
		if(Fornecedor.getListaFornecedores().size() == 0) {
			System.out.println("É necessário cadastrar um fornecedor antes");
			return numero;
		}
		do {
			new Fornecedor().consultarFornecedores();
		
			System.out.print("Digite o numero do fornecedor: ");
			numero = sc.nextInt();
			sc.nextLine();
			
			if(numero < 0 || numero > Fornecedor.getUltimoFornecedor()-1) {
				System.out.println("Opção inválida");
				continue;
			}
			valido = true;
			
		} while(!valido);
		return numero;
	}
	
	private void preencherDados(int fornecedor) {
		System.out.print("Digite o nome do produto: ");
		this.setNome(sc.nextLine());
		
		System.out.print("Digite a Descrição do produto: ");
		this.setDescricao(sc.nextLine());
		
		this.setFornecedor(Fornecedor.getListaFornecedores().get(fornecedor));
	}
	
	private void adcionarProduto() {
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
	
	public void cadastrarProduto() {
		int fornecedor = escolherFornecedor();
		
		if(fornecedor != -1) {
			preencherDados(fornecedor);
			adcionarProduto();
			Fornecedor.getListaFornecedores().get(fornecedor).adicionarProdutoFornecedor(this);
		}
	}
	
	public void consultarProdutos() {
		System.out.println("---------------------------------------------");
		System.out.println("Lista de produtos:");
		for(Produto produto : listaProdutos) {
			System.out.println(produto);
		}
	}
	
	private void mostrarProduto(Produto produto) {
		System.out.println("---------------------------------------------");
		System.out.println("Produto encontrado:");
		System.out.println(produto);
	}
	
	public void consultarProdutoEspecifico(String[] filtro) {
		boolean encontrado = false;
	    String id;
	    if(filtro == null) {
	    	consultarProdutos();
	    	return;
	    }
	    
		if(filtro[0] != null) {
			for(Produto produto : Produto.listaProdutos) {
				id = Integer.toString(produto.getId());
				if(filtro[0].equals(id)) {
					mostrarProduto(produto);
					encontrado = true;
					break;
				}
			}
		}else{
			for(Produto produto : Produto.listaProdutos) {
				if(filtro[1].equals(produto.getNome())) {
					mostrarProduto(produto);
					encontrado = true;
					break;
				}
			}
		}
		if(!encontrado) {
			System.out.println("produto não encontrado");
		}
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
		return "numero: " + this.getId() + " | nome: " + this.getNome() + " | descrição: " + this.getDescricao() + " | Estoque: " + this.getEstoque() + " | fornecedor: " + this.getFornecedor();
	}
}