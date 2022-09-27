package br.univille.dsi2022.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.univille.dsi2022.dto.ConsultaDTO;
import br.univille.dsi2022.dto.PacienteDTO;
import br.univille.dsi2022.dto.ProcedimentoRealizadoDTO;
import br.univille.dsi2022.entity.ProcedimentoRealizado;
import br.univille.dsi2022.service.ConsultaService;
import br.univille.dsi2022.service.PacienteService;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    private ConsultaService service;
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView index(){
        List<ConsultaDTO> listaConsultas = service.getAll();
        return new 
            ModelAndView("consulta/index", 
            "listaConsultas",listaConsultas);
    }
    @GetMapping("/novo")
    public ModelAndView novo(){
        ConsultaDTO consulta = new ConsultaDTO();
        List<PacienteDTO> listaPacientes =  pacienteService.getAll();
        ProcedimentoRealizadoDTO procedimentoRealizado = new ProcedimentoRealizadoDTO();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("consulta",consulta);
        dados.put("listaPacientes",listaPacientes);
        dados.put("procedimentoRealizado",procedimentoRealizado);

        return new ModelAndView("consulta/form", dados);
    }
    @PostMapping(params="incitem")
    public ModelAndView incluirProc(@ModelAttribute("consulta") 
                                ConsultaDTO consulta,
                                @ModelAttribute("procedimentoRealizado")
                                ProcedimentoRealizadoDTO procedimentoRealizado){

        consulta.getListaProcedimentos().add(procedimentoRealizado);

        HashMap<String,Object> dados = new HashMap<>();
        List<PacienteDTO> listaPacientes =  pacienteService.getAll();
        dados.put("listaPacientes",listaPacientes);
        dados.put("procedimentoRealizado",new ProcedimentoRealizado());
        return new ModelAndView("consulta/form", dados);
    }

    @PostMapping(params="removeitem")
    public ModelAndView removerPlano(@ModelAttribute("consulta") 
                                ConsultaDTO consulta,
                                @RequestParam(name = "removeitem") int index){
        consulta.getListaProcedimentos().remove(index);

        HashMap<String,Object> dados = new HashMap<>();
        List<PacienteDTO> listaPacientes =  pacienteService.getAll();
        dados.put("listaPacientes",listaPacientes);
        dados.put("procedimentoRealizado",new ProcedimentoRealizado());
        return new ModelAndView("consulta/form", dados);
    }

    @PostMapping(params="save")
    public ModelAndView save(@ModelAttribute("consulta") 
                            ConsultaDTO consulta){
        consulta.setPaciente(pacienteService.findById(consulta.getPacienteId()));
        service.save(consulta);
        return new ModelAndView("redirect:/consulta");
    }

    @GetMapping(path = "/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") long id){
        ConsultaDTO consulta = service.findById(id);
        List<PacienteDTO> listaPacientes =  pacienteService.getAll();
        ProcedimentoRealizadoDTO procedimentoRealizado = new ProcedimentoRealizadoDTO();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("consulta",consulta);
        dados.put("listaPacientes",listaPacientes);
        dados.put("procedimentoRealizado",procedimentoRealizado);

        return new ModelAndView("consulta/form", dados);
    }
}
