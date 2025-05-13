package br.ucs.ucs360.informacoes;

import java.util.Scanner;

public abstract class Pessoa {
	private String nome;
	private String telefone;
	private String email;
	private Endereco endereco;
	private int id;
	private Scanner sc;
	
	public void preencherDados(int id) {
		sc = new Scanner(System.in);
		
		System.out.print("Digite nome: ");
		this.setNome(sc.nextLine());
		
		System.out.print("Digite telefone: ");
		this.setTelefone(sc.nextLine());
		
		System.out.print("Digite email: ");
		this.setEmail(sc.nextLine());
		
		this.setId(id);
		
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
