package br.ucs.ucs360.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuComparator {
	private Scanner sc;
	
	public MenuComparator() {
		sc = new Scanner(System.in);
	}
	
	public int fornecedorProdutoComparator() {
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione o tipo de ordenação:");
				System.out.println("1 - Ordenar pelo código");
				System.out.println("2 - Ordenar pelo nome");
				System.out.println("3 - Ordenar pela descrição");
				System.out.println("0 - Voltar");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				switch(opcao) {
				case 1:
					return 1;
					
				case 2:
					return 2;
				
				case 3:
					return 3;			
					
				case 0:
					return 0;
				
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
		return 0;
	}
	
	public int pedidoComparator() {
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione o tipo de ordenação:");
				System.out.println("1 - Ordenar pelo código");
				System.out.println("2 - Ordenar pela data");
				System.out.println("3 - Ordenar pelo preço");
				System.out.println("4 - Ordenar pela situação");
				System.out.println("0 - Voltar");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				switch(opcao) {
				case 1:
					return 1;
					
				case 2:
					return 2;
				
				case 3:
					return 3;		
					
				case 4:
					return 4;	
					
				case 0:
					return 0;
				
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
		return 0;
	}
}
