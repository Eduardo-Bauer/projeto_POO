package br.ucs.ucs360.menus.admin.crud;

import java.util.Scanner;

import br.ucs.ucs360.logistica.Fornecedor;
import br.ucs.ucs360.menus.MenuConsulta;
import br.ucs.ucs360.menus.atualizacao.MenuAtualizacaoFornecedor;

public class MenuCrudFornecedor {
	private Scanner sc;
	private Fornecedor fornecedor;
	
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
				fornecedor = consultarListaFornecedores();
				if(fornecedor != null) {
					new MenuAtualizacaoFornecedor(fornecedor);
				}
				break;
				
			case 4:
				fornecedor = consultarListaFornecedores();
				System.out.println(fornecedor.getListaProdutos().size());
				if(fornecedor != null && fornecedor.getListaProdutos().size() == 0) {
					fornecedor.removerFornecedor(fornecedor);
					
				}else {
					System.out.println("Remoção cancelada, fornecedor inexistente ou com produtos vinculados");
				}
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
	
	private Fornecedor consultarListaFornecedores() {
		return new Fornecedor().escolherFornecedor();
	}
}
