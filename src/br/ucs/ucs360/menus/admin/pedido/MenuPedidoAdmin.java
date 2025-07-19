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
import br.ucs.ucs360.comparadores.ProdutoCodigoComparator;
import br.ucs.ucs360.comparadores.ProdutoDescricaoComparator;
import br.ucs.ucs360.comparadores.ProdutoNomeComparator;
import br.ucs.ucs360.dadosLoja.BancoDados;
import br.ucs.ucs360.execoes.ErroGravacaoException;
import br.ucs.ucs360.logistica.Estoque;
import br.ucs.ucs360.logistica.ItemPedido;
import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.logistica.Pedido;
import br.ucs.ucs360.logistica.Produto;
import br.ucs.ucs360.menus.MenuComparator;
import br.ucs.ucs360.menus.MenuConsulta;
import br.ucs.ucs360.usuarios.Admin;

public class MenuPedidoAdmin {
	private Scanner sc;
	
	public MenuPedidoAdmin(Admin admin, Loja loja) throws JsonProcessingException, ErroGravacaoException {
		sc = new Scanner(System.in);
		BancoDados bancoDados = new BancoDados();
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione a area desejada:");
				System.out.println("1 - Consultar meus pedidos");
				System.out.println("2 - Fazer pedido");
				System.out.println("0 - Voltar para o menu inicial");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				
				switch(opcao) {
				case 1:
					String[] filtro1 = new MenuConsulta().consultaPedido();
					if(filtro1 != null) {
						if(filtro1[0] != null) {
							mostrarPedido(admin.consultarIdPedido(filtro1[0]));
						}else {
							int ordenacao = new MenuComparator().pedidoComparator();
							if(filtro1[1] != null) {
								LocalDate[] datas = new LocalDate[2];
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								datas[0] = LocalDate.parse(filtro1[1], formatter);
								datas[1] = LocalDate.parse(filtro1[2], formatter);
								ordenarPedidoData(ordenacao, admin, datas);
							}else {
								ordenarPedido(ordenacao, admin);
							}
						}
					}
					break;
					
				case 2:
					String[] filtro = new MenuConsulta().consultaEspecifica();
					if(filtro != null) {
						if(filtro[0] != null) {
							Produto produto = (loja.consultarIdProduto(filtro[0]));
							mostrarProduto(produto);
							if(produto != null) {
								criarPedido(admin, produto, loja);
							}
						}else {
							int ordenacao = new MenuComparator().fornecedorProdutoComparator();
							if(filtro[1] != null) {
								Produto produto1 = ordenarProduto(ordenacao, loja.consultarNomeDescricaoProduto(filtro[1]));
								if(produto1 != null) {
									criarPedido(admin, produto1, loja);
								}
								
							}else {
								Produto produto2 = ordenarProduto(ordenacao, loja.getListaProdutos());
								if(produto2 != null) {
									criarPedido(admin, produto2, loja);
								}
							}
						}
						bancoDados.gravaJSONLoja("banco_de_dados/loja.json", loja);
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
	
	private void criarPedido(Admin adminAtivo, Produto produto, Loja loja){
		ItemPedido itemPedido = new ItemPedido();
		Pedido pedido = new Pedido();
		
		System.out.print("Digite a quantidade do pedido: ");
		itemPedido.setQuantidade(sc.nextInt());
		itemPedido.setProduto(produto);
		pedido.setNumero(adminAtivo.getPedidos().size());
		pedido.getItemPedidos().add(itemPedido);
		pedido.setDataPedido(LocalDate.now());
		pedido.setSituacao("NOVO");
		
		if(produto.getEstoque() == null) {
			System.out.println("Esse produto ainda não tem um estoque");
			System.out.println((loja.adicionarEstoque(cadastrarEstoque(produto, itemPedido)) ? "Estoque cadastrado com sucesso!" : "Esse produto já tem um estoque"));
		}else {
			produto.getEstoque().aumentarQuantidade(itemPedido.getQuantidade());
		}
		
		adminAtivo.getPedidos().add(pedido);
		
		System.out.println("Compra realizada com sucesso!!");
	}
	
	private Estoque cadastrarEstoque(Produto produto, ItemPedido itemPedido) {
		Estoque estoque = new Estoque();
		
		estoque.setQuantidade(itemPedido.getQuantidade());
		
		System.out.print("Digite o preco do produto: ");
		estoque.setPreco(sc.nextDouble());
		
		estoque.setId(Estoque.getUltimoEstoque());
		estoque.setProduto(produto);
		
		return estoque;
	}
	
	private void mostrarProduto(Produto produto) {
		if(produto == null) {
			System.out.println("Produto não encontrado");
			return;
		}
		System.out.println("---------------------------------------------");
		System.out.println("Produto encontrado:");
		System.out.println(produto);
		System.out.println("");
	}
	
	private void mostrarTodosProdutos(List<Produto> listaProdutos) {
		System.out.println("---------------------------------------------");
		System.out.println("Lista de produtos:");
		for(Produto produto : listaProdutos) {
			System.out.println(produto);
			System.out.println("");
		}
	}
	
	private Produto escolherProduto(List<Produto> listaProdutos) {
		int numero;
		
		if(listaProdutos.size() == 0) {
			System.out.println("Produto não encontrado");
			return null;
		}
		
		while(true){
			mostrarTodosProdutos(listaProdutos);
		
			System.out.print("Digite o numero do produto: ");
			numero = sc.nextInt();
			sc.nextLine();
			
			for(int i = 0; i < listaProdutos.size(); i++) {
				if(listaProdutos.get(i).getId() == numero) {
					return listaProdutos.get(i);
				}
			}
			System.out.println("Opção inválida");
			continue;	
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
	
	private void ordenarPedidoData(int ordenacao, Admin admin, LocalDate[] datas) {
		switch(ordenacao) {
		case 1:
			Collections.sort(admin.getPedidos(), new PedidoNumeroComparator());
			mostrarTodosPedidos(admin.consultarDataPedido(datas));
			break;
		
		case 2:
			Collections.sort(admin.getPedidos(), new PedidoDataComparator());
			mostrarTodosPedidos(admin.consultarDataPedido(datas));
			break;
		
		case 3:
			Collections.sort(admin.getPedidos(), new PedidoPrecoComparator());
			mostrarTodosPedidos(admin.consultarDataPedido(datas));
			break;
		
		case 4:
			Collections.sort(admin.getPedidos(), new PedidoStatusComparator());
			mostrarTodosPedidos(admin.consultarDataPedido(datas));
			break;
		}
	}
	
	private void ordenarPedido(int ordenacao, Admin admin) {
		switch(ordenacao) {
		case 1:
			Collections.sort(admin.getPedidos(), new PedidoNumeroComparator());
			mostrarTodosPedidos(admin.getPedidos());
			break;
		
		case 2:
			Collections.sort(admin.getPedidos(), new PedidoDataComparator());
			mostrarTodosPedidos(admin.getPedidos());
			break;
		
		case 3:
			Collections.sort(admin.getPedidos(), new PedidoPrecoComparator());
			mostrarTodosPedidos(admin.getPedidos());
			break;
		
		case 4:
			Collections.sort(admin.getPedidos(), new PedidoStatusComparator());
			mostrarTodosPedidos(admin.getPedidos());
			break;
		}
	}
	
	private Produto ordenarProduto(int ordenacao, List<Produto> produtos) {
		switch(ordenacao) {
		case 1:
			Collections.sort(produtos, new ProdutoCodigoComparator());
			return escolherProduto(produtos);
		
		case 2:
			Collections.sort(produtos, new ProdutoNomeComparator());
			return escolherProduto(produtos);
		
		case 3:
			Collections.sort(produtos, new ProdutoDescricaoComparator());
			return escolherProduto(produtos);
		}
		return null;
	}
}
