package br.univille.dsi2022.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.univille.dsi2022.dto.ProcedimentoRealizadoDTO;
import br.univille.dsi2022.entity.ProcedimentoRealizado;

@Mapper
public interface ProcedimentoRealizadoMapper {
    List<ProcedimentoRealizadoDTO> mapProcedimentoRealizado(List<ProcedimentoRealizado> ProcedimentoRealizado);
    List<ProcedimentoRealizado> mapProcedimentoRealizadoDTO(List<ProcedimentoRealizadoDTO> ProcedimentoRealizado);
    ProcedimentoRealizadoDTO mapProcedimentoRealizado(ProcedimentoRealizado ProcedimentoRealizado);
    ProcedimentoRealizado mapProcedimentoRealizadoDTO(ProcedimentoRealizadoDTO ProcedimentoRealizado);
}
