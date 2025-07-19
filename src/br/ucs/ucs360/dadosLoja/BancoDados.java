package br.ucs.ucs360.dadosLoja;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ucs.ucs360.execoes.ErroGravacaoException;
import br.ucs.ucs360.execoes.ErroLeituraException;
import br.ucs.ucs360.logistica.Loja;

public class BancoDados {

	public Loja leJSONLoja(String nomeArquivo) throws ErroLeituraException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		String json = null;
		Loja loja = null;
		try {
			json = this.leArquivo(nomeArquivo);
			loja = mapper.readValue(json, Loja.class);
			loja.setUltimoEstoque();
			loja.setUltimoFornecedor();
			loja.setUltimoProduto();
			loja.setUltimoPedido();
		} catch (IOException e) {
			throw new ErroLeituraException(e);
		}
		return loja;
	}
    
    public void gravaJSONLoja(String nomeArquivo, Loja loja) throws ErroGravacaoException, JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.registerModule(new JavaTimeModule());
    	mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    	String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(loja);
    	try {
    		this.gravaArquivo(nomeArquivo, json);
    	} catch (IOException e) {
    		throw new ErroGravacaoException(e);
    	}
    }
    
    private String leArquivo(String nomeArquivo) throws IOException {
    	StringBuilder sb = new StringBuilder();
    	FileInputStream fis = new FileInputStream(nomeArquivo);
    	InputStreamReader isr = new InputStreamReader(fis);
    	BufferedReader br = new BufferedReader(isr);
    	String linha;
    	while((linha = br.readLine()) != null) {
    		sb.append(linha);
    		sb.append("\n");
    	}
    	fis.close();
    	return sb.toString();
    }
    
    private void gravaArquivo(String nomeArquivo, String texto) throws IOException {
    	FileWriter fw = new FileWriter(nomeArquivo);
    	fw.write(texto);
    	fw.close();
    }
	
}
