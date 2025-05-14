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
	
	public void removerFornecedor() {
		int fornecedor = escolherFornecedor();
		
		for(Produto produto : listaFornecedores.get(fornecedor).getListaProdutos()) {
			produto.setFornecedor(null);
		}
		
		listaFornecedores.remove(fornecedor);
		
		System.out.println("Fornecedor removido com sucesso!");
	}
	
	public void removerProduto(Produto produto) {
		for(Fornecedor fornecedor : listaFornecedores) {
			if(fornecedor.getNome().equals(produto.getFornecedor().getNome())) {
				fornecedor.getListaProdutos().remove(produto);
				produto.setFornecedor(null);
			}
		}
		System.out.println("Produto removido com sucesso!");
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
			System.out.println("Seus produtos vinculados: ");
			consultarProdutos(fornecedor);
		}
	}
	
	private void mostrarFornecedor(Fornecedor fornecedor) {
		System.out.println("---------------------------------------------");
		System.out.println("Fornecedor encontrado:");
		System.out.println(fornecedor);
	}
	
	public void consultarFornecedorEspecifico(String[] filtro) {
		boolean encontrado = false;
	    String id;
	    if(filtro == null) {
	    	return;
	    }else {
	    	if(filtro[2] != null) {
	    		consultarFornecedores();
	    		return;
	    	}
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
	
	public int escolherFornecedor() {
		int numero = -1;
		
		if(Fornecedor.getListaFornecedores().size() == 0) {
			System.out.println("É necessário cadastrar um fornecedor antes");
			return numero;
		}
		while(true){
			consultarFornecedores();
		
			System.out.print("Digite o numero do fornecedor: ");
			numero = sc.nextInt();
			sc.nextLine();
			
			for(int i = 0; i < listaFornecedores.size(); i++) {
				if(listaFornecedores.get(i).getId() == numero) {
					return i;
				}
			}
			System.out.println("Opção inválida");
			continue;	
			
		} 
	}
	
	public void atualizarDescricao(int fornecedor) {
		System.out.print("Digite a nova descrição: ");
		listaFornecedores.get(fornecedor).setDescricao(sc.nextLine());
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
		return "numero: " + this.getId() + " | nome: " + this.getNome() + " | descrição: " + this.getDescricao();
	}
}