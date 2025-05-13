package br.ucs.ucs360.informacoes;

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
