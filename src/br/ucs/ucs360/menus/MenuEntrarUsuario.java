package br.ucs.ucs360.menus;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.ucs.ucs360.menus.admin.MenuAdminPrincipal;
import br.ucs.ucs360.menus.cliente.MenuClientePrincipal;
import br.ucs.ucs360.usuarios.Admin;
import br.ucs.ucs360.usuarios.Cliente;
import br.ucs.ucs360.execoes.ErroGravacaoException;
import br.ucs.ucs360.logistica.Loja;

public class MenuEntrarUsuario {
	private Scanner sc;
	
	public MenuEntrarUsuario(Loja loja) throws JsonProcessingException, ErroGravacaoException{
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
					Cliente clienteAtivo = entrarCliente(loja.getListaClientes());
					if(clienteAtivo != null){
						System.out.println("Entrando...");
						new MenuClientePrincipal(clienteAtivo, loja);
						
					}else {
						System.out.println("Login ou senha incorretos!");
					}
					opcao = 0;
					break;
					
				case 2:
					Admin adminAtivo = entrarAdmin(loja.getListaAdmins());
					if(adminAtivo != null){
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
	
	private Admin entrarAdmin(List<Admin> admins) {
		Admin adminTentandoEntrar = new Admin();
		
        System.out.print("Digite o login: ");
        adminTentandoEntrar.getDado().setLogin(sc.nextLine());
        
        System.out.print("Digite a senha: ");
        adminTentandoEntrar.getDado().setSenha(sc.nextLine());
        
        for(Admin admin : admins){
        	if(admin.getDado().equals(adminTentandoEntrar.getDado())) {
        		return admin;
        	}
        }
        return null;
	}
	
	private Cliente entrarCliente(List<Cliente> clientes) {
		Cliente clienteTentandoEntrar = new Cliente();
		
		System.out.print("Digite o login: ");
		clienteTentandoEntrar.getDado().setLogin(sc.nextLine());
		
		System.out.print("Digite a senha: ");
		clienteTentandoEntrar.getDado().setSenha(sc.nextLine());
        
        for(Cliente cliente : clientes) {
        	if(cliente.getDado().equals(clienteTentandoEntrar.getDado())) {
        		return cliente;
        	}
        }
        return null;
	}
}
