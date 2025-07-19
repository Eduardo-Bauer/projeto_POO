package br.ucs.ucs360.logistica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.ucs.ucs360.usuarios.Admin;
import br.ucs.ucs360.usuarios.Cliente;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Loja {
	private List<Cliente> listaClientes;
	private List<Admin> listaAdmins;
	private List<Estoque> listaEstoques;
	private List<Fornecedor> listaFornecedores;
	private List<Produto> listaProdutos;
	private List<Pedido> listaPedidos;
	
	public Loja() {
		this.listaClientes = new ArrayList<>();
		this.listaAdmins = new ArrayList<>();
		this.listaEstoques = new ArrayList<>();
		this.listaFornecedores = new ArrayList<>();
		this.listaProdutos = new ArrayList<>();
		this.listaPedidos = new ArrayList<>();
		
	}
	
	public void setUltimoPedido() {
		int maiorIdPedido = 0;
		
		for (Pedido p : this.getListaPedidos()) {
			maiorIdPedido = Math.max(maiorIdPedido, p.getNumero());
		}
		
		Estoque.setUltimoEstoque(maiorIdPedido+1);
	}
	
	public void setUltimoEstoque() {
		int maiorIdEstoque = 0;
		
		for (Estoque e : this.getListaEstoques()) {
			maiorIdEstoque = Math.max(maiorIdEstoque, e.getId());
		}
		
		Estoque.setUltimoEstoque(maiorIdEstoque+1);
	}
	
	public void setUltimoProduto() {
		int maiorIdProduto = 0;
		
		for (Produto p : this.getListaProdutos()) {
			maiorIdProduto = Math.max(maiorIdProduto, p.getId());
		}
		
		Produto.setUltimoProduto(maiorIdProduto+1);
	}
	
	public void setUltimoFornecedor() {
		int maiorId = 0;
		
		for (Fornecedor f : this.getListaFornecedores()) {
			maiorId = Math.max(maiorId, f.getId());
		}
		
		Fornecedor.setUltimoFornecedor(maiorId+1);
	}
	
	public boolean adicionarCliente(Cliente cliente) {
		for(Cliente lista : listaClientes) {
			if(lista.getNome().equals(cliente.getNome()) || lista.getDado().getLogin().equals(cliente.getDado().getLogin())) {
				return false;
			}
		}
		listaClientes.add(cliente);
		Cliente.setUltimoCliente(Cliente.getUltimoCliente() + 1);
		
		return true;
	}
	
	public boolean adicionarAdmin(Admin admin) {
		for(Admin lista : listaAdmins) {
			if(lista.getDado().getLogin().equals(admin.getDado().getLogin())) {
				return false;
			}
		}
		listaAdmins.add(admin);
		return true;
	}
	
	public boolean adicionarEstoque(Estoque estoque) {
		for(Produto produto : listaProdutos) {
			if(produto == estoque.getProduto() && produto.getEstoque( ) == null) {
				produto.setEstoque(estoque);
				listaEstoques.add(estoque);
				Estoque.setUltimoEstoque(Estoque.getUltimoEstoque() + 1);
				return true;
			}
		}
		return false;
	}
	
	public List<Estoque> consultarNomeEstoque(String nome){
		List<Estoque> listaEstoqueEncontrado  = new ArrayList<>();
		for(Estoque estoque : listaEstoques) {
			if(estoque.getProduto().getNome().toUpperCase().contains(nome.toUpperCase())){
				listaEstoqueEncontrado.add(estoque);
			}
		}
		return listaEstoqueEncontrado;
	}
	
	public Estoque consultarIdEstoque(String id) {
		String idEstoque;
		
		for(Estoque estoque : listaEstoques) {
			idEstoque = Integer.toString(estoque.getId());
			if(id.equals(idEstoque)) {
				return estoque;
			}
		}
		return null;
	}
	
	public void removerEstoque(Estoque estoque) {
		estoque.getProduto().setEstoque(null);
		listaEstoques.remove(estoque);
	}
	
	public boolean adicionarFornecedor(Fornecedor fornecedor) {

		for(Fornecedor lista : listaFornecedores) {
			if(lista.getNome().equals(fornecedor.getNome())) {
				return false;
			}
		}
		listaFornecedores.add(fornecedor);
		Fornecedor.setUltimoFornecedor(Fornecedor.getUltimoFornecedor() + 1);
		
		return true;
	}
	
	public List<Fornecedor> consultarNomeFornecedor(String nome){
		List<Fornecedor> listaFornecedorEncontrado  = new ArrayList<>();
		for(Fornecedor fornecedor : listaFornecedores) {
			if(fornecedor.getNome().toUpperCase().contains(nome.toUpperCase())){
				listaFornecedorEncontrado.add(fornecedor);
			}
		}
		return listaFornecedorEncontrado;
	}
	
	public Fornecedor consultarIdFornecedor(String id) {
		String idFornecedor;
		
		for(Fornecedor fornecedor : listaFornecedores) {
			idFornecedor = Integer.toString(fornecedor.getId());
			if(id.equals(idFornecedor)) {
				return fornecedor;
			}
		}
		return null;
	}
	
	public void removerFornecedor(Fornecedor fornecedor) {
		listaFornecedores.remove(fornecedor);
	}
	
	public boolean adicionarProduto(Produto produto) {
		for(Produto lista : listaProdutos) {
			if(lista.getNome().equals(produto.getNome())) {
				return false;
			}
		}
		listaProdutos.add(produto);
		Produto.setUltimoProduto(Produto.getUltimoProduto() + 1);
		produto.getFornecedor().getListaProdutos().add(produto);
		
		return true;
	}
	
	public List<Produto> consultarNomeDescricaoProduto(String nome){
		List<Produto> listaProdutoEncontrado  = new ArrayList<>();
		
		for(Produto produto : listaProdutos) {
			if(produto.getNome().toUpperCase().contains(nome.toUpperCase()) || produto.getDescricao().toUpperCase().contains(nome.toUpperCase())){
				listaProdutoEncontrado.add(produto);
			}
		}
		return listaProdutoEncontrado;
	}
	
	public Produto consultarIdProduto(String id) {
		String idProduto;
		
		for(Produto produto : listaProdutos) {
			idProduto = Integer.toString(produto.getId());
			if(id.equals(idProduto)) {
				return produto;
			}
		}
		return null;
	}
	
	public void removerProduto(Produto produto) {
		for(Fornecedor fornecedor : listaFornecedores) {
			if(fornecedor.getListaProdutos().contains(produto)) {
				fornecedor.getListaProdutos().remove(produto);
				break;
			}
		}
		listaEstoques.remove(produto.getEstoque());
		listaProdutos.remove(produto);
	}
	
	public Pedido consultarIdPedido(String id) {
		String idPedido;
		
		for(Pedido pedido : this.listaPedidos) {
			idPedido = Integer.toString(pedido.getNumero());
			if(id.equals(idPedido)) {
				return pedido;
			}
		}
		return null;
	}
	
	public List<Pedido> consultarDataPedido(LocalDate[] datas){
		List<Pedido> listaPedidoEncontrado  = new ArrayList<>();
		for(Pedido pedido : this.listaPedidos) {
			if(pedido.getDataPedido().isAfter(datas[0]) && pedido.getDataPedido().isBefore(datas[1])){
				listaPedidoEncontrado.add(pedido);
			}
		}
		return listaPedidoEncontrado;
	}
	
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Admin> getListaAdmins() {
		return listaAdmins;
	}

	public void setListaAdmins(List<Admin> listaAdmins) {
		this.listaAdmins = listaAdmins;
	}

	public List<Estoque> getListaEstoques() {
		return listaEstoques;
	}
	
	public void setListaEstoques(List<Estoque> listaEstoques) {
		this.listaEstoques = listaEstoques;
	}
	
	public List<Fornecedor> getListaFornecedores() {
		return listaFornecedores;
	}
	
	public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
		this.listaFornecedores = listaFornecedores;
	}
	
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
}
