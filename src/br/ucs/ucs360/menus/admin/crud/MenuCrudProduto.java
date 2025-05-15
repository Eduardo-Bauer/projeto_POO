package br.ucs.ucs360.menus.admin.crud;

import java.util.Scanner;

import br.ucs.ucs360.logistica.Estoque;
import br.ucs.ucs360.logistica.Fornecedor;
import br.ucs.ucs360.logistica.Produto;
import br.ucs.ucs360.menus.MenuConsulta;
import br.ucs.ucs360.menus.atualizacao.MenuAtualizacaoProduto;

public class MenuCrudProduto {
	private Scanner sc;
	private Produto produto;
	private Fornecedor fornecedor;
	
	public MenuCrudProduto() {
		sc = new Scanner(System.in);
		int opcao = 0;
		
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione o que fazer:");
			System.out.println("1 - Cadastrar produto");
			System.out.println("2 - Consultar produto");
			System.out.println("3 - Atualizar produto");
			System.out.println("4 - Remover produto");
			System.out.println("5 - Cadastrar estoque");
			System.out.println("0 - Voltar para o menu inicial");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				fornecedor = consultarListaFornecedores();
				if(fornecedor != null) {
					new Produto().cadastrarProduto(fornecedor);
				}
				break;
				
			case 2:
				new Produto().consultarProdutoEspecifico(new MenuConsulta().consultaEspecifica(), true);
				break;
				
			case 3:
				produto = consultarListaProdutos();
				if(produto != null) {
					new MenuAtualizacaoProduto(produto);
				}
				break;
				
			case 4:
				produto = consultarListaProdutos();
				if(produto != null) {
					new Produto().removerProduto(produto);
				}
				break;
						
			case 5:
				produto = consultarListaProdutos();
				if(produto != null) {
					new Estoque().cadastrarEstoque(produto);
				}
				break;	
				
			case 0:
				System.out.println("Voltando...");
				break;
			
			default: 
				System.out.println("Opção inválida");
				break;
			}
		}while(opcao != 0);
	}
	
	private Produto consultarListaProdutos() {
		return new Produto().escolherProduto();
	}
	
	private Fornecedor consultarListaFornecedores() {
		return new Fornecedor().escolherFornecedor();
	}
}
