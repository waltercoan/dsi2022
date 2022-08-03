package br.univille.dsi2022.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.univille.dsi2022.dto.ConsultaDTO;
import br.univille.dsi2022.entity.Consulta;

@Mapper
public interface ConsultaMapper {
    List<ConsultaDTO> mapConsulta(List<Consulta> Consulta);
    List<Consulta> mapConsultaDTO(List<ConsultaDTO> Consulta);
    ConsultaDTO mapConsulta(Consulta Consulta);
    Consulta mapConsultaDTO(ConsultaDTO Consulta);
}
