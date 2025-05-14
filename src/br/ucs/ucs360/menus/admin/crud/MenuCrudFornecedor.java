package br.ucs.ucs360.menus.admin.crud;

import java.util.Scanner;

import br.ucs.ucs360.logistica.Fornecedor;
import br.ucs.ucs360.logistica.Produto;
import br.ucs.ucs360.menus.MenuConsulta;
import br.ucs.ucs360.menus.atualizacao.MenuAtualizacaoFornecedor;

public class MenuCrudFornecedor {
	private Scanner sc;
	
	public MenuCrudFornecedor() {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Selecione o que fazer:");
			System.out.println("1 - Cadastrar fornecedor");
			System.out.println("2 - Consultar fornecedor");
			System.out.println("3 - Atualizar fornecedor");
			System.out.println("4 - Remover fornecedor");
			System.out.println("5 - Remover produto");
			System.out.println("0 - Voltar para o menu inicial");
			opcao = sc.nextInt();
			sc.nextLine();
			switch(opcao) {
			case 1:
				new Fornecedor().cadastrarFornecedor();
				break;
				
			case 2:
				new Fornecedor().consultarFornecedorEspecifico(new MenuConsulta().consultaEspecifica());
				break;
				
			case 3:
				new MenuAtualizacaoFornecedor();
				break;
				
			case 4:
				new Fornecedor().removerFornecedor();
				break;
				
			case 5:
				new Fornecedor().removerProduto(Produto.getListaProdutos().get(new Produto().escolherProduto()));
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
