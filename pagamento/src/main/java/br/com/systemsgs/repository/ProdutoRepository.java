package br.com.systemsgs.repository;

import br.com.systemsgs.model.ModelProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ModelProduto, Long> {

}
