package br.univille.dsi2022.service;

import java.util.List;

import br.univille.dsi2022.dto.ProcedimentoRealizadoDTO;

public interface ProcedimentoRealizadoService {
    public List<ProcedimentoRealizadoDTO> getAll();
    public ProcedimentoRealizadoDTO save(ProcedimentoRealizadoDTO ProcedimentoRealizado);
    public ProcedimentoRealizadoDTO findById(long id);
    public void delete(long id);
}
