package br.com.systemsgs.model;

import br.com.systemsgs.vo.ProdutoVendaVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "produto_venda")
public class ModelProdutoVenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_produto", nullable = false, length = 10)
    private Long idProduto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venda")
    private ModelVenda venda;

    public static ModelProdutoVenda converteEntidade(ProdutoVendaVO produtoVendaVO){
        return new ModelMapper().map(produtoVendaVO, ModelProdutoVenda.class);
    }

}
