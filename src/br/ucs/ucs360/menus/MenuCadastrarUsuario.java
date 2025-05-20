package br.ucs.ucs360.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.usuarios.Admin;
import br.ucs.ucs360.usuarios.Cliente;

public class MenuCadastrarUsuario {
	private Scanner sc;
	
	public MenuCadastrarUsuario(Loja loja) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Cadastre-se:");
				System.out.println("1 - Cadastre-se como cliente");
				System.out.println("2 - Cadastre-se como administrador");
				System.out.println("0 - Voltar para o menu inicial");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				switch(opcao) {
				case 1:
					System.out.println((loja.adicionarCliente(cadastrarCliente()) ? "Cliente cadastrado com sucesso!" : "Usuario já cadastrado"));
					opcao = 0;
					break;
					
				case 2:
					System.out.println((loja.adicionarAdmin(cadastrarAdmin()) ?  "Admin cadastrado com sucesso!" : "Usuario já cadastrado"));
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
	
	private Cliente cadastrarCliente() {
		Cliente cliente = new Cliente();
		
        System.out.print("Digite o login: ");
        cliente.getDado().setLogin(sc.nextLine());
        
        System.out.print("Digite a senha: ");
        cliente.getDado().setSenha(sc.nextLine());
        
        cliente.getDado().setTipo(false);
		
		System.out.print("Digite nome: ");
		cliente.setNome(sc.nextLine());
		
		System.out.print("Digite telefone: ");
		cliente.setTelefone(sc.nextLine());
		
		System.out.print("Digite email: ");
		cliente.setEmail(sc.nextLine());
		
		cliente.setId(Cliente.getUltimoCliente());
		
		System.out.print("Digite rua: ");
		cliente.getEndereco().setRua(sc.nextLine());
		
		System.out.print("Digite bairro: ");
		cliente.getEndereco().setBairro(sc.nextLine());
		
		System.out.print("Digite numero: ");
		cliente.getEndereco().setNumero(sc.nextLine());
		
		System.out.print("Digite cidade: ");
		cliente.getEndereco().setCidade(sc.nextLine());
		
		System.out.print("Digite estado: ");
		cliente.getEndereco().setEstado(sc.nextLine());
		
		System.out.print("Digite cep: ");
		cliente.getEndereco().setCep(sc.nextLine());
		
		System.out.print("Digite algum complemento: ");
		cliente.getEndereco().setComplemento(sc.nextLine());
		
		return cliente;
	}
	
	private Admin cadastrarAdmin() {
		Admin admin = new Admin();
		
        System.out.print("Digite o login: ");
        admin.getDado().setLogin(sc.nextLine());
        
        System.out.print("Digite a senha: ");
        admin.getDado().setSenha(sc.nextLine());
        
        admin.getDado().setTipo(true);
        
        return admin;
	}
}
