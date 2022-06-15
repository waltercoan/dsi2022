package br.univille.dsi2022.service.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.dsi2022.dto.PacienteDTO;
import br.univille.dsi2022.entity.Paciente;
import br.univille.dsi2022.mapper.PacienteMapper;
import br.univille.dsi2022.repository.PacienteRepository;
import br.univille.dsi2022.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService{

    @Autowired
    private PacienteRepository repository;

    private PacienteMapper mapper 
        = Mappers.getMapper(PacienteMapper.class);

    @Override
    public List<PacienteDTO> getAll() {

        return mapper.mapPaciente(repository.findAll());
    }

    @Override
    public PacienteDTO save(PacienteDTO paciente) {
        Paciente pacienteEntity = mapper.mapPacienteDTO(paciente);
        repository.save(pacienteEntity);
        return mapper.mapPaciente(pacienteEntity);
    }

    @Override
    public PacienteDTO findById(long id) {
        Optional<Paciente> paciente = repository.findById(id);
        if(paciente.isPresent()){
            Paciente pacienteEntity = paciente.get();
            return mapper.mapPaciente(pacienteEntity);

        }
        return new PacienteDTO();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
    
}
