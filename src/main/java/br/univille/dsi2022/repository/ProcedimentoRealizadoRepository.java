package br.univille.dsi2022.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.dsi2022.entity.ProcedimentoRealizado;

@Repository
public interface ProcedimentoRealizadoRepository 
    extends JpaRepository<ProcedimentoRealizado, Long> {
    
}
