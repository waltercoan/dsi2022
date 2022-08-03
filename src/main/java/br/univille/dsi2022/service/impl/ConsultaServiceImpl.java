package br.univille.dsi2022.service.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.dsi2022.dto.ConsultaDTO;
import br.univille.dsi2022.entity.Consulta;
import br.univille.dsi2022.mapper.ConsultaMapper;
import br.univille.dsi2022.repository.ConsultaRepository;
import br.univille.dsi2022.service.ConsultaService;
@Service
public class ConsultaServiceImpl
    implements ConsultaService {
        
    @Autowired
    private ConsultaRepository repository;

    private ConsultaMapper mapper 
        = Mappers.getMapper(ConsultaMapper.class);
    
    @Override
    public List<ConsultaDTO> getAll() {
        return mapper.mapConsulta(repository.findAll());
    }

    @Override
    public ConsultaDTO save(ConsultaDTO consulta) {
        Consulta consultaEntity = mapper.mapConsultaDTO(consulta);
        repository.save(consultaEntity);
        return mapper.mapConsulta(consultaEntity);
    }

    @Override
    public ConsultaDTO findById(long id) {
        Optional<Consulta> consulta = repository.findById(id);
        if(consulta.isPresent()){
            Consulta consultaEntity = consulta.get();
            return mapper.mapConsulta(consultaEntity);

        }
        return new ConsultaDTO();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
    
}
