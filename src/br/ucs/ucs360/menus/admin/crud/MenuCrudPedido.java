package br.ucs.ucs360.menus.admin.crud;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.ucs.ucs360.execoes.ErroGravacaoException;
import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.menus.admin.pedido.MenuPedidoAdmin;
import br.ucs.ucs360.menus.admin.pedido.MenuPedidoCliente;
import br.ucs.ucs360.usuarios.Admin;

public class MenuCrudPedido {
	private Scanner sc;

	public MenuCrudPedido(Admin admin, Loja loja) throws JsonProcessingException, ErroGravacaoException {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione a area desejada:");
				System.out.println("1 - Pedidos dos clientes");
				System.out.println("2 - Meus pedidos");
				System.out.println("0 - Voltar para o menu inicial");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				
				switch(opcao) {
				case 1:
					new MenuPedidoCliente(loja);
					break;
					
				case 2:
					new MenuPedidoAdmin(admin, loja);
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
