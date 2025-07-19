package br.ucs.ucs360.menus.atualizacao;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.ucs.ucs360.dadosLoja.BancoDados;
import br.ucs.ucs360.execoes.ErroGravacaoException;
import br.ucs.ucs360.logistica.Fornecedor;
import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.logistica.Produto;

public class MenuAtualizacaoFornecedor {
	private Scanner sc;
	private Produto produto;
	
	public MenuAtualizacaoFornecedor(Fornecedor fornecedor, Loja loja) throws JsonProcessingException, ErroGravacaoException {
		sc = new Scanner(System.in);
		BancoDados bancoDados = new BancoDados();
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione o que fazer:");
				System.out.println("1 - Atualizar descricao");
				System.out.println("2 - Atualizar produto");
				System.out.println("3 - Desvincular produto");
				System.out.println("4 - Atualizar dados pessoais");
				System.out.println("5 - Atualizar endereco");
				System.out.println("0 - Voltar");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				switch(opcao) {
				case 1:
					System.out.print("Digite a nova descrição: ");
					fornecedor.setDescricao(sc.nextLine());
					System.out.println("Descricao atualizada com sucesso!");
					opcao = 0;
					break;
					
				case 2:
					produto = escolherProduto(fornecedor);
					if(produto != null) {
						new MenuAtualizacaoProduto(produto, loja);	
						
					}else {
						System.out.println("É necessário vincular um produto antes");
					}
					opcao = 0;
					break;
					
				case 3:
					produto = escolherProduto(fornecedor);
					if(produto != null) {
						fornecedor.getListaProdutos().remove(produto);
						
						System.out.println("Selecione o novo fornecedor: ");
						fornecedor = escolherFornecedor(loja.getListaFornecedores());
						
						produto.setFornecedor(fornecedor);
						fornecedor.getListaProdutos().add(produto);
						
						System.out.println("Novo fornecedor vinculado com sucesso!");
						
					}else {
						System.out.println("É necessário vincular um produto antes");
					}
					opcao = 0;
					break;
					 
				case 4:
					new MenuAtualizacaoPessoa(fornecedor);
					opcao = 0;
					break;
				
				case 5:
					new MenuAtualizacaoEndereco(fornecedor);
					opcao = 0;
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
			bancoDados.gravaJSONLoja("banco_de_dados/loja.json", loja);
		}while(opcao != 0);
	}
	
	
	private void mostrarProdutosVinculados(Fornecedor fornecedor) {
		System.out.println("---------------------------------------------");
		System.out.println("Produtos vinculados:");
		for(Produto produto : fornecedor.getListaProdutos()) {
			System.out.println(produto);
			
		}
		System.out.println("");
	}
	
	private void mostrarProdutos(Fornecedor fornecedor) {
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
			mostrarProdutos(fornecedor);
			System.out.println("");
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
	
	private Produto escolherProduto(Fornecedor fornecedor) {
		int numero;
		
		if(fornecedor.getListaProdutos().size() == 0) {
			return null;
		}
		
		while(true){
			mostrarProdutosVinculados(fornecedor);
		
			System.out.print("Digite o numero do produto: ");
			numero = sc.nextInt();
			sc.nextLine();
			
			for(int i = 0; i < fornecedor.getListaProdutos().size(); i++) {
				if(fornecedor.getListaProdutos().get(i).getId() == numero) {
					return fornecedor.getListaProdutos().get(i);
				}
			}
			System.out.println("Opção inválida");
			continue;	
		}
	}
}
