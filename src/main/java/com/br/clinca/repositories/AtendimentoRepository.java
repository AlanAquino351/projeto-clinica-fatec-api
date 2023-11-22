package com.br.clinca.repositories;

import com.br.clinca.domain.Atendimento;
import com.br.clinca.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer>{
    @Query("SELECT a FROM Atendimento a WHERE a.medico.crm = :crm ORDER BY a.dataAtendimento DESC ")
    List<Atendimento> findAllByMedico(String crm);
}