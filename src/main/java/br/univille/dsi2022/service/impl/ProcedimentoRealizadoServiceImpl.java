package br.univille.dsi2022.service.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.dsi2022.dto.ProcedimentoRealizadoDTO;
import br.univille.dsi2022.entity.ProcedimentoRealizado;
import br.univille.dsi2022.mapper.ProcedimentoRealizadoMapper;
import br.univille.dsi2022.repository.ProcedimentoRealizadoRepository;
import br.univille.dsi2022.service.ProcedimentoRealizadoService;
@Service
public class ProcedimentoRealizadoServiceImpl 
    implements ProcedimentoRealizadoService{
    
    @Autowired
    private ProcedimentoRealizadoRepository repository;

    private ProcedimentoRealizadoMapper mapper 
        = Mappers.getMapper(ProcedimentoRealizadoMapper.class);

    @Override
    public List<ProcedimentoRealizadoDTO> getAll() {
        return mapper.mapProcedimentoRealizado(repository.findAll());
    }

    @Override
    public ProcedimentoRealizadoDTO save(ProcedimentoRealizadoDTO ProcedimentoRealizado) {
        ProcedimentoRealizado procedimentoRealizadoEntity = mapper.mapProcedimentoRealizadoDTO(ProcedimentoRealizado);
        repository.save(procedimentoRealizadoEntity);
        return mapper.mapProcedimentoRealizado(procedimentoRealizadoEntity);
    }

    @Override
    public ProcedimentoRealizadoDTO findById(long id) {
        Optional<ProcedimentoRealizado> procedimentoRealizado = repository.findById(id);
        if(procedimentoRealizado.isPresent()){
            ProcedimentoRealizado procedimentoRealizadoEntity = procedimentoRealizado.get();
            return mapper.mapProcedimentoRealizado(procedimentoRealizadoEntity);

        }
        return new ProcedimentoRealizadoDTO();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
    
}
