package br.com.systemsgs.repository;

import br.com.systemsgs.model.ModelProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ModelProdutoVenda, Long> {

}
