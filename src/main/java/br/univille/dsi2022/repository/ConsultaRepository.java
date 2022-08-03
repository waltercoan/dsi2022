package br.univille.dsi2022.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.dsi2022.entity.Consulta;

@Repository
public interface ConsultaRepository 
    extends JpaRepository<Consulta, Long>{
    
}
