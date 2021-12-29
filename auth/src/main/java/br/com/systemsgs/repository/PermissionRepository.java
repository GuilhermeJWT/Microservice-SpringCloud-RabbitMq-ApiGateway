package br.com.systemsgs.repository;

import br.com.systemsgs.model.ModelPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<ModelPermission, Long> {

    @Query("SELECT p FROM ModelPermission p WHERE p.description =:description")
    ModelPermission findByDescription(@Param("description") String description);

}
