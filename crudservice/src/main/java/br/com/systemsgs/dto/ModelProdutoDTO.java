package br.com.systemsgs.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ModelProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Id deve ser Informado!!!")
	private Long id;

	@NotBlank(message = "Nome deve ser Informado!!!")
	private String nome;

	@NotNull(message = "Estoque deve ser Informado!!!")
	private Integer estoque;
	
	@NotNull(message = "Preco deve ser Informado!!!")
	private Double preco;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
}
