package br.ucs.ucs360.menus.atualizacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.ucs.ucs360.usuarios.informacoes.Pessoa;

public class MenuAtualizacaoPessoa {
	private Scanner sc;
	
	public MenuAtualizacaoPessoa(Pessoa pessoa) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione o que fazer:");
				System.out.println("1 - Atualizar nome");
				System.out.println("2 - Atualizar telefone");
				System.out.println("3 - Atualizar email");
				System.out.println("4 - Atualizar endereco");
				System.out.println("0 - Voltar");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				
				switch(opcao) {
				case 1:
					System.out.print("Digite o novo nome: ");
					pessoa.setNome(sc.nextLine());
					System.out.println("Nome atualizado com sucesso!");
					opcao = 0;
					break;
					
				case 2:
					System.out.print("Digite o novo telefone: ");
					pessoa.setTelefone(sc.nextLine());
					System.out.println("Telefone atualizado com sucesso!");
					opcao = 0;
					break;
					
				case 3:
					System.out.print("Digite o novo email: ");
					pessoa.setEmail(sc.nextLine());
					System.out.println("Email atualizado com sucesso!");
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
				
			}catch(InputMismatchException e) {
                System.out.println("Digite apenas números válidos.");
                opcao = -1;
                sc.nextLine();
            }
			
		}while(opcao != 0);
	}
}
