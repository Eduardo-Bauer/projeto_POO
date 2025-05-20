package br.ucs.ucs360.menus.atualizacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.ucs.ucs360.usuarios.informacoes.Pessoa;

public class MenuAtualizacaoEndereco {
	private Scanner sc;
	
	public MenuAtualizacaoEndereco(Pessoa pessoa) {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			try {
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
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				
				switch(opcao) {
				case 1:
					System.out.print("Digite a nova rua: ");
					pessoa.getEndereco().setRua(sc.nextLine());
					System.out.println("Rua atualizada com sucesso!");
					opcao = 0;
					break;
					
				case 2:
					System.out.print("Digite o novo bairro: ");
					pessoa.getEndereco().setBairro(sc.nextLine());	
					System.out.println("Bairro atualizada com sucesso!");	
					opcao = 0;
					break;
					
				case 3:
					System.out.print("Digite o novo numero: ");
					pessoa.getEndereco().setNumero(sc.nextLine());
					System.out.println("Numero atualizado com sucesso!");
					opcao = 0;
					break;
				
				case 4:
					System.out.print("Digite a nova cidade: ");
					pessoa.getEndereco().setCidade(sc.nextLine());
					System.out.println("Cidade atualizada com sucesso!");
					opcao = 0;
					break;
				
				case 5:
					System.out.print("Digite o novo estado: ");
					pessoa.getEndereco().setEstado(sc.nextLine());
					System.out.println("Estado atualizado com sucesso!");
					opcao = 0;
					break;
				
				case 6:
					System.out.print("Digite o novo cep: ");
					pessoa.getEndereco().setCep(sc.nextLine());
					System.out.println("Cep atualizado com sucesso!");
					opcao = 0;
					break;
				
				case 7:
					System.out.print("Digite algum novo complemento: ");
					pessoa.getEndereco().setComplemento(sc.nextLine());
					System.out.println("Complemento atualizado com sucesso!");
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
