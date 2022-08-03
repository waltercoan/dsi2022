package br.univille.dsi2022.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.dsi2022.dto.PacienteDTO;
import br.univille.dsi2022.service.PacienteService;

@Controller
@RequestMapping("/paciente")
public class PacienteController {
    
    @Autowired
    private PacienteService service;

    @GetMapping
    public ModelAndView index(){
        List<PacienteDTO> listaPacientes = service.getAll();
        return new 
            //ModelAndView("paciente/index2", 
            ModelAndView("paciente/index", 
            "listaPacientes",listaPacientes);
    }

    @GetMapping("/novo")
    public ModelAndView novo(){
        PacienteDTO paciente = new PacienteDTO();
        return new ModelAndView("paciente/form","paciente",paciente);
    }

    @PostMapping(params="form")
    public ModelAndView save(PacienteDTO paciente){
        service.save(paciente);
        return new ModelAndView("redirect:/paciente");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable long id){
        PacienteDTO paciente = service.findById(id);

        return new ModelAndView("paciente/form",
            "paciente",paciente);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable long id){
        service.delete(id);
        return new ModelAndView("redirect:/paciente");
    }

}
