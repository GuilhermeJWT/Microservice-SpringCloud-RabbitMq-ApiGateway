package br.com.systemsgs.config;

import br.com.systemsgs.model.ModelProduto;
import br.com.systemsgs.repository.ProdutoRepository;
import br.com.systemsgs.vo.ProdutoVO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoReceiveMessage {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoReceiveMessage(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /* Método que vai ficar escutando a fila - Consumer */
    @RabbitListener(queues = { "${crud.rabbitmq.queue}" })
    public void receive(@Payload ProdutoVO produtoVO){
        produtoRepository.save(ModelProduto.coverteEntidade(produtoVO));
    }

}
