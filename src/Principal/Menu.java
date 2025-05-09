package Principal;

import java.util.Scanner;

public class Menu {
	private Scanner sc;
	
	public Menu() {
		sc = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.menuInicial();
	};
	
	public void menuInicial() {
		int opcao = 0;

		do {
			System.out.println("---------------------------------------------");
			System.out.println("Bem vindo ao UCS 360!!, como posso ajudar?");
			System.out.println("1 - Entrar");
			System.out.println("2 - Cadastre-se");
			System.out.println("0 - Sair");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				entrar();
				break;
				
			case 2:
				cadastrarUsuario();
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
	
	public void entrar() {
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Entrar");
			System.out.println("1 - Entrar como cliente");
			System.out.println("2 - Entrar como administrador");
			System.out.println("0 - Voltar para o menu inicial");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				int clienteAtivo = new Cliente().conferirEntrada();
				if(clienteAtivo != -1){
					
				};
				opcao = 0;
				break;
				
			case 2:
				int adminAtivo = new Admin().conferirEntrada();
				if(adminAtivo != -1){
					
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
	
	public void cadastrarUsuario() {
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Cadastre-se");
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
