package Principal;

import java.util.Scanner;

public abstract class Pessoa {
	private String nome;
	private String telefone;
	private String email;
	private Endereco endereco;
	private int id;
	
	private void preencherDados() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o seu nome: ");
		this.setNome(sc.nextLine());
		
		System.out.println("Digite o seu telefone: ");
		this.setTelefone(sc.nextLine());
		
		System.out.println("Digite o seu email: ");
		this.setEmail(sc.nextLine());
	}

	public Pessoa() {
		preencherDados();
		endereco = new Endereco();
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	} 
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
