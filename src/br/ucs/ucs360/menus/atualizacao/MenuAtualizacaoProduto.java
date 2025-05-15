package br.ucs.ucs360.menus.atualizacao;

import java.util.Scanner;
import br.ucs.ucs360.logistica.Produto;

public class MenuAtualizacaoProduto {
	private Scanner sc;
	
	public MenuAtualizacaoProduto(Produto produto) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione o que fazer:");
			System.out.println("1 - Atualizar nome");
			System.out.println("2 - Atualizar descricao");
			System.out.println("3 - Atualizar fornecedor");
			System.out.println("4 - Atualizar estoque");
			System.out.println("0 - Voltar");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				produto.atualizarNome();
				opcao = 0;
				break;
				
			case 2:
				produto.atualizarDescricao();
				opcao = 0;
				break;
				
			case 3:
				new MenuAtualizacaoFornecedor(produto.getFornecedor());
				opcao = 0;
				break;
				
			case 4:
				if(produto.getEstoque() != null) {
					new MenuAtualizacaoEstoque(produto.getEstoque());	
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
		}while(opcao != 0);
	}
}
