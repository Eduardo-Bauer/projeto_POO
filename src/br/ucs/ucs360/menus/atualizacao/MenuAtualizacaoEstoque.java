package br.ucs.ucs360.menus.atualizacao;

import java.util.Scanner;
import br.ucs.ucs360.logistica.Estoque;

public class MenuAtualizacaoEstoque {
	private Scanner sc;
	
	public MenuAtualizacaoEstoque(Estoque estoque) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione o que fazer:");
			System.out.println("1 - Atualizar quantidade");
			System.out.println("2 - Atualizar valor");
			System.out.println("3 - Atualizar produto");
			System.out.println("0 - Voltar");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				estoque.atualizarQuantidade();
				opcao = 0;
				break;
				
			case 2:
				estoque.atualizarQuantidade();
				opcao = 0;
				break;
				
			case 3:
				new MenuAtualizacaoProduto(estoque.getProduto());
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
