package br.ucs.ucs360.menus.atualizacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.logistica.Produto;

public class MenuAtualizacaoProduto {
	private Scanner sc;
	
	public MenuAtualizacaoProduto(Produto produto, Loja loja) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione o que fazer:");
				System.out.println("1 - Atualizar nome");
				System.out.println("2 - Atualizar descricao");
				System.out.println("3 - Atualizar fornecedor");
				System.out.println("4 - Atualizar estoque");
				System.out.println("0 - Voltar");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				
				switch(opcao) {
				case 1:
					System.out.print("Digite o novo nome: ");
					produto.setNome(sc.nextLine());
					System.out.println("Nome atualizado com sucesso!");
					opcao = 0;
					break;
					
				case 2:
					System.out.print("Digite a nova descricao: ");
					produto.setDescricao(sc.nextLine());
					System.out.println("Descrição atualizado com sucesso!");
					opcao = 0;
					break;
					
				case 3:
					new MenuAtualizacaoFornecedor(produto.getFornecedor(), loja);
					opcao = 0;
					break;
					
				case 4:
					if(produto.getEstoque() != null) {
						new MenuAtualizacaoEstoque(produto.getEstoque(), loja);	
						
					}else {
						System.out.println("Esse produto não tem estoque para atualizar");
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
}
