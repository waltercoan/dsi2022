package br.univille.dsi2022.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PacienteDTO {
    private long id;
    private String nome;
    private String sexo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;
    private String foto;
    
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    
}
