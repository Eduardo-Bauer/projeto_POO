package br.ucs.ucs360.menus.admin.crud;

import java.util.Scanner;

import br.ucs.ucs360.logistica.Estoque;
import br.ucs.ucs360.logistica.Produto;
import br.ucs.ucs360.menus.MenuConsulta;
import br.ucs.ucs360.menus.atualizacao.MenuAtualizacaoEstoque;

public class MenuCrudEstoque {
	private Scanner sc;
	private Estoque estoque;
	
	public MenuCrudEstoque() {
		sc = new Scanner(System.in);
		int opcao = 0;
		
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione o que fazer:");
			System.out.println("1 - Cadastrar estoque");
			System.out.println("2 - Consultar estoque");
			System.out.println("3 - Atualizar estoque");
			System.out.println("4 - Remover estoque");
			System.out.println("0 - Voltar para o menu inicial");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				Produto produto = consultarListaProdutos();
				if(produto != null) {
					new Estoque().cadastrarEstoque(produto);
				}
				break;
				
			case 2:
				new Estoque().consultarEstoqueEspecifico(new MenuConsulta().consultaEspecifica());
				break;
				
			case 3:
				estoque = consultarListaEstoques();
				if(estoque != null) {
					new MenuAtualizacaoEstoque(estoque);
				}
				break;
				
			case 4:
				estoque = consultarListaEstoques();
				if(estoque != null) {
					estoque.removerEstoque();
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
	
	private Estoque consultarListaEstoques() {
		return new Estoque().escolherEstoque();
	}
}
