package Principal;

import java.util.Scanner;

public class Dado {
	private String login;
	private String senha;
	private boolean tipo;
	
    private void preencherDados(boolean tipo) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o login: ");
        this.setLogin(sc.nextLine());
        
        System.out.print("Digite a senha: ");
        this.setSenha(sc.nextLine());
        		
        this.setTipo(tipo);
    }
	
	public Dado(boolean tipo) {
		preencherDados(tipo);
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean isTipo() {
		return tipo;
	}
	
	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}
}
