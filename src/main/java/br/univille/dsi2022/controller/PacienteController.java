package br.univille.dsi2022.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.property.access.spi.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;

import br.univille.dsi2022.dto.PacienteDTO;
import br.univille.dsi2022.service.PacienteService;
import br.univille.dsi2022.service.SalvarArquivosService;

@Controller
@RequestMapping("/paciente")
public class PacienteController {
    
    @Autowired
    private PacienteService service;
    @Autowired
    private SalvarArquivosService salvarArquivoService;

    @GetMapping
    public ModelAndView index(){
        List<PacienteDTO> listaPacientes = service.getAll();
        return new 
            ModelAndView("paciente/index2", 
            //ModelAndView("paciente/index", 
            "listaPacientes",listaPacientes);
    }

    @GetMapping("/novo")
    public ModelAndView novo(){
        PacienteDTO paciente = new PacienteDTO();
        return new ModelAndView("paciente/form","paciente",paciente);
    }

    @PostMapping(params="form")
    public ModelAndView save(PacienteDTO paciente, @RequestParam("file") MultipartFile file){

        if(file.getSize() != 0){
            String caminho = salvarArquivoService.save(file);
            paciente.setFoto(caminho);
        }

        service.save(paciente);
        return new ModelAndView("redirect:/paciente");
    }
    @GetMapping(value = "/image/{id}")
    public @ResponseBody byte[] getImage(@PathVariable("id") long id){
        try{
            PacienteDTO paciente = service.findById(id);
            File file = new File(paciente.getFoto());
            byte[] bytes = new byte[(int) file.length()];
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            dis.readFully(bytes);
            return bytes;
        }catch (Exception e){
            return new byte[0];
        }
    }
    @RequestMapping(
        value = "/busca-nome", 
        method = RequestMethod.GET, 
        produces="application/json"
    ) 
    @ResponseBody
    public String buscarPorNome(@RequestParam(name="q") String nome){
        Gson gson = new Gson();
        var listaPacientes = service.buscaPorNome(nome);

        List<ItemBusca> listaNomes = new ArrayList<ItemBusca>();
        for(var umPaciente : listaPacientes){
            listaNomes.add(new ItemBusca(umPaciente.getId(),umPaciente.getNome()));
        }
        
        String json = gson.toJson(listaNomes);
        return json;
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

    class ItemBusca{
        private long value;
        private String text;
        public ItemBusca() {
            super();
        }
        public ItemBusca(long value, String text) {
            setValue(value);
            setText(text);
        }
        public long getValue() {
            return value;
        }
        public void setValue(long value) {
            this.value = value;
        }
        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }
        
    }
}
