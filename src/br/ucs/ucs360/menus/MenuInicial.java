package br.ucs.ucs360.menus;

import java.util.Scanner;

public class MenuInicial {
	private Scanner sc;
	
	public MenuInicial() {
		sc = new Scanner(System.in);
		int opcao = 0;

		do {
			System.out.println("---------------------------------------------");
			System.out.println("Bem vindo ao UCS 360!!");
			System.out.println("1 - Entrar");
			System.out.println("2 - Cadastre-se");
			System.out.println("0 - Sair");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				new MenuEntrarUsuario();
				break;
				
			case 2:
				new MenuCadastrarUsuario();
				break;
				
			case 0:
				System.out.println("Saindo...");
				sc.close();
				System.out.println("---------------------------------------------");
				System.exit(0);
				
			default: 
				System.out.println("Opção inválida");
				break;
			}
		}while(opcao != 0);
	}
	
	public static void main(String[] args) {
		new MenuInicial();
	};
}
