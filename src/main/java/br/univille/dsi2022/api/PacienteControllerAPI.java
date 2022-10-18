package br.univille.dsi2022.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.univille.dsi2022.dto.PacienteDTO;
import br.univille.dsi2022.service.PacienteService;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteControllerAPI {
    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAll(){
        List<PacienteDTO> listaPacientes = service.getAll();
        return new ResponseEntity<>(listaPacientes,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> 
            save(@RequestBody PacienteDTO paciente){
        if(paciente.getId() == 0){
            paciente = service.save(paciente);
            return new 
                ResponseEntity<PacienteDTO>(paciente,HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> 
        getById(@PathVariable("id") long id){
        var umPaciente = service.findById(id);
        if(umPaciente.getId() == 0){
            return ResponseEntity.notFound().build();
        }
        return 
            new ResponseEntity<PacienteDTO>(umPaciente,
                HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> 
            update(@PathVariable("id") long id,
            @RequestBody PacienteDTO paciente){
        var pacienteAntigo 
                = service.findById(id);
        if(pacienteAntigo.getId() == 0){
            return ResponseEntity.notFound().build();
        }
        pacienteAntigo.setNome(paciente.getNome());
        pacienteAntigo.setSexo(paciente.getSexo());
        pacienteAntigo.setDataNascimento(paciente.getDataNascimento());
        
        pacienteAntigo = service.save(pacienteAntigo);
        return new 
            ResponseEntity<PacienteDTO>(pacienteAntigo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteDTO> 
        delete(@PathVariable("id") long id){
        var pacienteAntigo = service.findById(id);
        if(pacienteAntigo.getId() == 0){
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return new 
            ResponseEntity<PacienteDTO>(pacienteAntigo, HttpStatus.OK);
    }

}
