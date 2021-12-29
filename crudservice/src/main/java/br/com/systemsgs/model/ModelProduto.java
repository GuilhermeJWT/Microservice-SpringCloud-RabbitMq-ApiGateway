package br.com.systemsgs.model;

import br.com.systemsgs.vo.ProdutoVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity
@Table(name = "produto")
public class ModelProduto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "estoque", nullable = false)
    private Integer estoque;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    public static ModelProduto coverteEntidade(ProdutoVO produtoVO){
        return new ModelMapper().map(produtoVO, ModelProduto.class);
    }

}
