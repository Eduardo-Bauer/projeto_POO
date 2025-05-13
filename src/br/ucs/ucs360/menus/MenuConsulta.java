package br.ucs.ucs360.menus;

import java.util.Scanner;

public class MenuConsulta {
	private Scanner sc;
	
	public MenuConsulta() {
		sc = new Scanner(System.in);
	}
	
	public String[] consultaEspecifica() {
		int opcao = 0;
		String[] filtro = new String[2];
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione o tipo da consulta:");
			System.out.println("1 - Consultar pelo id");
			System.out.println("2 - Consultar pelo nome");
			System.out.println("3 - Consultar todos");
			System.out.println("0 - Voltar");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				System.out.print("Digite o id: ");
				filtro[0] = sc.nextLine();
				opcao = 0;
				break;
				
			case 2:
				System.out.print("Digite o nome: ");
				filtro[1] = sc.nextLine();
				opcao = 0;
				break;
			
			case 3:
				filtro = null;
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
		return filtro;
	}
}
