package br.com.systemsgs.service;

import br.com.systemsgs.exception.ResourceNotFoundException;
import br.com.systemsgs.model.ModelProdutoVenda;
import br.com.systemsgs.model.ModelVenda;
import br.com.systemsgs.repository.ProdutoVendaRepository;
import br.com.systemsgs.repository.VendaRepository;
import br.com.systemsgs.vo.VendaVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoVendaRepository produtoVendaRepository;

    public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoVendaRepository = produtoVendaRepository;
    }

    @Transactional
    public VendaVO create(VendaVO vendaVO){
        ModelVenda venda = vendaRepository.save(ModelVenda.converteEntidade(vendaVO));

        List<ModelProdutoVenda> produtosSalvos = new ArrayList<>();
        vendaVO.getProdutos().forEach(p -> {
            ModelProdutoVenda pv = ModelProdutoVenda.converteEntidade(p);
            pv.setVenda(venda);
            produtosSalvos.add(produtoVendaRepository.save(pv));
        });

        venda.setProdutos(produtosSalvos);

        return VendaVO.converteEntidade(venda);
    }

    public Page<VendaVO> findAll(Pageable pageable){
        var page = vendaRepository.findAll(pageable);

        return page.map(this::convertToProdutoVO);
    }

    public VendaVO findById(Long id){
        var venda = vendaRepository.findById(id).orElseThrow(() -> new ResourceAccessException("Venda não Encontrada!"));

        return VendaVO.converteEntidade(venda);
    }

    public VendaVO alterar(VendaVO vendaVO){
        final Optional<ModelVenda> optionalVenda = vendaRepository.findById(vendaVO.getId());

        if (!optionalVenda.isPresent()){
            new ResourceNotFoundException("Venda não Encontrada!");
        }

        return VendaVO.converteEntidade(vendaRepository.save(ModelVenda.converteEntidade(vendaVO)));
    }

    public void delete(Long id){
        var venda = vendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venda não Encontrada!"));
        vendaRepository.delete(venda);
    }

    private VendaVO convertToProdutoVO(ModelVenda modelVenda){
        return VendaVO.converteEntidade(modelVenda);
    }

}
