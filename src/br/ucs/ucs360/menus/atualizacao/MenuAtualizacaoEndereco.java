package br.ucs.ucs360.menus.atualizacao;

import java.util.Scanner;

import br.ucs.ucs360.usuarios.informacoes.Pessoa;

public class MenuAtualizacaoEndereco {
	private Scanner sc;
	
	public MenuAtualizacaoEndereco(Pessoa pessoa) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione o que fazer:");
			System.out.println("1 - Atualizar rua");
			System.out.println("2 - Atualizar bairro");
			System.out.println("3 - Atualizar numero");
			System.out.println("4 - Atualizar cidade");
			System.out.println("5 - Atualizar estado");
			System.out.println("6 - Atualizar cep");
			System.out.println("7 - Atualizar complemento");
			System.out.println("0 - Voltar");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				pessoa.getEndereco().atualizarRua();
				opcao = 0;
				break;
				
			case 2:
				pessoa.getEndereco().atualizarBairro();
				opcao = 0;
				break;
				
			case 3:
				pessoa.getEndereco().atualizarNumero();
				opcao = 0;
				break;
			
			case 4:
				pessoa.getEndereco().atualizarCidade();
				opcao = 0;
				break;
			
			case 5:
				pessoa.getEndereco().atualizarEstado();
				opcao = 0;
				break;
			
			case 6:
				pessoa.getEndereco().atualizarCep();
				opcao = 0;
				break;
			
			case 7:
				pessoa.getEndereco().atualziarComplemento();
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
