package com.example.rtietjen2.trabalhoapp.Entity;

import java.time.LocalTime;
import java.util.List;

public class Agendamento {

    private LocalTime horaInicio;

    private LocalTime horaFim;

    private Cliente cliente;

    private Profissional profissional;

    private Procedimento procedimentos;

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Procedimento getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(Procedimento procedimentos) {
        this.procedimentos = procedimentos;
    }
}
