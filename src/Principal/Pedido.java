package Principal;

import java.sql.Date;

public class Pedido {
	private int numero;
	private Date dataPedido;
	private Date dataEntraga;
	private String situacao;
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public Date getDataPedido() {
		return dataPedido;
	}
	
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	public Date getDataEntraga() {
		return dataEntraga;
	}
	
	public void setDataEntraga(Date dataEntraga) {
		this.dataEntraga = dataEntraga;
	}
	
	public String getSituacao() {
		return situacao;
	}
	
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
