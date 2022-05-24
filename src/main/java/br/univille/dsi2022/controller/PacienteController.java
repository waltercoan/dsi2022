package br.univille.dsi2022.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("paciente/index");
    }
}
