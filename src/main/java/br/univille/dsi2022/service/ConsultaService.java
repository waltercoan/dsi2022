package br.univille.dsi2022.service;

import java.util.List;

import br.univille.dsi2022.dto.ConsultaDTO;

public interface ConsultaService {
    public List<ConsultaDTO> getAll();
    public ConsultaDTO save(ConsultaDTO Consulta);
    public ConsultaDTO findById(long id);
    public void delete(long id);
}
