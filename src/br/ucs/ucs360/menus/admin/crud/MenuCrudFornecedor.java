package br.ucs.ucs360.menus.admin.crud;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.ucs.ucs360.comparadores.FornecedorDescrucaoComparator;
import br.ucs.ucs360.comparadores.FornecedorNomeComparator;
import br.ucs.ucs360.comparadores.FornecedorCodigoComparator;
import br.ucs.ucs360.dadosLoja.BancoDados;
import br.ucs.ucs360.execoes.ErroGravacaoException;
import br.ucs.ucs360.logistica.Fornecedor;
import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.logistica.Produto;
import br.ucs.ucs360.menus.MenuComparator;
import br.ucs.ucs360.menus.MenuConsulta;
import br.ucs.ucs360.menus.atualizacao.MenuAtualizacaoFornecedor;

public class MenuCrudFornecedor {
	private Scanner sc;
	
	public MenuCrudFornecedor(Loja loja) throws JsonProcessingException, ErroGravacaoException {
		sc = new Scanner(System.in);
		BancoDados bancoDados = new BancoDados();
		int opcao = 0;
		
		do {
			try {
				System.out.println("---------------------------------------------");
				System.out.println("Selecione o que fazer:");
				System.out.println("1 - Cadastrar fornecedor");
				System.out.println("2 - Consultar fornecedor");
				System.out.println("3 - Atualizar fornecedor");
				System.out.println("4 - Remover fornecedor");
				System.out.println("0 - Voltar para o menu admin");
				System.out.print("Digite sua opção: ");
				opcao = sc.nextInt();
				sc.nextLine();
				switch(opcao) {
				case 1:
					System.out.println((loja.adicionarFornecedor(cadastrarFornecedor()) ? "Fornecedor cadastrado com sucesso!" : "Fornecedor já cadastrado"));
					break;
					
				case 2:
					String[] filtro = new MenuConsulta().consultaEspecifica();
					if(filtro != null) {
						if(filtro[0] != null) {
							mostrarFornecedor(loja.consultarIdFornecedor(filtro[0]));
						}else {
							int ordenacao = new MenuComparator().fornecedorProdutoComparator();
							if(filtro[1] != null) {
								ordenarFornecedorMostrar(ordenacao, loja.consultarNomeFornecedor(filtro[1]));
								
							}else {
								ordenarFornecedorMostrar(ordenacao, loja.getListaFornecedores());
							}
						}
					}
					break;
					
				case 3:
					int ordenacao = new MenuComparator().fornecedorProdutoComparator();
					Fornecedor fornecedor = ordenarFornecedorEscolher(ordenacao, loja.getListaFornecedores());
					if(fornecedor != null) {
						new MenuAtualizacaoFornecedor(fornecedor, loja);
						
					}else {
						System.out.println("É necessário cadastrar um fornecedor antes");
					}
					break;
					
				case 4:
					int ordenacao1 = new MenuComparator().fornecedorProdutoComparator();
					Fornecedor fornecedor1 = ordenarFornecedorEscolher(ordenacao1, loja.getListaFornecedores());
					if(fornecedor1 != null && fornecedor1.getListaProdutos().size() == 0) {
						loja.removerFornecedor(fornecedor1);
						System.out.println("Fornecedor removido com sucesso!");
						
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
				bancoDados.gravaJSONLoja("banco_de_dados/loja.json", loja);
			}catch(InputMismatchException e) {
                System.out.println("Digite apenas números válidos.");
                opcao = -1;
                sc.nextLine();
            }
	
		}while(opcao != 0);
	}
	
	private Fornecedor cadastrarFornecedor() {
		Fornecedor fornecedor = new Fornecedor();
		
		System.out.print("Digite nome: ");
		fornecedor.setNome(sc.nextLine());
		
		System.out.print("Digite uma descrição: ");
		fornecedor.setDescricao(sc.nextLine());
		
		System.out.print("Digite telefone: ");
		fornecedor.setTelefone(sc.nextLine());
		
		System.out.print("Digite email: ");
		fornecedor.setEmail(sc.nextLine());
		
		System.out.println(Fornecedor.getUltimoFornecedor());
		fornecedor.setId(Fornecedor.getUltimoFornecedor());
		
		System.out.print("Digite rua: ");
		fornecedor.getEndereco().setRua(sc.nextLine());
		
		System.out.print("Digite bairro: ");
		fornecedor.getEndereco().setBairro(sc.nextLine());
		
		System.out.print("Digite numero: ");
		fornecedor.getEndereco().setNumero(sc.nextLine());
		
		System.out.print("Digite cidade: ");
		fornecedor.getEndereco().setCidade(sc.nextLine());
		
		System.out.print("Digite estado: ");
		fornecedor.getEndereco().setEstado(sc.nextLine());
		
		System.out.print("Digite cep: ");
		fornecedor.getEndereco().setCep(sc.nextLine());
		
		System.out.print("Digite algum complemento: ");
		fornecedor.getEndereco().setComplemento(sc.nextLine());
		
		return fornecedor;
	}
	
	private void mostrarFornecedor(Fornecedor fornecedor) {
		if(fornecedor == null) {
			System.out.println("Fornecedor não encontrado");
			return;
		}
		System.out.println("---------------------------------------------");
		System.out.println("Fornecedor encontrado:");
		System.out.println(fornecedor);
		System.out.println("");
	}
	
	private void mostrarProdutosVinculados(Fornecedor fornecedor) {
		for(Produto produto : fornecedor.getListaProdutos()) {
			System.out.println(produto);
		}
	}
	
	private void mostrarTodosFornecedores(List<Fornecedor> listaFornecedores) {
		System.out.println("---------------------------------------------");
		System.out.println("Lista de fornecedores:");
		for(Fornecedor fornecedor : listaFornecedores) {
			System.out.println(fornecedor);
			System.out.println("Produtos vinculados: ");
			mostrarProdutosVinculados(fornecedor);
			System.out.println("");
		}
	}
	
	private Fornecedor escolherFornecedor(List<Fornecedor> listaFornecedores) {
		int numero;
		
		if(listaFornecedores.size() == 0) {
			return null;
		}
		
		while(true){
			mostrarTodosFornecedores(listaFornecedores);
		
			System.out.print("Digite o numero do fornecedor: ");
			numero = sc.nextInt();
			sc.nextLine();
			
			for(int i = 0; i < listaFornecedores.size(); i++) {
				if(listaFornecedores.get(i).getId() == numero) {
					return listaFornecedores.get(i);
				}
			}
			System.out.println("Opção inválida");
			continue;	
		} 
	}
	
	private void ordenarFornecedorMostrar(int ordenacao, List<Fornecedor> fornecedores) {
		switch(ordenacao) {
		
		case 1:
			Collections.sort(fornecedores, new FornecedorCodigoComparator());
			mostrarTodosFornecedores(fornecedores);
			break;
		
		case 2:
			Collections.sort(fornecedores, new FornecedorNomeComparator());
			mostrarTodosFornecedores(fornecedores);
			break;
			
		case 3:
			Collections.sort(fornecedores, new FornecedorDescrucaoComparator());
			mostrarTodosFornecedores(fornecedores);
			break;
		}
	}
	
	private Fornecedor ordenarFornecedorEscolher(int ordenacao, List<Fornecedor> fornecedores) {
		switch(ordenacao) {
		
		case 1:
			Collections.sort(fornecedores, new FornecedorCodigoComparator());
			return escolherFornecedor(fornecedores);
		
		case 2:
			Collections.sort(fornecedores, new FornecedorNomeComparator());
			return escolherFornecedor(fornecedores);
			
		case 3:
			Collections.sort(fornecedores, new FornecedorDescrucaoComparator());
			return escolherFornecedor(fornecedores);
		}
		
		return null;
	}
}
