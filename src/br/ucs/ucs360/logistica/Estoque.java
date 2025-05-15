package br.ucs.ucs360.logistica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Estoque {
	private int id;
	private int quantidade;
	private double preco;
	private Produto produto;
	private static List<Estoque> listaEstoques = new ArrayList<>();
	private static int ultimoEstoque = 0;
	private Scanner sc;
	
	public Estoque() {
		sc = new Scanner(System.in);
	}
	
	private void preencherDados(Produto produto) {
		System.out.print("Digite a quantidade: ");
		this.setQuantidade(sc.nextInt());
		
		System.out.print("Digite o preco do produto: ");
		this.setPreco(sc.nextDouble());
		
		this.setId(ultimoEstoque++);
		this.setProduto(produto);
		listaEstoques.add(this);
		System.out.println("Estoque cadastrado com sucesso!");
	}
	
	public void cadastrarEstoque(Produto produto) {
		if(produto.getEstoque() == null) {
			preencherDados(produto);
			produto.setEstoque(this);
		}else {
			System.out.println("Esse produto já tem um estoque");
		}
	}
	
	public Estoque escolherEstoque() {
		int numero;
		
		if(Estoque.getListaEstoque().size() == 0) {
			System.out.println("É necessário cadastrar um estoque antes");
			return null;
		}
		
		while(true){
			consultarEstoques();
		
			System.out.print("Digite o numero do estoque: ");
			numero = sc.nextInt();
			sc.nextLine();
			
			for(int i = 0; i < listaEstoques.size(); i++) {
				if(listaEstoques.get(i).getId() == numero) {
					return listaEstoques.get(i);
				}
			}
			System.out.println("Opção inválida");
			continue;	
		} 
	}
	
	private void mostrarEstoque(Estoque estoque) {
		System.out.println("---------------------------------------------");
		System.out.println("Estoque encontrado:");
		System.out.println(estoque);
		System.out.println("Produto vinculado: ");
		System.out.println(estoque.getProduto());
	}
	
	public void consultarEstoques() {
		System.out.println("---------------------------------------------");
		System.out.println("Lista de estoques:");
		for(Estoque estoque : listaEstoques) {
			System.out.println(estoque);
			System.out.println("Produto vinculado: ");
			System.out.println(estoque.getProduto());
			System.out.println("");
		}
	}
	
	public void consultarEstoqueEspecifico(String[] filtro) {
		boolean encontrado = false;
	    String id;
	    if(filtro == null) {
	    	return;
	    }
	    
	    if(filtro[2] != null) {
	    	consultarEstoques();
	    	return;
	    }
	    
		if(filtro[0] != null) {
			for(Estoque estoque : listaEstoques) {
				id = Integer.toString(estoque.getId());
				if(filtro[0].equals(id)) {
					mostrarEstoque(estoque);
					encontrado = true;
					break;
				}
			}
		}else{
			for(Estoque estoque : listaEstoques) {
				if(filtro[1].equals(estoque.getProduto().getNome())) {
					mostrarEstoque(estoque);
					encontrado = true;
					break;
				}
			}
		}
		
		if(!encontrado) {
			System.out.println("estoque não encontrado");
		}
	}
	
	public void removerEstoque() {
		this.getProduto().setEstoque(null);
		listaEstoques.remove(this);
		System.out.println("Estoque removido com sucesso!");
	}
	
	public void atualizarQuantidade() {
		System.out.print("Digite a nova Quantidade: ");
		this.setQuantidade(sc.nextInt());
		System.out.println("Quantidade atualizada com sucesso!");
	}
	
	public void atualizarPreco() {
		System.out.print("Digite a novo Preço: ");
		this.setPreco(sc.nextDouble());
		System.out.println("Preço atualizado com sucesso!");
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
		return listaEstoques;
	}

	public static void setListaEstoque(List<Estoque> listaEstoque) {
		Estoque.listaEstoques = listaEstoque;
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
