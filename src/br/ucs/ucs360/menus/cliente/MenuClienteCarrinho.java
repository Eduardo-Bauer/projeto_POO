package br.ucs.ucs360.menus.cliente;

import java.time.LocalDate;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.ucs.ucs360.comparadores.ProdutoDescricaoComparator;
import br.ucs.ucs360.comparadores.ProdutoNomeComparator;
import br.ucs.ucs360.comparadores.ProdutoCodigoComparator;
import br.ucs.ucs360.dadosLoja.BancoDados;
import br.ucs.ucs360.execoes.ErroGravacaoException;
import br.ucs.ucs360.execoes.QuantidadeInsuficienteException;
import br.ucs.ucs360.logistica.ItemPedido;
import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.logistica.Pedido;
import br.ucs.ucs360.logistica.Produto;
import br.ucs.ucs360.menus.MenuComparator;
import br.ucs.ucs360.menus.MenuConsulta;
import br.ucs.ucs360.usuarios.Cliente;

public class MenuClienteCarrinho {
	private Scanner sc;
	
	public MenuClienteCarrinho(Cliente clienteAtivo, Loja loja) throws JsonProcessingException, ErroGravacaoException {
		sc = new Scanner(System.in);
		BancoDados bancoDados = new BancoDados();
		int opcao = 0;
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("1 - Consultar carrinho");
				System.out.println("2 - adicionar produto no carrinho");
				System.out.println("3 - remover produto do carrinho");
				System.out.println("4 - finalizar carrinho");
				System.out.println("0 - Voltar para o menu inicial");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				switch(opcao) {
				case 1:
					mostrarCarrinho(clienteAtivo.getCarrinho());
					break;
					
				case 2:
					String[] filtro = new MenuConsulta().consultaEspecifica();
					if(filtro != null) {
						if(filtro[0] != null) {
							Produto produto = (loja.consultarIdProduto(filtro[0]));
							mostrarProduto(produto);
							if(produto != null) {
								criarProdutoCarrinho(clienteAtivo, produto);
							}
						}else {
							int ordenacao = new MenuComparator().fornecedorProdutoComparator();
							if(filtro[1] != null) {
								Produto produto1 = ordenarProduto(ordenacao, loja.consultarNomeDescricaoProduto(filtro[1]));
								if(produto1 != null) {
									criarProdutoCarrinho(clienteAtivo, produto1);
								}
								
							}else {
								Produto produto2 = ordenarProduto(ordenacao, loja.getListaProdutos());
								if(produto2 != null) {
									criarProdutoCarrinho(clienteAtivo, produto2);
								}
							}
						}
					}
					break;
					
				case 3:
					ItemPedido pedido = escolherItemPedido(clienteAtivo.getCarrinho());
					if(pedido != null) {
						pedido.getProduto().getEstoque().aumentarQuantidade(pedido.getQuantidade());
						clienteAtivo.getCarrinho().remove(pedido);
						System.out.println("Item no carrinho removido com sucesso!");
						
					}else {
						System.out.println("É necessário ter um item no carrinho antes");
					}
					break;
				
				case 4:
					if(clienteAtivo.getCarrinho().size() > 0) {
						Pedido pedidoNovo = criarPedido(clienteAtivo);
						clienteAtivo.adicionarPedido(pedidoNovo);
						loja.getListaPedidos().add(pedidoNovo);
						clienteAtivo.getCarrinho().clear();
						System.out.println("Pedido criado com sucesso!");
					}else {
						System.out.println("É necessário ter um item no carrinho antes");
					}
					
					break;
					
				case 0:
					System.out.println("Voltando...");
					break;
				
				default: 
					System.out.println("Opção inválida");
					break;
				}
				bancoDados.gravaJSONLoja("banco_de_dados/loja.json", loja);
			}catch(InputMismatchException e) {
	            System.out.println("Digite apenas números válidos.");
	            opcao = -1;
	            sc.nextLine();
	        }

		}while(opcao != 0);
	}
	
	private Pedido criarPedido(Cliente cliente) {
		Pedido pedido = new Pedido();
		pedido.setNumero(cliente.getPedidos().size());
		pedido.setDataPedido(LocalDate.now());
		pedido.setSituacao("NOVO");
		
		for(ItemPedido item : cliente.getCarrinho()) {
			pedido.getItemPedidos().add(item);
			pedido.setPrecoTotal(item.getPreco());
		}
		
		return pedido;
	}
	
	private void criarProdutoCarrinho(Cliente cliente, Produto produto) {
		if (produto.getEstoque() != null && produto.getEstoque().getQuantidade() >= 1) {
			ItemPedido pedido = new ItemPedido();
			pedido.setProduto(produto);
			
			try {
				System.out.print("Digite a quantidade do pedido: ");
				pedido.setQuantidade(sc.nextInt());
				
				produto.getEstoque().diminuirQuantidade(pedido.getQuantidade());
				pedido.setPreco(pedido.getQuantidade() * produto.getEstoque().getPreco() * 1.17);
				
			} catch (QuantidadeInsuficienteException e) {
				System.out.println("Quantidade insuficiente no estoque!");
				System.out.print("temos disponível: " + produto.getEstoque().getQuantidade() + " quer continuar? s - sim / n - não: ");
				String resposta = sc.next();
				
				if(resposta.toUpperCase().equals("S")) {
					pedido.setQuantidade(produto.getEstoque().getQuantidade());
					produto.getEstoque().setQuantidade(0);
					pedido.setPreco(pedido.getQuantidade() * produto.getEstoque().getPreco() * 1.17);
					
				}else {
					System.out.println("Produto cancelado");
					return;
				}
			}
			
			System.out.println("seu pedido: ");
			System.out.println(pedido);
			System.out.print("Confirmar pedido (s - sim / n - não): ");
			String resposta = sc.next();
			
			if(resposta.toUpperCase().equals("S")) {
				cliente.adicionarProdutoCarrinho(pedido);
				System.out.println("Produto adicionado no carrinho!");
				
			}else {
				produto.getEstoque().aumentarQuantidade(pedido.getQuantidade());
				System.out.println("Pedido cancelado");
			}
			
		}else {
			System.out.println("Produto indisponível");
		}
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
			System.out.println(produto + " | Status: " + (produto.getEstoque() != null && produto.getEstoque().getQuantidade() >= 1? "Disponível" : "Produto indisponível"));
			System.out.println("");
		}
	}
	
	private Produto escolherProduto(List<Produto> listaProdutos) {
		int numero;
		
		if(listaProdutos.size() == 0) {
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
	
	private void mostrarCarrinho(List<ItemPedido> carrinho) {
		int i = 0;
		System.out.println("---------------------------------------------");
		System.out.println("Carrinho:");
		double valorTotal = 0;
		for(ItemPedido pedido : carrinho) {
			System.out.println(i++ + " " + pedido);
			valorTotal += pedido.getPreco();
		}
		if(carrinho.size() == 0) {
			System.out.println("sem produtos ainda");
		}else {
			System.out.println("Valor total do carrinho: " + valorTotal);
		}
	}
	
	private ItemPedido escolherItemPedido(List<ItemPedido> listaPedidos) {
		int numero;
		
		if(listaPedidos.size() == 0) {
			return null;
		}
		
		while(true){
			mostrarCarrinho(listaPedidos);
		
			System.out.print("Digite o numero do produto: ");
			numero = sc.nextInt();
			sc.nextLine();
			
			if(numero >= 0 && numero < listaPedidos.size()) {
				return listaPedidos.get(numero);
			}
			                                             
			System.out.println("Opção inválida");
			continue;	
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
