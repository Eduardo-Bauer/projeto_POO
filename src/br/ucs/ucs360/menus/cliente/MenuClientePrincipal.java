package br.ucs.ucs360.menus.cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.ucs.ucs360.comparadores.PedidoDataComparator;
import br.ucs.ucs360.comparadores.PedidoPrecoComparator;
import br.ucs.ucs360.comparadores.PedidoStatusComparator;
import br.ucs.ucs360.comparadores.ProdutoDescricaoComparator;
import br.ucs.ucs360.comparadores.ProdutoNomeComparator;
import br.ucs.ucs360.execoes.ErroGravacaoException;
import br.ucs.ucs360.logistica.ItemPedido;
import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.logistica.Pedido;
import br.ucs.ucs360.logistica.Produto;
import br.ucs.ucs360.menus.MenuComparator;
import br.ucs.ucs360.menus.MenuConsulta;
import br.ucs.ucs360.usuarios.Cliente;

public class MenuClientePrincipal {
	private Scanner sc;
	
	public MenuClientePrincipal(Cliente clienteAtivo, Loja loja) throws JsonProcessingException, ErroGravacaoException {
		sc = new Scanner(System.in);
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione a area desejada");
				System.out.println("1 - Consultar produtos");
				System.out.println("2 - Carrinho");
				System.out.println("3 - Consultar meus pedidos");
				System.out.println("0 - Voltar para o menu inicial");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				switch(opcao) {
				case 1:
					String[] filtro = new MenuConsulta().consultaEspecifica();
					if(filtro != null) {
						if(filtro[0] != null) {
							mostrarProduto(loja.consultarIdProduto(filtro[0]));
						}else {
							int ordenacao = new MenuComparator().fornecedorProdutoComparator();
							if(filtro[1] != null) {
								ordenarProdutoMostrar(ordenacao, loja.consultarNomeDescricaoProduto(filtro[1]));
							}else {
								ordenarProdutoMostrar(ordenacao, loja.getListaProdutos());
							}
						}
					}
					break;
					
				case 2:
					new MenuClienteCarrinho(clienteAtivo, loja);
					break;
					
				case 3:
					String[] filtro1 = new MenuConsulta().consultaPedido();
					if(filtro1 != null) {
						if(filtro1[0] != null) {
							mostrarPedido(clienteAtivo.consultarIdPedido(filtro1[0]));
						}else {
							int ordenacao = new MenuComparator().pedidoComparator();
							if(filtro1[1] != null) {
								LocalDate[] datas = new LocalDate[2];
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								datas[0] = LocalDate.parse(filtro1[1], formatter);
								datas[1] = LocalDate.parse(filtro1[2], formatter);
								ordenarPedidoData(ordenacao, clienteAtivo, datas);
							}else {
								ordenarPedido(ordenacao, clienteAtivo);
							}
						}
					}
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
	
	private void mostrarProduto(Produto produto) {
		if(produto == null) {
			System.out.println("Produto não encontrado");
			return;
		}
		System.out.println("---------------------------------------------");
		System.out.println("Produto encontrado:");
		System.out.println(produto + " | Status: " + (produto.getEstoque() != null && produto.getEstoque().getQuantidade() >= 1? "Disponível" : "Indisponível"));
		System.out.println("");
	}
	
	private void mostrarTodosProdutos(List<Produto> listaProdutos) {
		System.out.println("---------------------------------------------");
		System.out.println("Lista de produtos:");
		for(Produto produto : listaProdutos) {
			System.out.println(produto + " | Status: " + (produto.getEstoque() != null && produto.getEstoque().getQuantidade() >= 1? "Disponível" : "Indisponível"));
			System.out.println("");
		}
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
	
	private void ordenarPedidoData(int ordenacao, Cliente cliente, LocalDate[] datas) {
		switch(ordenacao) {
		case 1:
			mostrarTodosPedidos(cliente.consultarDataPedido(datas));
			break;
		
		case 2:
			Collections.sort(cliente.getPedidos(), new PedidoDataComparator());
			mostrarTodosPedidos(cliente.consultarDataPedido(datas));
			break;
		
		case 3:
			Collections.sort(cliente.getPedidos(), new PedidoPrecoComparator());
			mostrarTodosPedidos(cliente.consultarDataPedido(datas));
			break;
		
		case 4:
			Collections.sort(cliente.getPedidos(), new PedidoStatusComparator());
			mostrarTodosPedidos(cliente.consultarDataPedido(datas));
			break;
		}
	}
	
	private void ordenarPedido(int ordenacao, Cliente cliente) {
		switch(ordenacao) {
		case 1:
			mostrarTodosPedidos(cliente.getPedidos());
			break;
		
		case 2:
			Collections.sort(cliente.getPedidos(), new PedidoDataComparator());
			mostrarTodosPedidos(cliente.getPedidos());
			break;
		
		case 3:
			Collections.sort(cliente.getPedidos(), new PedidoPrecoComparator());
			mostrarTodosPedidos(cliente.getPedidos());
			break;
		
		case 4:
			Collections.sort(cliente.getPedidos(), new PedidoStatusComparator());
			mostrarTodosPedidos(cliente.getPedidos());
			break;
		}
	}
	
	private void ordenarProdutoMostrar(int ordenacao, List<Produto> produtos) {
		switch(ordenacao) {
		case 1:
			mostrarTodosProdutos(produtos);
			break;
		
		case 2:
			Collections.sort(produtos, new ProdutoNomeComparator());
			mostrarTodosProdutos(produtos);
			break;
		
		case 3:
			Collections.sort(produtos, new ProdutoDescricaoComparator());
			mostrarTodosProdutos(produtos);
			break;
		}
	}
}
