package br.com.systemsgs.repository;

import br.com.systemsgs.model.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ModelUser, Long> {

    @Query("SELECT u FROM ModelUser u WHERE u.userName =:userName")
    ModelUser findByUserName(@Param("userName") String userName);

}
