package br.ucs.ucs360.menus.admin.crud;

import java.util.Scanner;

import br.ucs.ucs360.logistica.Estoque;
import br.ucs.ucs360.logistica.Produto;
import br.ucs.ucs360.menus.MenuConsulta;

public class MenuCrudProduto {
	private Scanner sc;
	
	public MenuCrudProduto() {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione o que fazer:");
			System.out.println("1 - Cadastrar produto");
			System.out.println("2 - Atualizar estoque");
			System.out.println("3 - Consultar produto");
			System.out.println("4 - Atualizar produto");
			System.out.println("5 - Remover produto");
			System.out.println("0 - Voltar para o menu inicial");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				new Produto().cadastrarProduto();
				break;
				
			case 2:
				new Estoque().cadastrarEstoque();
				break;
				
			case 3:
				new Produto().consultarProdutoEspecifico(new MenuConsulta().consultaEspecifica());
				break;
				
			case 4:
				
				break;
				
			case 5:
				
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
}
