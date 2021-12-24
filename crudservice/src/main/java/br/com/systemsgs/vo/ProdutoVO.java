package br.com.systemsgs.vo;

import br.com.systemsgs.model.ModelProduto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@JsonPropertyOrder({"id", "nome", "estoque", "preco"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
public class ProdutoVO extends RepresentationModel<ProdutoVO> implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("estoque")
    private Integer estoque;

    @JsonProperty("preco")
    private BigDecimal preco;

    public static ProdutoVO converteEntidade(ModelProduto modelProduto){
        return new ModelMapper().map(modelProduto, ProdutoVO.class);
    }

}
