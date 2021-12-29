package br.com.systemsgs.model;

import br.com.systemsgs.vo.ProdutoVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

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
    private Long id;

    @Column(name = "estoque", nullable = false)
    private Integer estoque;

    public static ModelProduto coverteEntidade(ProdutoVO produtoVO){
        return new ModelMapper().map(produtoVO, ModelProduto.class);
    }

}
