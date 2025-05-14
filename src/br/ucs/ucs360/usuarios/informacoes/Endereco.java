package br.ucs.ucs360.usuarios.informacoes;

import java.util.Scanner;

public class Endereco {
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String complemento;
	private Scanner sc;
	
	private void preencherDados() {
		sc = new Scanner(System.in);
		
		System.out.print("Digite rua: ");
		this.setRua(sc.nextLine());
		
		System.out.print("Digite bairro: ");
		this.setBairro(sc.nextLine());
		
		System.out.print("Digite numero: ");
		this.setNumero(sc.nextLine());
		
		System.out.print("Digite cidade: ");
		this.setCidade(sc.nextLine());
		
		System.out.print("Digite estado: ");
		this.setEstado(sc.nextLine());
		
		System.out.print("Digite cep: ");
		this.setCep(sc.nextLine());
		
		System.out.print("Digite algum complemento: ");
		this.setComplemento(sc.nextLine());
	}
	
	public void atualizarRua() {
		System.out.print("Digite a nova rua: ");
		this.setRua(sc.nextLine());
		System.out.println("Rua atualizada com sucesso!");
	}
	
	public void atualizarBairro() {
		System.out.print("Digite o novo bairro: ");
		this.setBairro(sc.nextLine());	
		System.out.println("Bairro atualizada com sucesso!");		
	}
	
	public void atualizarNumero() {
		System.out.print("Digite o novo numero: ");
		this.setNumero(sc.nextLine());
		System.out.println("Numero atualizado com sucesso!");
	}
	
	public void atualizarCidade() {
		System.out.print("Digite a nova cidade: ");
		this.setCidade(sc.nextLine());
		System.out.println("Cidade atualizada com sucesso!");
	}
	
	public void atualizarEstado() {
		System.out.print("Digite o novo estado: ");
		this.setEstado(sc.nextLine());
		System.out.println("Estado atualizado com sucesso!");
	}
	
	public void atualizarCep() {
		System.out.print("Digite o novo cep: ");
		this.setCep(sc.nextLine());
		System.out.println("Cep atualizado com sucesso!");
	}
	
	public void atualziarComplemento() {
		System.out.print("Digite algum novo complemento: ");
		this.setComplemento(sc.nextLine());
		System.out.println("Complemento atualizado com sucesso!");
	}
	
	public Endereco() {
		preencherDados();
	}
	
	public String getRua() {
		return rua;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
