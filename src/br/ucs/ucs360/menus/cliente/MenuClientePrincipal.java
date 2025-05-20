package br.ucs.ucs360.menus.cliente;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.ucs.ucs360.logistica.Loja;

public class MenuClientePrincipal {
	private Scanner sc;
	
	public MenuClientePrincipal(int clienteAtivo, Loja loja) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione a area desejada");
				System.out.println("1 - Consultar produtos");
				System.out.println("2 - Fazer pedido");
				System.out.println("3 - Consultar meus pedidos");
				System.out.println("0 - Voltar para o menu inicial");
				System.out.print("Digite sua opção: ");
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
				
			}catch(InputMismatchException e) {
                System.out.println("Digite apenas números válidos.");
                opcao = -1;
                sc.nextLine();
            }
			
		}while(opcao != 0);
	}
}
