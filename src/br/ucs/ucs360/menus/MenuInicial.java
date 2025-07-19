package br.ucs.ucs360.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.ucs.ucs360.dadosLoja.BancoDados;
import br.ucs.ucs360.logistica.Loja;

public class MenuInicial {
    private Scanner sc;
    private BancoDados bancoDados;
    
    public MenuInicial() throws Exception {
        sc = new Scanner(System.in);
        bancoDados = new BancoDados();
        
        Loja loja = bancoDados.leJSONLoja("banco_de_dados/loja.json");
             
        int opcao = -1;
        do {
            try {
                System.out.println("---------------------------------------------");
                System.out.println("Bem vindo ao UCS 360!!");
                System.out.println("1 - Entrar");
                System.out.println("2 - Cadastre-se");
                System.out.println("0 - Sair");
                System.out.print("Digite sua opção: ");
                opcao = sc.nextInt();
                sc.nextLine();
                
                switch(opcao) {
                    case 1:
                        new MenuEntrarUsuario(loja);
                        break;
                        
                    case 2:
                        new MenuCadastrarUsuario(loja);
                        break;
                        
                    case 0:
                        System.out.println("Saindo...");
                        System.out.println("---------------------------------------------");
                        break;
                        
                    default: 
                        System.out.println("Opção inválida");
                        break;
                }
                
            } catch(InputMismatchException e) {
                System.out.println("Digite apenas números válidos.");
                sc.nextLine();
            }
            
        } while(opcao != 0);
        sc.close();
    }
    
    public static void main(String[] args) throws Exception{
    	new MenuInicial();
    }
}