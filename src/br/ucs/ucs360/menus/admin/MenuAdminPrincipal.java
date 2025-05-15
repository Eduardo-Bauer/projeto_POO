package br.ucs.ucs360.menus.admin;

import java.util.Scanner;

import br.ucs.ucs360.menus.admin.crud.MenuCrudEstoque;
import br.ucs.ucs360.menus.admin.crud.MenuCrudFornecedor;
import br.ucs.ucs360.menus.admin.crud.MenuCrudProduto;

public class MenuAdminPrincipal {
	private Scanner sc;
	
	public MenuAdminPrincipal(int adminAtivo) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione a area desejada:");
			System.out.println("1 - Produto");
			System.out.println("2 - Fornecedor");
			System.out.println("3 - Estoque");
			System.out.println("4 - Pedido");
			System.out.println("5 - Cliente");
			System.out.println("6 - Admin");
			System.out.println("0 - Voltar para o menu inicial");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				new MenuCrudProduto();
				break;
				
			case 2:
				new MenuCrudFornecedor();
				break;
			
			case 3:
				new MenuCrudEstoque();
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
