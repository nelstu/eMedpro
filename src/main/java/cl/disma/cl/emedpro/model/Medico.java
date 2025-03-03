/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.disma.cl.emedpro.model;

/**
 *
 * @author devmarin
 */
public class Medico {

 
    private int id;
    private String rut;
    private String nombres;
    private String apellido_paterno;
    private String apellido_materno;
    
        public Medico(int id, String rut, String nombres, String apellido_paterno, String apellido_materno) {
        this.id = id;
        this.rut = rut;
        this.nombres = nombres;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
    }
    
        
           public Medico(int id, String rut, String nombres) {
        this.id = id;
        this.rut = rut;
        this.nombres = nombres;
    }

        
            public int getId() {
        return id;
    }

    public String getRut() {
        return rut;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

}
