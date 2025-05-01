package Principal;

import java.util.ArrayList;
import java.util.List;

public class Fornecedor extends Pessoa{
	private String descricao;
	private static List<Fornecedor> listaFornecedores = new ArrayList<>();

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static List<Fornecedor> getListaFornecedores() {
		return listaFornecedores;
	}

	public static void setListaFornecedores(List<Fornecedor> listaFornecedores) {
		Fornecedor.listaFornecedores = listaFornecedores;
	}
}
