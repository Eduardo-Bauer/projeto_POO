package Principal;

import java.util.Scanner;

	public class Funcionalidades {
		private Funcionalidades() {
			
		}
		
	    public static Dado preencherDados(boolean tipo) {
	    	Dado dado = new Dado();
	        Scanner scanner = new Scanner(System.in);
	
	        System.out.print("Digite o login: ");
	        dado.setSenha(scanner.nextLine());
	
	        System.out.print("Digite a senha: ");
	        dado.setSenha(scanner.nextLine());
	
	        dado.setTipo(tipo);
	        
	        System.out.println("Conta criada com sucesso!");
	        if (dado.isTipo()) {
	            System.out.println("Usuário criado como ADMINISTRADOR.");
	   
	        } else {
	            System.out.println("Usuário criado como CLIENTE");
	        }
	        return dado;
	    }
}
