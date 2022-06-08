package br.univille.dsi2022.service;

import java.util.List;

import br.univille.dsi2022.dto.PacienteDTO;

public interface PacienteService {
    public List<PacienteDTO> getAll();
    public PacienteDTO save(PacienteDTO paciente);
}
