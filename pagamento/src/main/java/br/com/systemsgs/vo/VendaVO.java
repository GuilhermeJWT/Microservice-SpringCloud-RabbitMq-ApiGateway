package br.com.systemsgs.vo;

import br.com.systemsgs.model.ModelProdutoVenda;
import br.com.systemsgs.model.ModelVenda;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({"id", "data", "produtos", "valorTotal"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class VendaVO extends RepresentationModel<VendaVO> implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("data")
    private Date data;

    @JsonProperty("produtos")
    private List<ModelProdutoVenda> produtos;

    @JsonProperty("valorTotal")
    private BigDecimal valorTotal;

    public static VendaVO converteEntidade(ModelVenda modelVenda){
        return new ModelMapper().map(modelVenda, VendaVO.class);
    }

}
