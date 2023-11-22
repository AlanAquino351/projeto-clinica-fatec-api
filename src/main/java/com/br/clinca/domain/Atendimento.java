package com.br.clinca.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity
public class Atendimento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Paciente paciente;

    private String receituario;

    @ManyToMany
    private List<Exame> exames;

    @ManyToMany
    private List<Medicamento> medicamentos;

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column
    private LocalDateTime dataAtendimento;

    public Atendimento() {
    }

    public Atendimento(Integer id, Medico medico, Paciente paciente, String receituario, List<Exame> exames, List<Medicamento> medicamentos) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.receituario = receituario;
        this.exames = exames;
        this.medicamentos = medicamentos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getReceituario() {
        return receituario;
    }

    public void setReceituario(String receituario) {
        this.receituario = receituario;
    }

    public List<Exame> getExames() {
        return exames;
    }

    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    @PrePersist
    public void prePersist() {
        this.dataAtendimento = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
}