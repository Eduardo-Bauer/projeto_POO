package br.ucs.ucs360.menus;

import java.util.Scanner;

import br.ucs.ucs360.menus.admin.MenuAdminPrincipal;
import br.ucs.ucs360.menus.cliente.MenuClientePrincipal;
import br.ucs.ucs360.usuarios.Admin;
import br.ucs.ucs360.usuarios.Cliente;

public class MenuEntrarUsuario {
	private Scanner sc;
	
	public MenuEntrarUsuario() {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Entrar:");
			System.out.println("1 - Entrar como cliente");
			System.out.println("2 - Entrar como administrador");
			System.out.println("0 - Voltar para o menu inicial");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				int clienteAtivo = new Cliente().conferirEntrada();
				if(clienteAtivo != -1){
					new MenuClientePrincipal(clienteAtivo);
				};
				opcao = 0;
				break;
				
			case 2:
				int adminAtivo = new Admin().conferirEntrada();
				if(adminAtivo != -1){
					new MenuAdminPrincipal(adminAtivo);
				};
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
