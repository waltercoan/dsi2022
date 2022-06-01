package br.univille.dsi2022.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.univille.dsi2022.dto.PacienteDTO;
import br.univille.dsi2022.entity.Paciente;

@Mapper
public interface PacienteMapper {
    List<PacienteDTO> mapPaciente(List<Paciente> paciente);
    List<Paciente> mapPacienteDTO(List<PacienteDTO> paciente);
}
