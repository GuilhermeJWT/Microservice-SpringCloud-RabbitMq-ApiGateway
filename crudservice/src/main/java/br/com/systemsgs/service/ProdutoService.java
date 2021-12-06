package br.com.systemsgs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systemsgs.config.DozerConverter;
import br.com.systemsgs.dto.ModelProdutoDTO;
import br.com.systemsgs.exception.RecursoNaoEncontradoException;
import br.com.systemsgs.model.ModelProduto;
import br.com.systemsgs.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public ModelProdutoDTO salvar(ModelProdutoDTO produtoDTO) {
		ModelProduto produto = DozerConverter.converteEntidade(produtoDTO, ModelProduto.class);
		ModelProdutoDTO produtoRetorno = DozerConverter.converteEntidade(produtoRepository.save(produto), ModelProdutoDTO.class);
		return produtoRetorno;
	}
	
	public List<ModelProdutoDTO> findAll() {
		return DozerConverter.converteList(produtoRepository.findAll(), ModelProdutoDTO.class);
	}	
	
	public ModelProdutoDTO findById(Long id) {
		ModelProduto modelProduto = produtoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException());
		return DozerConverter.converteEntidade(modelProduto, ModelProdutoDTO.class);
	}
		
	public ModelProdutoDTO update(ModelProdutoDTO produtoDTO) {
		ModelProduto modelProduto = produtoRepository.findById(produtoDTO.getId())
				.orElseThrow(() -> new RecursoNaoEncontradoException());
		
		modelProduto.setEstoque(produtoDTO.getEstoque());
		modelProduto.setNome(produtoDTO.getNome());
		modelProduto.setPreco(produtoDTO.getPreco());
		
		ModelProdutoDTO produtoRetorno = DozerConverter.converteEntidade(produtoRepository.save(modelProduto), ModelProdutoDTO.class);
		return produtoRetorno;
	}	
	
	public void delete(Long id) {
		ModelProduto modelProduto = produtoRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException());
		produtoRepository.delete(modelProduto);
	}
	
}
