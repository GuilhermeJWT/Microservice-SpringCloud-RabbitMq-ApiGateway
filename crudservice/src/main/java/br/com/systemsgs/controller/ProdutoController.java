package br.com.systemsgs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.dto.ModelProdutoDTO;
import br.com.systemsgs.service.ProdutoService;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(value = "/listar")
	public List<ModelProdutoDTO> findAll() {
		return produtoService.findAll();
	}	
	
	@GetMapping(value = "/pesquisar/{id}")
	public ModelProdutoDTO findById(@PathVariable("id") Long id) {
		return produtoService.findById(id);
	}	
	
	@PostMapping(value = "/salvar")
	public ModelProdutoDTO create(@RequestBody @Valid ModelProdutoDTO modelProdutoDTO) {
		return produtoService.salvar(modelProdutoDTO);
	}
	
	@PutMapping(value = "/editar")
	public ModelProdutoDTO update(@RequestBody @Valid ModelProdutoDTO modelProdutoDTO) {
		return produtoService.update(modelProdutoDTO);
	}	
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		produtoService.delete(id);
		return ResponseEntity.ok().build();
	}	

}
