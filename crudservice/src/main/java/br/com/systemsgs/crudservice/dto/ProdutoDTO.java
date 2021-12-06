package br.com.systemsgs.crudservice.dto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private Integer estoque;
	
	private Double preco;
	
}
