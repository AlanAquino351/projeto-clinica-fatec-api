package com.br.clinca.repositories;

import com.br.clinca.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer>{

    @Query("SELECT obj FROM Medico obj WHERE obj.crm =:crm")
    Medico findByCrm(@Param("crm") String crm);
}