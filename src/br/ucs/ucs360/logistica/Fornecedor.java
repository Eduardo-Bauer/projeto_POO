package br.ucs.ucs360.logistica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ucs.ucs360.usuarios.informacoes.Pessoa;

public class Fornecedor extends Pessoa{
	private String descricao;
	private List<Produto> listaProdutos;
	private static List<Fornecedor> listaFornecedores = new ArrayList<>();
	private static int ultimoFornecedor = 0;
	private Scanner sc;
	
	public Fornecedor() {
		sc = new Scanner(System.in);
	}
	
	private void preencherDados() {
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
		listaFornecedores.add(this);
		ultimoFornecedor++;
		System.out.println("Fornecedor cadastrado com sucesso!");
	}
	
	public void adicionarProduto(Produto produto) {
		this.listaProdutos.add(produto);
	}
	
	public void cadastrarFornecedor() {
		preencherDados(ultimoFornecedor);
		preencherDados();
		listaProdutos = new ArrayList<>();
		adicionarFornecedor();
	}
	
	public Fornecedor escolherFornecedor() {
		int numero;
		
		if(Fornecedor.getListaFornecedores().size() == 0) {
			System.out.println("É necessário cadastrar um fornecedor antes");
			return null;
		}
		
		while(true){
			consultarFornecedores();
		
			System.out.print("Digite o numero do fornecedor: ");
			numero = sc.nextInt();
			sc.nextLine();
			
			for(int i = 0; i < listaFornecedores.size(); i++) {
				if(listaFornecedores.get(i).getId() == numero) {
					return listaFornecedores.get(i);
				}
			}
			System.out.println("Opção inválida");
			continue;	
		} 
	}
	
	public Produto escolherProduto() {
		int numero;
		
		if(this.getListaProdutos().size() == 0) {
			System.out.println("É necessário cadastrar um produto antes");
			return null;
		}
		
		while(true){
			consultarFornecedores();
		
			System.out.print("Digite o numero do produto vinculado: ");
			numero = sc.nextInt();
			sc.nextLine();
			
			for(int i = 0; i < this.getListaProdutos().size(); i++) {
				if( this.getListaProdutos().get(i).getId() == numero) {
					return  this.getListaProdutos().get(i);
				}
			}
			System.out.println("Opção inválida");
			continue;	
		} 
	}
	
	private void mostrarFornecedor(Fornecedor fornecedor) {
		System.out.println("---------------------------------------------");
		System.out.println("Fornecedor encontrado:");
		System.out.println(fornecedor);
		System.out.println("");
	}
	
	private void consultarProdutos(Fornecedor fornecedor) {
		for(Produto produto : fornecedor.getListaProdutos()) {
			System.out.println(produto);
		}
	}
	
	public void consultarFornecedores() {
		System.out.println("---------------------------------------------");
		System.out.println("Lista de fornecedores:");
		for(Fornecedor fornecedor : listaFornecedores) {
			System.out.println(fornecedor);
			System.out.println("Produtos vinculados: ");
			consultarProdutos(fornecedor);
			System.out.println("");
		}
	}
	
	public void consultarFornecedorEspecifico(String[] filtro) {
		boolean encontrado = false;
	    String id;
	    
	    if(filtro == null) {
	    	return;
	    }
	    
	    if(filtro[2] != null) {
	    	consultarFornecedores();
	    	return;
	    }
	    
		if(filtro[0] != null) {
			for(Fornecedor fornecedor : listaFornecedores) {
				id = Integer.toString(fornecedor.getId());
				if(filtro[0].equals(id)) {
					mostrarFornecedor(fornecedor);
					encontrado = true;
					break;
				}
			}
		}else{
			for(Fornecedor fornecedor : listaFornecedores) {
				if(filtro[1].equals(fornecedor.getNome())){
					mostrarFornecedor(fornecedor);
					encontrado = true;
					break;
				}
			}
		}
		
		if(!encontrado) {
			System.out.println("produto não encontrado");
		}
	}
	
	private void vincularProduto(Produto produto) {
		System.out.println("");
		System.out.println("Escolha o novo fonecedor: ");
		Fornecedor fornecedor = escolherFornecedor();
		if(fornecedor != null) {
			produto.setFornecedor(fornecedor);
			fornecedor.adicionarProduto(produto);
			System.out.println("Novo fornecedor vinculado com sucesso!");
		}
	}

	public void desvincularProduto(Produto produto) {
		this.getListaProdutos().remove(produto);
		vincularProduto(produto);
	}
	
	public void removerFornecedor(Fornecedor fornecedor) {
		listaFornecedores.remove(fornecedor);
		System.out.println("Fornecedor removido com sucesso!");
	}
	
	public void atualizarDescricao() {
		System.out.print("Digite a nova descrição: ");
		this.setDescricao(sc.nextLine());
		System.out.println("Descricao atualizada com sucesso!");
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
		return "Fornecedor: " + this.getId() + " | nome: " + this.getNome() + " | descrição: " + this.getDescricao();
	}
}