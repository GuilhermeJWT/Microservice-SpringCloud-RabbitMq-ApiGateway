package br.com.systemsgs.vo;

import br.com.systemsgs.model.ModelProduto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@JsonPropertyOrder({"id", "nome", "estoque", "preco"})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProdutoVO implements Serializable {

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
