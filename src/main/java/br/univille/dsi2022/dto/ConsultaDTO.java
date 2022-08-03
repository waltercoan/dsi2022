package br.univille.dsi2022.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class ConsultaDTO {
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;
    private String status;
    private PacienteDTO paciente;
    private List<ProcedimentoRealizadoDTO> listaProcedimentos = new ArrayList<>();
    private long pacienteId;
    
    public long getPacienteId() {
        return pacienteId;
    }
    public void setPacienteId(long pacienteId) {
        this.pacienteId = pacienteId;
    }
    public List<ProcedimentoRealizadoDTO> getListaProcedimentos() {
        return listaProcedimentos;
    }
    public void setListaProcedimentos(List<ProcedimentoRealizadoDTO> listaProcedimentos) {
        this.listaProcedimentos = listaProcedimentos;
    }
    public PacienteDTO getPaciente() {
        return paciente;
    }
    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
