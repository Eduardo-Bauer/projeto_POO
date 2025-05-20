package br.ucs.ucs360.menus.atualizacao;

import java.util.InputMismatchException;
import java.util.Scanner;
import br.ucs.ucs360.logistica.Estoque;
import br.ucs.ucs360.logistica.Loja;

public class MenuAtualizacaoEstoque {
	private Scanner sc;
	
	public MenuAtualizacaoEstoque(Estoque estoque, Loja loja) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione o que fazer:");
				System.out.println("1 - Atualizar quantidade");
				System.out.println("2 - Atualizar valor");
				System.out.println("3 - Atualizar produto");
				System.out.println("0 - Voltar");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				
				switch(opcao) {
				case 1:
					System.out.print("Digite a nova Quantidade: ");
					estoque.setQuantidade(sc.nextInt());
					System.out.println("Quantidade atualizada com sucesso!");
					opcao = 0;
					break;
					
				case 2:
					System.out.print("Digite a novo Preço: ");
					estoque.setPreco(sc.nextDouble());
					System.out.println("Preço atualizado com sucesso!");
					opcao = 0;
					break;
					
				case 3:
					new MenuAtualizacaoProduto(estoque.getProduto(), loja);
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
}
