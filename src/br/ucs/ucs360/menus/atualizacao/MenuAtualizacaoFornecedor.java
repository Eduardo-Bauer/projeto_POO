package br.ucs.ucs360.menus.atualizacao;

import java.util.Scanner;

import br.ucs.ucs360.logistica.Fornecedor;
import br.ucs.ucs360.logistica.Produto;

public class MenuAtualizacaoFornecedor {
	private Scanner sc;
	private Produto produto;
	
	public MenuAtualizacaoFornecedor(Fornecedor fornecedor) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione o que fazer:");
			System.out.println("1 - Atualizar descricao");
			System.out.println("2 - Atualizar produto");
			System.out.println("3 - Desvincular produto");
			System.out.println("4 - Atualizar dados pessoais");
			System.out.println("5 - Atualizar endereco");
			System.out.println("0 - Voltar");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				fornecedor.atualizarDescricao();
				opcao = 0;
				break;
				
			case 2:
				produto = fornecedor.escolherProduto();
				if(produto != null) {
					new MenuAtualizacaoProduto(produto);						
				}
				opcao = 0;
				break;
				
			case 3:
				produto = fornecedor.escolherProduto();
				if(produto != null) {
					fornecedor.desvincularProduto(produto);
				}
				break;
				
			case 4:
				new MenuAtualizacaoPessoa(fornecedor);
				opcao = 0;
				break;
			
			case 5:
				new MenuAtualizacaoEndereco(fornecedor);
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
