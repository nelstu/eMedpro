/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.disma.cl.emedpro.model;

/**
 *
 * @author nelsonstuardo
 */
public class Reserva {


    private int id;
    private String title;
    private String start;
    private String end;

    // Constructor


    public Reserva(int id, String title, String start, String end) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public Reserva() {
   
    }

    // Getters
    public String getTitle() { return title; }
    public String getStart() { return start; }
    public String getEnd() { return end; }

    public int getId() {
        return id;
    }


        public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    
    
    
}
