package br.com.systemsgs.repository;

import br.com.systemsgs.model.ModelVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<ModelVenda, Long> {

}
