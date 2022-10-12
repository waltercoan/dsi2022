package br.univille.dsi2022.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
