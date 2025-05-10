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
			System.out.println("Bem vindo ao UCS 360!!");
			System.out.println("1 - Entrar");
			System.out.println("2 - Cadastre-se");
			System.out.println("0 - Sair");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				entrarUsuario();
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
	
	public void entrarUsuario() {
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
					menuCliente(clienteAtivo);
				};
				opcao = 0;
				break;
				
			case 2:
				int adminAtivo = new Admin().conferirEntrada();
				if(adminAtivo != -1){
					menuAdmin(adminAtivo);
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
	
	public void menuCliente(int clienteAtivo) {
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione o que fazer");
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
	
	public void menuAdmin(int adminAtivo) {
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione o que fazer");
			System.out.println("1 - Produto");
			System.out.println("2 - Fornecedor");
			System.out.println("3 - Pedido");
			System.out.println("4 - Cliente");
			System.out.println("2 - Admin");
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
