package br.univille.dsi2022;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.univille.dsi2022.entity.Paciente;
import br.univille.dsi2022.repository.PacienteRepository;
import br.univille.dsi2022.repository.ProcedimentoRealizadoRepository;

@Component
public class Startup {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ProcedimentoRealizadoRepository procRepository;

    @EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
        if(!pacienteRepository.findByNome("Zezinho").isPresent()){
            Paciente pac1 = new Paciente();
            pac1.setId(1);
            pac1.setNome("Zezinho");
            pac1.setSexo("Masculino");
            pacienteRepository.save(pac1);
        }
        if(!pacienteRepository.findByNome("Mariazinha").isPresent()){
            Paciente pac2 = new Paciente();
            pac2.setId(2);
            pac2.setNome("Mariazinha");
            pac2.setSexo("Feminino");
            pacienteRepository.save(pac2);
        }
        
    }
}
