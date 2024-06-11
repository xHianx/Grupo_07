/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import TDAs.ArrayListGrupo7;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Usuario {
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasenia;
    private String celular;

    public Usuario(String nombre, String apellido, String correo, String contrasenia, String celular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.celular = celular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getContrasenia(){
        return contrasenia;
    }
    
    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }
    
    public String getCelular(){
        return celular;
    }
    
    public void setCelular(String celular){
        this.celular = celular;
    }
    
    public static ArrayListGrupo7<Usuario> leerArchivo(String nomfile){
        ArrayListGrupo7<Usuario> lista = new ArrayListGrupo7<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(nomfile));)
        {
            String line;
            while((line=bf.readLine())!=null)
            {
               String[] tokens=line.split(",");
               Usuario u=new Usuario(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4]);
               lista.add(u);
            }
            return lista;
        }
        catch(IOException io){
            System.out.println("no se puede abrir el canal");
        }
        return lista;
    }
    
}
