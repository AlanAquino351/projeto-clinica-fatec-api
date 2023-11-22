package com.br.clinca.repositories;

import com.br.clinca.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{

    @Query("SELECT obj FROM Paciente obj WHERE obj.cpf =:cpf")
    Paciente findByCPF(@Param("cpf") String cpf);

    @Query("SELECT obj FROM Paciente obj WHERE obj.cns =:cns")
    Paciente findByCNS(@Param("cns") String cns);
}