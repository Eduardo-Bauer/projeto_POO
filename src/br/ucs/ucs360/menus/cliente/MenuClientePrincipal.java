package br.ucs.ucs360.menus.cliente;

import java.util.Scanner;

public class MenuClientePrincipal {
	private Scanner sc;
	
	public MenuClientePrincipal(int clienteAtivo) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione a area desejada");
			System.out.println("1 - Consultar produtos");
			System.out.println("2 - Fazer pedido");
			System.out.println("3 - Consultar meus pedidos");
			System.out.println("0 - Voltar para o menu inicial");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				
				break;
				
			case 2:
				
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
