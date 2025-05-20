package br.ucs.ucs360.menus;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.ucs.ucs360.menus.admin.MenuAdminPrincipal;
import br.ucs.ucs360.menus.cliente.MenuClientePrincipal;
import br.ucs.ucs360.usuarios.Admin;
import br.ucs.ucs360.usuarios.Cliente;
import br.ucs.ucs360.logistica.Loja;

public class MenuEntrarUsuario {
	private Scanner sc;
	
	public MenuEntrarUsuario(Loja loja) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Entrar:");
				System.out.println("1 - Entrar como cliente");
				System.out.println("2 - Entrar como administrador");
				System.out.println("0 - Voltar para o menu inicial");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				switch(opcao) {
				case 1:
					int clienteAtivo = entrarCliente(loja.getListaClientes());
					if(clienteAtivo != -1){
						System.out.println("Entrando...");
						new MenuClientePrincipal(clienteAtivo, loja);
						
					}else {
						System.out.println("Login ou senha incorretos!");
					}
					opcao = 0;
					break;
					
				case 2:
					int adminAtivo = entrarAdmin(loja.getListaAdmins());
					if(adminAtivo != -1){
						System.out.println("Entrando...");
						new MenuAdminPrincipal(adminAtivo, loja);
						
					}else {
						System.out.println("Login ou senha incorretos!");
					}
					opcao = 0;
					break;
					
				case 0:
					System.out.println("Voltando...");
					break;
				
				default: 
					System.out.println("Opção inválida");
					break;
				}
				
			}catch(InputMismatchException e) {
                System.out.println("Digite apenas números válidos.");
                opcao = -1;
                sc.nextLine();
            }
			
		}while(opcao != 0);
	}
	
	private int entrarAdmin(List<Admin> admins) {
		Admin admin = new Admin();
		
        System.out.print("Digite o login: ");
        admin.getDado().setLogin(sc.nextLine());
        
        System.out.print("Digite a senha: ");
        admin.getDado().setSenha(sc.nextLine());
        
        admin.getDado().setTipo(true);
        
        return admin.conferirEntrada(admins);
	}
	
	private int entrarCliente(List<Cliente> clientes) {
		Cliente cliente = new Cliente();
		
        System.out.print("Digite o login: ");
        cliente.getDado().setLogin(sc.nextLine());
        
        System.out.print("Digite a senha: ");
        cliente.getDado().setSenha(sc.nextLine());
        
        cliente.getDado().setTipo(false);
        
        return cliente.conferirEntrada(clientes);
	}
}
