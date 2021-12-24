package br.com.systemsgs.serivice;

import br.com.systemsgs.repository.ProdutoRepository;
import br.com.systemsgs.vo.ProdutoVO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public ProdutoVO create(ProdutoVO produtoVO){
        return null;
    }

}
