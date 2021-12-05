package br.com.systemsgs.crudservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systemsgs.crudservice.dto.ProdutoDTO;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoService produtoService;
	
	public ProdutoDTO create(ProdutoDTO produtoVO) {
		return null;
	}

}
