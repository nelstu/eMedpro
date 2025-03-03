/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.disma.cl.emedpro.model;

import java.sql.Date;
import java.sql.Time;

public class BloqueMedico {
    private int id;
    private int idmedico;
    private Date desde;
    private Date hasta;
    private Time horaInicial;
    private Time horaFinal;
    private int intervalo;
    
    
    public BloqueMedico(int id, int idmedico, Date desde, Date hasta, Time horaInicial, Time horaFinal, int intervalo) {
        this.id = id;
        this.idmedico = idmedico;
        this.desde = desde;
        this.hasta = hasta;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.intervalo = intervalo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdmedico() {
        return idmedico;
    }

    public void setIdmedico(int idmedico) {
        this.idmedico = idmedico;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Time getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Time horaInicial) {
        this.horaInicial = horaInicial;
    }

    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }


  
}

