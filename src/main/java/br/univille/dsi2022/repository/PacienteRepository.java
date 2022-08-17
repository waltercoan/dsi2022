package br.univille.dsi2022.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.univille.dsi2022.entity.Paciente;

@Repository
public interface PacienteRepository 
            extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByNome(String nome);
    List<Paciente> findByNomeIgnoreCaseContaining(@Param("nome") String nome);
}
