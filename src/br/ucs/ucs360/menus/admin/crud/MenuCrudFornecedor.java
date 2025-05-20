package br.ucs.ucs360.menus.admin.crud;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.ucs.ucs360.logistica.Fornecedor;
import br.ucs.ucs360.logistica.Loja;
import br.ucs.ucs360.logistica.Produto;
import br.ucs.ucs360.menus.MenuConsulta;
import br.ucs.ucs360.menus.atualizacao.MenuAtualizacaoFornecedor;

public class MenuCrudFornecedor {
	private Scanner sc;
	private Fornecedor fornecedor;
	
	public MenuCrudFornecedor(Loja loja) {
		sc = new Scanner(System.in);
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
						if(filtro[2] != null) {
							mostrarTodosFornecedores(loja.getListaFornecedores());
							
						}else {
							mostrarFornecedor(loja.consultarFornecedor(filtro));
						}
					}
					break;
					
				case 3:
					fornecedor = escolherFornecedor(loja.getListaFornecedores());
					if(fornecedor != null) {
						new MenuAtualizacaoFornecedor(fornecedor, loja);
						
					}else {
						System.out.println("É necessário cadastrar um fornecedor antes");
					}
					break;
					
				case 4:
					fornecedor = escolherFornecedor(loja.getListaFornecedores());
					if(fornecedor != null && fornecedor.getListaProdutos().size() == 0) {
						loja.removerFornecedor(fornecedor);
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
}
