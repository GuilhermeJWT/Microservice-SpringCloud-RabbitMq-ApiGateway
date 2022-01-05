package br.com.systemsgs.controller;

import br.com.systemsgs.service.VendaService;
import br.com.systemsgs.vo.VendaVO;
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
@RequestMapping(value = "/v1/vendas")
public class VendaController {

    private final VendaService vendaService;
    private final PagedResourcesAssembler<VendaVO> assembler;

    public VendaController(VendaService vendaService, PagedResourcesAssembler assembler){
        this.vendaService = vendaService;
        this.assembler = assembler;
    }

    @PostMapping(value = "/salvar", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public VendaVO salvar(@RequestBody VendaVO vendaVO){
        VendaVO vendaSalva = vendaService.create(vendaVO);
        vendaSalva.add(linkTo(methodOn(VendaController.class).findById(vendaSalva.getId())).withSelfRel());

        return vendaSalva;
    }

    @PutMapping(value = "/alterar", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public VendaVO alterar(@RequestBody VendaVO vendaVO){
        VendaVO vendaSalva = vendaService.alterar(vendaVO);
        vendaSalva.add(linkTo(methodOn(VendaController.class).findById(vendaVO.getId())).withSelfRel());

        return vendaSalva;
    }

    @GetMapping(value = "/listar", produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "12") int limit,
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection,"data"));

        Page<VendaVO> vendas = vendaService.findAll(pageable);
        vendas.stream()
                .forEach(v -> v.add(linkTo(methodOn(VendaController.class).findById(v.getId())).withSelfRel()));

        PagedModel<EntityModel<VendaVO>> pagedModel = assembler.toModel(vendas);

        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @GetMapping(value = "/pesquisa/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public VendaVO findById(@PathVariable Long id){
        VendaVO vendaVO = vendaService.findById(id);
        vendaVO.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());

        return vendaVO;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        vendaService.delete(id);

        return ResponseEntity.ok().build();
    }

}
