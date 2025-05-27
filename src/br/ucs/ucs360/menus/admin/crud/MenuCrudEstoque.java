package br.ucs.ucs360.menus.admin.crud;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.ucs.ucs360.logistica.Estoque;
import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.logistica.Produto;
import br.ucs.ucs360.menus.MenuConsulta;
import br.ucs.ucs360.menus.atualizacao.MenuAtualizacaoEstoque;

public class MenuCrudEstoque {
	private Scanner sc;
	private Estoque estoque;
	private Produto produto;
	
	public MenuCrudEstoque(Loja loja) {
		sc = new Scanner(System.in);
		int opcao = 0;
		
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione o que fazer:");
				System.out.println("1 - Cadastrar estoque");
				System.out.println("2 - Consultar estoque");
				System.out.println("3 - Atualizar estoque");
				System.out.println("4 - Remover estoque");
				System.out.println("0 - Voltar para o menu admin");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				switch(opcao) {
				case 1:
					produto = escolherProduto(loja.getListaProdutos());
					if(produto != null) {
						System.out.println((loja.adicionarEstoque(cadastrarEstoque(produto)) ? "Estoque cadastrado com sucesso!" : "Esse produto já tem um estoque"));
						
					}else {
						System.out.println("É necessário cadastrar um produto antes");
					}
					break;
					
				case 2:
					String[] filtro = new MenuConsulta().consultaEspecifica();
					if(filtro != null) {
						if(filtro[0] != null) {
							mostrarEstoque(loja.consultarIdEstoque(filtro[0]));
							
						}else {
							if(filtro[1] != null) {
								mostrarTodosEstoques(loja.consultarNomeEstoque(filtro[1]));
								
							}else {
								mostrarTodosEstoques(loja.getListaEstoques());
							}
						}
					}
					break;
					
				case 3:
					estoque = escolherEstoque(loja.getListaEstoques());
					if(estoque != null) {
						new MenuAtualizacaoEstoque(estoque, loja);
						
					}else {
						System.out.println("É necessário cadastrar um estoque antes");
					}
					break;
					
				case 4:
					estoque = escolherEstoque(loja.getListaEstoques());
					if(estoque != null) {
						loja.removerEstoque(estoque);
						System.out.println("Estoque removido com sucesso!");
						
					}else {
						System.out.println("É necessário cadastrar um estoque antes");
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
	
	private Estoque cadastrarEstoque(Produto produto) {
		Estoque estoque = new Estoque();
		
		System.out.print("Digite a quantidade: ");
		estoque.setQuantidade(sc.nextInt());
		
		System.out.print("Digite o preco do produto: ");
		estoque.setPreco(sc.nextDouble());
		
		estoque.setId(Estoque.getUltimoEstoque());
		estoque.setProduto(produto);
		
		return estoque;
	}
	
	private void mostrarEstoque(Estoque estoque) {
		if(estoque == null) {
			System.out.println("Estoque não encontrado");
			return;
		}
		
		System.out.println("---------------------------------------------");
		System.out.println("Estoque encontrado:");
		System.out.println(estoque);
		System.out.println("Produto vinculado: ");
		System.out.println(estoque.getProduto());
	}
	
	public void mostrarTodosEstoques(List<Estoque> listaEstoques) {
		System.out.println("---------------------------------------------");
		System.out.println("Lista de estoques:");
		for(Estoque estoque : listaEstoques) {
			System.out.println(estoque);
			System.out.println("Produto vinculado: ");
			System.out.println(estoque.getProduto());
			System.out.println("");
		}
	}
	
	private void mostrarTodosProdutos(List<Produto> listaProdutos) {
		System.out.println("---------------------------------------------");
		System.out.println("Lista de produtos:");
		for(Produto produto : listaProdutos) {
			System.out.println(produto);
			System.out.println("Estoque vinculado: ");
			System.out.println(produto.getEstoque());
			System.out.println("");
		}
	}
	
	private Estoque escolherEstoque(List<Estoque> listaEstoques) {
		int numero;
		
		if(listaEstoques.size() == 0) {
			return null;
		}
		
		while(true){
			mostrarTodosEstoques(listaEstoques);
		
			System.out.print("Digite o numero do estoque: ");
			numero = sc.nextInt();
			sc.nextLine();
			
			for(int i = 0; i < listaEstoques.size(); i++) {
				if(listaEstoques.get(i).getId() == numero) {
					return listaEstoques.get(i);
				}
			}
			System.out.println("Opção inválida");
			continue;	
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
}
