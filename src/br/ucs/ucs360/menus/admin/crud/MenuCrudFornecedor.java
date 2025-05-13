package br.ucs.ucs360.menus.admin.crud;

import java.util.Scanner;

import br.ucs.ucs360.logistica.Fornecedor;

public class MenuCrudFornecedor {
	private Scanner sc;
	
	public MenuCrudFornecedor() {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione o que fazer:");
			System.out.println("1 - Cadastrar fornecedor");
			System.out.println("2 - Consultar fornecedor");
			System.out.println("3 - Atualizar fornecedor");
			System.out.println("4 - Remover fornecedor");
			System.out.println("0 - Voltar para o menu inicial");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				new Fornecedor().cadastrarFornecedor();
				break;
				
			case 2:

				break;
				
			case 3:

				break;
				
			case 4:

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
