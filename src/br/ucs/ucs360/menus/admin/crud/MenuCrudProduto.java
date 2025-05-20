package br.ucs.ucs360.menus.admin.crud;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.ucs.ucs360.logistica.Estoque;
import br.ucs.ucs360.logistica.Fornecedor;
import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.logistica.Produto;
import br.ucs.ucs360.menus.MenuConsulta;
import br.ucs.ucs360.menus.atualizacao.MenuAtualizacaoProduto;

public class MenuCrudProduto {
	private Scanner sc;
	private Produto produto;
	private Fornecedor fornecedor;
	
	public MenuCrudProduto(Loja loja) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione o que fazer:");
				System.out.println("1 - Cadastrar produto");
				System.out.println("2 - Consultar produto");
				System.out.println("3 - Atualizar produto");
				System.out.println("4 - Remover produto");
				System.out.println("5 - Cadastrar estoque");
				System.out.println("0 - Voltar para o menu admin");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				switch(opcao) {
				case 1:
					fornecedor = escolherFornecedor(loja.getListaFornecedores());
					if(fornecedor != null) {
						System.out.println((loja.adicionarProduto(cadastrarProduto(fornecedor)) ? "Produto cadastrado com sucesso!" : "Produto já cadastrado"));
						
					}else {
						System.out.println("É necessário cadastrar um fornecedor antes");
					}
					break;
					
				case 2:
					String[] filtro = new MenuConsulta().consultaEspecifica();
					if(filtro != null) {
						if(filtro[2] != null) {
							mostrarTodosProdutos(loja.getListaProdutos());
							
						}else {
							mostrarProduto(loja.consultarProduto(filtro));
						}
					}
					break;
					
				case 3:
					produto = escolherProduto(loja.getListaProdutos());
					if(fornecedor != null) {
						new MenuAtualizacaoProduto(produto, loja);
						
					}else {
						System.out.println("É necessário cadastrar um produto antes");
					}
					break;
					
				case 4:
					produto = escolherProduto(loja.getListaProdutos());
					if(produto != null) {
						loja.removerProduto(produto);
						System.out.println("Produto removido com sucesso!");
						
					}else {
						System.out.println("É necessário cadastrar um produto antes");
					}
					break;
							
				case 5:
					produto = escolherProduto(loja.getListaProdutos());
					if(produto != null) {
						System.out.println((loja.adicionarEstoque(cadastrarEstoque(produto)) ? "Estoque cadastrado com sucesso!" : "Esse produto já tem um estoque"));
						
					}else {
						System.out.println("É necessário cadastrar um produto antes");
					}
					break;	
					
				case 0:
					System.out.println("Voltando...");
					break;
				
				default: 
					System.out.println("Opção inválida");
					break;
				}
				
			}catch(InputMismatchException e) {
                System.out.println("Digite apenas números válidos.");
                opcao = -1;
                sc.nextLine();
            }
			
		}while(opcao != 0);
	}
	
	private Estoque cadastrarEstoque(Produto produto) {
		Estoque estoque = new Estoque();
		
		System.out.print("Digite a quantidade: ");
		estoque.setQuantidade(sc.nextInt());
		
		System.out.print("Digite o preco do produto: ");
		estoque.setPreco(sc.nextDouble());
		
		estoque.setId(Estoque.getUltimoEstoque());
		estoque.setProduto(produto);
		
		return estoque;
	}
	
	private Produto cadastrarProduto(Fornecedor fornecedor) {
		Produto produto = new Produto();
		
		System.out.print("Digite nome: ");
		produto.setNome(sc.nextLine());
		
		System.out.print("Digite uma descrição: ");
		produto.setDescricao(sc.nextLine());
		
		produto.setId(Produto.getUltimoProduto());
		produto.setFornecedor(fornecedor);
		
		return produto;
	}
	
	private void mostrarProdutosVinculados(Fornecedor fornecedor) {
		for(Produto produto : fornecedor.getListaProdutos()) {
			System.out.println(produto);
		}
	}
	
	private void mostrarTodosFornecedores(List<Fornecedor> listaFornecedores) {
		System.out.println("---------------------------------------------");
		System.out.println("Lista de fornecedores:");
		for(Fornecedor fornecedor : listaFornecedores) {
			System.out.println(fornecedor);
			System.out.println("Produtos vinculados: ");
			mostrarProdutosVinculados(fornecedor);
			System.out.println("");
		}
	}
	
	private void mostrarProduto(Produto produto) {
		if(produto == null) {
			System.out.println("Produto não encontrado");
			return;
		}
		System.out.println("---------------------------------------------");
		System.out.println("Produto encontrado:");
		System.out.println(produto);
		System.out.println("");
	}
	
	private void mostrarTodosProdutos(List<Produto> listaProdutos) {
		System.out.println("---------------------------------------------");
		System.out.println("Lista de produtos:");
		for(Produto produto : listaProdutos) {
			System.out.println(produto);
			System.out.println("Fornecedor vinculado: ");
			System.out.println(produto.getFornecedor());
			System.out.println("Estoque vinculado: ");
			System.out.println(produto.getEstoque());
			System.out.println("");
		}
	}
	
	private Produto escolherProduto(List<Produto> listaProdutos) {
		int numero;
		
		if(listaProdutos.size() == 0) {
			return null;
		}
		
		while(true){
			mostrarTodosProdutos(listaProdutos);
		
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
	
	private Fornecedor escolherFornecedor(List<Fornecedor> listaFornecedores) {
		int numero;
		
		if(listaFornecedores.size() == 0) {
			return null;
		}
		
		while(true){
			mostrarTodosFornecedores(listaFornecedores);
		
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
}
