package br.univille.dsi2022.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.univille.dsi2022.dto.PacienteDTO;
import br.univille.dsi2022.entity.Paciente;

@Mapper
public interface PacienteMapper {
    //Entra ENTIDADE sai DTO
    List<PacienteDTO> mapPaciente(List<Paciente> paciente);
    //Entra DTO sai ENTIDADE
    List<Paciente> mapPacienteDTO(List<PacienteDTO> paciente);
    PacienteDTO mapPaciente(Paciente paciente);
    Paciente mapPacienteDTO(PacienteDTO paciente);

}
