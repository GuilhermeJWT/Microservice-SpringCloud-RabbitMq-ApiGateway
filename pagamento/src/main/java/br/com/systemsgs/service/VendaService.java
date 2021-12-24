package br.com.systemsgs.service;

import br.com.systemsgs.exception.ResourceNotFoundException;
import br.com.systemsgs.model.ModelVenda;
import br.com.systemsgs.repository.VendaRepository;
import br.com.systemsgs.vo.VendaVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Transactional
    public VendaVO create(VendaVO vendaVO){
        VendaVO vendaRetorno = VendaVO.converteEntidade(vendaRepository.save(ModelVenda.converteEntidade(vendaVO)));
        return vendaRetorno;
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
