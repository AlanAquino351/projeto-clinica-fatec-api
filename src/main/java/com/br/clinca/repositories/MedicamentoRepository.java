package com.br.clinca.repositories;

import com.br.clinca.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer>{
    @Query("SELECT m FROM Medicamento m ORDER BY m.nome")
    List<Medicamento> findAllOrderedByName();
}