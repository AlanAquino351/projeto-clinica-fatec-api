package com.br.clinca.repositories;

import com.br.clinca.domain.Exame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Integer>{
    @Query("SELECT e FROM Exame e ORDER BY e.nome")
    List<Exame> findAllOrderedByName();
}