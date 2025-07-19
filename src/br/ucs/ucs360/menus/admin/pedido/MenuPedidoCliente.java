package br.ucs.ucs360.menus.admin.pedido;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.ucs.ucs360.comparadores.PedidoDataComparator;
import br.ucs.ucs360.comparadores.PedidoNumeroComparator;
import br.ucs.ucs360.comparadores.PedidoPrecoComparator;
import br.ucs.ucs360.comparadores.PedidoStatusComparator;
import br.ucs.ucs360.dadosLoja.BancoDados;
import br.ucs.ucs360.execoes.ErroGravacaoException;
import br.ucs.ucs360.logistica.ItemPedido;
import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.logistica.Pedido;
import br.ucs.ucs360.menus.MenuComparator;
import br.ucs.ucs360.menus.MenuConsulta;

public class MenuPedidoCliente {
	private Scanner sc;
	
	public MenuPedidoCliente(Loja loja) throws JsonProcessingException, ErroGravacaoException {
		sc = new Scanner(System.in);
		BancoDados bancoDados = new BancoDados();
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione a area desejada:");
				System.out.println("1 - Consultar pedidos");
				System.out.println("2 - Entregar pedidos");
				System.out.println("3 - Cancelar pedidos");
				System.out.println("0 - Voltar para o menu inicial");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				
				switch(opcao) {
				case 1:
					String[] filtro1 = new MenuConsulta().consultaPedido();
					if(filtro1 != null) {
						if(filtro1[0] != null) {
							mostrarPedido(loja.consultarIdPedido(filtro1[0]));
						}else {
							int ordenacao = new MenuComparator().pedidoComparator();
							if(filtro1[1] != null) {
								LocalDate[] datas = new LocalDate[2];
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								datas[0] = LocalDate.parse(filtro1[1], formatter);
								datas[1] = LocalDate.parse(filtro1[2], formatter);
								ordenarPedidoData(ordenacao, loja, datas);
							}else {
								ordenarPedido(ordenacao, loja);
							}
						}
					}
					break;
					
				case 2:
					int ordenacao = new MenuComparator().pedidoComparator();
					Pedido pedido = ordenarPedidoEscolha(ordenacao, loja);
					if(pedido != null && pedido.getSituacao().equals("NOVO")) {
						pedido.setSituacao("ENTREGUE");
						pedido.setDataEntraga(LocalDate.now());
						System.out.println("Pedido entregue com sucesso!");
						bancoDados.gravaJSONLoja("banco_de_dados/loja.json", loja);
						
					}else {
						System.out.println("Pedido já entregue ou cancelado, ou sem pedidos cadastrados");
					}
					break;
				
				case 3:
					int ordenacao1 = new MenuComparator().pedidoComparator();
					Pedido pedido1 = ordenarPedidoEscolha(ordenacao1, loja);
					if(pedido1 != null && pedido1.getSituacao().equals("NOVO")) {
						pedido1.setSituacao("CANCELADO");
						pedido1.setDataCancelamento(LocalDate.now());
						for(ItemPedido itemPedido : pedido1.getItemPedidos()) {
							itemPedido.getProduto().getEstoque().aumentarQuantidade(itemPedido.getQuantidade());
						}
						System.out.println("Pedido cancelado com sucesso!");
						bancoDados.gravaJSONLoja("banco_de_dados/loja.json", loja);
						
					}else {
						System.out.println("Pedido já entregue ou cancelado, ou sem pedidos cadastrados");
					}
					
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
	
	private void mostrarProdutosVinculados(Pedido pedido) {
		for(ItemPedido produto : pedido.getItemPedidos()) {
			System.out.println(produto);
		}
	}
	
	private void mostrarPedido(Pedido pedido) {
		if(pedido == null) {
			System.out.println("Pedido não encontrado");
			return;
		}
		System.out.println("---------------------------------------------");
		System.out.println("Pedido encontrado:");
		System.out.println(pedido);
		System.out.println("Produtos vinculados: ");
		mostrarProdutosVinculados(pedido);
		System.out.println("");
	}
	
	private void mostrarTodosPedidos(List<Pedido> pedidos) {
		System.out.println("---------------------------------------------");
		System.out.println("Lista de Pedidos:");
		for(Pedido pedido : pedidos) {
			System.out.println(pedido);
			System.out.println("Produtos vinculados: ");
			mostrarProdutosVinculados(pedido);
			System.out.println("");
		}
	}
	
	private Pedido escolherPedido(List<Pedido> listaPedidos) {
		int numero;
		
		if(listaPedidos.size() == 0) {
			return null;
		}
		
		while(true){
			mostrarTodosPedidos(listaPedidos);
		
			System.out.print("Digite o numero do pedido: ");
			numero = sc.nextInt();
			sc.nextLine();
			
			for(int i = 0; i < listaPedidos.size(); i++) {
				if(listaPedidos.get(i).getNumero() == numero) {
					return listaPedidos.get(i);
				}
			}
			System.out.println("Opção inválida");
			continue;	
		} 
	}
	
	private Pedido ordenarPedidoEscolha(int ordenacao, Loja loja) {
		switch(ordenacao) {
		case 1:
			return escolherPedido(loja.getListaPedidos());
		
		case 2:
			Collections.sort(loja.getListaPedidos(), new PedidoDataComparator());
			return escolherPedido(loja.getListaPedidos());
		
		case 3:
			Collections.sort(loja.getListaPedidos(), new PedidoPrecoComparator());
			return escolherPedido(loja.getListaPedidos());
		
		case 4:
			Collections.sort(loja.getListaPedidos(), new PedidoStatusComparator());
			return escolherPedido(loja.getListaPedidos());
		}
		return null;
	}
	
	private void ordenarPedidoData(int ordenacao, Loja loja, LocalDate[] datas) {
		switch(ordenacao) {
		case 1:
			Collections.sort(loja.getListaPedidos(), new PedidoNumeroComparator());
			mostrarTodosPedidos(loja.consultarDataPedido(datas));
			break;
		
		case 2:
			Collections.sort(loja.getListaPedidos(), new PedidoDataComparator());
			mostrarTodosPedidos(loja.consultarDataPedido(datas));
			break;
		
		case 3:
			Collections.sort(loja.getListaPedidos(), new PedidoPrecoComparator());
			mostrarTodosPedidos(loja.consultarDataPedido(datas));
			break;
		
		case 4:
			Collections.sort(loja.getListaPedidos(), new PedidoStatusComparator());
			mostrarTodosPedidos(loja.consultarDataPedido(datas));
			break;
		}
	}
	
	private void ordenarPedido(int ordenacao, Loja loja) {
		switch(ordenacao) {
		case 1:
			Collections.sort(loja.getListaPedidos(), new PedidoNumeroComparator());
			mostrarTodosPedidos(loja.getListaPedidos());
			break;
		
		case 2:
			Collections.sort(loja.getListaPedidos(), new PedidoDataComparator());
			mostrarTodosPedidos(loja.getListaPedidos());
			break;
		
		case 3:
			Collections.sort(loja.getListaPedidos(), new PedidoPrecoComparator());
			mostrarTodosPedidos(loja.getListaPedidos());
			break;
		
		case 4:
			Collections.sort(loja.getListaPedidos(), new PedidoStatusComparator());
			mostrarTodosPedidos(loja.getListaPedidos());
			break;
		}
	}
}
