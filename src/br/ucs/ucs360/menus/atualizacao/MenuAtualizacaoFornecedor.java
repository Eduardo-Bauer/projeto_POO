package br.ucs.ucs360.menus.atualizacao;

import java.util.Scanner;

import br.ucs.ucs360.logistica.Fornecedor;

public class MenuAtualizacaoFornecedor {
	private Scanner sc;
	
	public MenuAtualizacaoFornecedor() {
		sc = new Scanner(System.in);
		int opcao = 0;
		int fornecedor = new Fornecedor().escolherFornecedor();
		
		if(fornecedor != -1) {
			do {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione o que fazer:");
				System.out.println("1 - Atualizar descricao");
				System.out.println("2 - Atualizar produto");
				System.out.println("3 - Atualizar dados pessoais");
				System.out.println("4 - Atualizar endereco");
				System.out.println("0 - Voltar");
				opcao = sc.nextInt();
				sc.nextLine();
				switch(opcao) {
				case 1:
					new Fornecedor().atualizarDescricao(fornecedor);
					opcao = 0;
					break;
					
				case 2:
					new MenuAtualizacaoProduto();
					opcao = 0;
					break;
					
				case 3:
					new MenuAtualizacaoPessoa(Fornecedor.getListaFornecedores().get(fornecedor));
					opcao = 0;
					break;
				
				case 4:
					new MenuAtualizacaoEndereco(Fornecedor.getListaFornecedores().get(fornecedor));
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
}
