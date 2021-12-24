package br.com.systemsgs.controller;

import br.com.systemsgs.serivice.ProdutoService;
import br.com.systemsgs.vo.ProdutoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final PagedResourcesAssembler<ProdutoVO> assembler;

    public ProdutoController(ProdutoService produtoService, PagedResourcesAssembler assembler) {
        this.produtoService = produtoService;
        this.assembler = assembler;
    }

    @PostMapping(value = "/salvar", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ProdutoVO salvar(@RequestBody ProdutoVO produtoVO){
        ProdutoVO produtoSalvo = produtoService.create(produtoVO);
        produtoVO.add(linkTo(methodOn(ProdutoController.class).findById(produtoSalvo.getId())).withSelfRel());

        return produtoSalvo;
    }

    @PutMapping(value = "/alterar", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ProdutoVO alterar(@RequestBody ProdutoVO produtoVO){
        ProdutoVO produtoSalvo = produtoService.alterar(produtoVO);
        produtoVO.add(linkTo(methodOn(ProdutoController.class).findById(produtoSalvo.getId())).withSelfRel());

        return produtoSalvo;
    }

    @GetMapping(value = "/listas", produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<ProdutoVO> findAll(@RequestParam(value = "page", defaultValue = "0") int limit,
                                             @RequestParam(value = "limit", defaultValue = "12") int page,
                                             @RequestParam(value = "direction", defaultValue = "0") String direction){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
        Page<ProdutoVO> produtos = produtoService.findAll(pageable);

        produtos.stream().forEach(p -> p.add(linkTo(methodOn(ProdutoController.class).findById(p.getId())).withSelfRel()));
        PagedModel<EntityModel<ProdutoVO>> pagedModel = assembler.toModel(produtos);

        return new ResponseEntity(pagedModel, HttpStatus.OK);
    }

    @GetMapping(value = "/pesquisa/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public ProdutoVO findById(@PathVariable Long id){
        ProdutoVO produtoVO = produtoService.findById(id);
        produtoVO.add(linkTo(methodOn(ProdutoController.class).findById(id)).withSelfRel());

        return produtoVO;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        produtoService.delete(id);

        return ResponseEntity.ok().build();
    }

}
