package br.ucs.ucs360.menus;

import java.util.Scanner;

import br.ucs.ucs360.usuarios.Admin;
import br.ucs.ucs360.usuarios.Cliente;

public class MenuCadastrarUsuario {
	private Scanner sc;
	
	public MenuCadastrarUsuario() {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Cadastre-se:");
			System.out.println("1 - Cadastre-se como cliente");
			System.out.println("2 - Cadastre-se como administrador");
			System.out.println("0 - Voltar para o menu inicial");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				new Cliente().cadastrarCliente();
				opcao = 0;
				break;
				
			case 2:
				new Admin().cadastrarAdmin();
				opcao = 0;
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
