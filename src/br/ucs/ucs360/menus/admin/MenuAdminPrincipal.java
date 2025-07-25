package br.ucs.ucs360.menus.admin;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.ucs.ucs360.execoes.ErroGravacaoException;
import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.menus.admin.crud.MenuCrudEstoque;
import br.ucs.ucs360.menus.admin.crud.MenuCrudFornecedor;
import br.ucs.ucs360.menus.admin.crud.MenuCrudPedido;
import br.ucs.ucs360.menus.admin.crud.MenuCrudProduto;
import br.ucs.ucs360.usuarios.Admin;

public class MenuAdminPrincipal {
	private Scanner sc;
	
	public MenuAdminPrincipal(Admin admin, Loja loja) throws JsonProcessingException, ErroGravacaoException {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione a area desejada:");
				System.out.println("1 - Produto");
				System.out.println("2 - Fornecedor");
				System.out.println("3 - Estoque");
				System.out.println("4 - Pedido");
				System.out.println("0 - Voltar para o menu inicial");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				
				switch(opcao) {
				case 1:
					new MenuCrudProduto(loja);
					break;
					
				case 2:
					new MenuCrudFornecedor(loja);
					break;
				
				case 3:
					new MenuCrudEstoque(loja);
					break;
				
				case 4:
					new MenuCrudPedido(admin, loja);
					
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
