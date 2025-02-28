/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.disma.cl.emedpro.model;


public class Usuario {
    private int id;
    private String email;
    private String pass;

    public Usuario(int id, String email, String pass) {
        this.id = id;
        this.email = email;
        this.pass = pass;
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getPass() { return pass; }
}
