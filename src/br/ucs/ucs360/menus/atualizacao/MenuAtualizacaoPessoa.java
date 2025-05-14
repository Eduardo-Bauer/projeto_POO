package br.ucs.ucs360.menus.atualizacao;

import java.util.Scanner;

import br.ucs.ucs360.usuarios.informacoes.Pessoa;

public class MenuAtualizacaoPessoa {
	private Scanner sc;
	
	public MenuAtualizacaoPessoa(Pessoa pessoa) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione o que fazer:");
			System.out.println("1 - Atualizar nome");
			System.out.println("2 - Atualizar telefone");
			System.out.println("3 - Atualizar email");
			System.out.println("4 - Atualizar endereco");
			System.out.println("0 - Voltar");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				pessoa.atualizarNome();
				opcao = 0;
				break;
				
			case 2:
				pessoa.atualizarTelefone();
				opcao = 0;
				break;
				
			case 3:
				pessoa.atualizarEmail();
				opcao = 0;
				break;
			
			case 4:
				new MenuAtualizacaoEndereco(pessoa);
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
