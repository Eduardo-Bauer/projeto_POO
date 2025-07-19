package br.ucs.ucs360.logistica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Pedido{
	private int numero;
	private LocalDate dataPedido;
	private LocalDate dataEntraga;
	private LocalDate dataCancelamento;
	private String situacao;
	private List<ItemPedido> itemPedidos;
	private double precoTotal = 0;
	
	public Pedido() {
		itemPedidos = new ArrayList<>();
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public LocalDate getDataPedido() {
		return dataPedido;
	}
	
	public void setDataPedido(LocalDate localDate) {
		this.dataPedido = localDate;
	}
	
	public LocalDate getDataEntraga() {
		return dataEntraga;
	}
	
	public void setDataEntraga(LocalDate dataEntraga) {
		this.dataEntraga = dataEntraga;
	}
	
	public String getSituacao() {
		return situacao;
	}
	
	public void setSituacao(String situacao) {
		this.situacao = situacao.toUpperCase();
	}
	
	public List<ItemPedido> getItemPedidos() {
		return itemPedidos;
	}

	public void setItemPedidos(List<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(double precoProduto) {
		this.precoTotal += precoProduto;
	}
	
	public LocalDate getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(LocalDate dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public String toString() {
		return "Numero: " + this.getNumero() + " | Data do pedido: " + this.getDataPedido() + " | Preço total + 17% ICMS: " + this.getPrecoTotal() + " | Situação: " + this.getSituacao(); 
	}
}
