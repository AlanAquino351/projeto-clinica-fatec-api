package com.br.clinca.dtos;

import com.br.clinca.domain.Atendimento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AtendimentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "O campo Médico é requerido")
    private MedicoDTO medico;

    @NotNull(message = "O campo paciente é requerido")
    private PacienteDTO paciente;

    @NotEmpty(message = "O campo receituario é requerido")
    private String receituario;

    private List<ExameDTO> exames;
    private List<MedicamentoDTO> medicamentos;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataAtendimento;

    public AtendimentoDTO() {
    }

    public AtendimentoDTO(Integer id, MedicoDTO medico, PacienteDTO paciente, String receituario, List<ExameDTO> exames, List<MedicamentoDTO> medicamentos) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.receituario = receituario;
        this.exames = exames;
        this.medicamentos = medicamentos;
    }

    public AtendimentoDTO(Atendimento atendimento) {
        this.id = atendimento.getId();
        this.medico = new MedicoDTO(atendimento.getMedico());
        this.paciente = new PacienteDTO(atendimento.getPaciente());
        this.receituario = atendimento.getReceituario();
        this.exames = atendimento.getExames().stream()
                .map(exame -> new ExameDTO(exame))
                .collect(Collectors.toList());
        this.medicamentos = atendimento.getMedicamentos().stream()
                .map(medicamento -> new MedicamentoDTO(medicamento))
                .collect(Collectors.toList());
        this.dataAtendimento = atendimento.getDataAtendimento();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MedicoDTO getMedico() {
        return medico;
    }

    public void setMedico(MedicoDTO medico) {
        this.medico = medico;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

    public String getReceituario() {
        return receituario;
    }

    public void setReceituario(String receituario) {
        this.receituario = receituario;
    }

    public List<ExameDTO> getExames() {
        return exames;
    }

    public void setExames(List<ExameDTO> exames) {
        this.exames = exames;
    }

    public List<MedicamentoDTO> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<MedicamentoDTO> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
