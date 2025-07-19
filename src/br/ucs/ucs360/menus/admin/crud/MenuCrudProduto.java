package br.ucs.ucs360.menus.admin.crud;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.ucs.ucs360.comparadores.FornecedorDescrucaoComparator;
import br.ucs.ucs360.comparadores.FornecedorNomeComparator;
import br.ucs.ucs360.comparadores.FornecedorCodigoComparator;
import br.ucs.ucs360.comparadores.ProdutoDescricaoComparator;
import br.ucs.ucs360.comparadores.ProdutoNomeComparator;
import br.ucs.ucs360.comparadores.ProdutoCodigoComparator;
import br.ucs.ucs360.dadosLoja.BancoDados;
import br.ucs.ucs360.execoes.ErroGravacaoException;
import br.ucs.ucs360.logistica.Estoque;
import br.ucs.ucs360.logistica.Fornecedor;
import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.logistica.Produto;
import br.ucs.ucs360.menus.MenuComparator;
import br.ucs.ucs360.menus.MenuConsulta;
import br.ucs.ucs360.menus.atualizacao.MenuAtualizacaoProduto;

public class MenuCrudProduto {
	private Scanner sc;
	
	public MenuCrudProduto(Loja loja) throws JsonProcessingException, ErroGravacaoException {
		sc = new Scanner(System.in);
		BancoDados bancoDados = new BancoDados();
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
					int ordenacao = new MenuComparator().fornecedorProdutoComparator();
					Fornecedor fornecedor = ordenarFornecedor(ordenacao, loja.getListaFornecedores());
					if(fornecedor != null) {
						System.out.println((loja.adicionarProduto(cadastrarProduto(fornecedor)) ? "Produto cadastrado com sucesso!" : "Produto já cadastrado"));

					}else {
						System.out.println("É necessário cadastrar um fornecedor antes");
					}
					break;
					
				case 2:
					String[] filtro = new MenuConsulta().consultaEspecifica();
					if(filtro != null) {
						if(filtro[0] != null) {
							mostrarProduto(loja.consultarIdProduto(filtro[0]));
						}else {
							int ordenacao1 = new MenuComparator().fornecedorProdutoComparator();
							if(filtro[1] != null) {
								ordenarProdutoMostrar(ordenacao1, loja.consultarNomeDescricaoProduto(filtro[1]));
							}else {
								ordenarProdutoMostrar(ordenacao1, loja.getListaProdutos());
							}
						}
					}
					break;
					
				case 3:
					int ordenacao2 = new MenuComparator().fornecedorProdutoComparator();
					Produto produto = ordenarProdutoEscolher(ordenacao2, loja.getListaProdutos());
					if(produto != null) {
						new MenuAtualizacaoProduto(produto, loja);
						
					}else {
						System.out.println("É necessário cadastrar um produto antes");
					}
					break;
					
				case 4:
					Produto produto1 = escolherProduto(loja.getListaProdutos());
					if(produto1 != null) {
						loja.removerProduto(produto1);
						System.out.println("Produto removido com sucesso!");
						bancoDados.gravaJSONLoja("banco_de_dados/loja.json", loja);
						
					}else {
						System.out.println("É necessário cadastrar um produto antes");
					}
					break;
							
				case 5:
					int ordenacao3 = new MenuComparator().fornecedorProdutoComparator();
					Produto produto2 = ordenarProdutoEscolher(ordenacao3, loja.getListaProdutos());
					if(produto2 != null) {
						System.out.println((loja.adicionarEstoque(cadastrarEstoque(produto2)) ? "Estoque cadastrado com sucesso!" : "Esse produto já tem um estoque"));
						
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
				bancoDados.gravaJSONLoja("banco_de_dados/loja.json", loja);
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
	
	private Fornecedor ordenarFornecedor(int ordenacao, List<Fornecedor> fornecedores) {
		switch(ordenacao) {
		
		case 1:
			Collections.sort(fornecedores, new FornecedorCodigoComparator());
			return escolherFornecedor(fornecedores);
		
		case 2:
			Collections.sort(fornecedores, new FornecedorNomeComparator());
			return escolherFornecedor(fornecedores);
			
		case 3:
			Collections.sort(fornecedores, new FornecedorDescrucaoComparator());
			return escolherFornecedor(fornecedores);
		}
		return null;
	}
	
	private void ordenarProdutoMostrar(int ordenacao, List<Produto> produtos) {
		switch(ordenacao) {
		case 1:
			Collections.sort(produtos, new ProdutoCodigoComparator());
			mostrarTodosProdutos(produtos);
			break;
		
		case 2:
			Collections.sort(produtos, new ProdutoNomeComparator());
			mostrarTodosProdutos(produtos);
			break;
		
		case 3:
			Collections.sort(produtos, new ProdutoDescricaoComparator());
			mostrarTodosProdutos(produtos);
			break;
		}
	}
	
	private Produto ordenarProdutoEscolher(int ordenacao, List<Produto> produtos) {
		switch(ordenacao) {
		case 1:
			Collections.sort(produtos, new ProdutoCodigoComparator());
			return escolherProduto(produtos);
		
		case 2:
			Collections.sort(produtos, new ProdutoNomeComparator());
			return escolherProduto(produtos);
		
		case 3:
			Collections.sort(produtos, new ProdutoDescricaoComparator());
			return escolherProduto(produtos);
		}
		return null;
	}
}
