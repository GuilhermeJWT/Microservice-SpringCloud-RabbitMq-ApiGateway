package br.com.systemsgs.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
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

}
