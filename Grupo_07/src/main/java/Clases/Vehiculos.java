/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import TDAs.CircularDoublyLinkedList;
import com.mycompany.grupo_07.DoubleLinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Vehiculos {
    
    private String marca, modelo, traccion, transmision;
    private int precio, aniousado, kilometraje; //Poner mas de 20000 pero menos de 40000
    private String tipo; 
    private Combustible combus;
    private CircularDoublyLinkedList<String> imagenes;
    
    public Vehiculos(String marca, String modelo, int precio, String tipo, int aniousado, int kilometraje, Combustible combus, String traccion, String transmision, CircularDoublyLinkedList<String> imagenes) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.tipo = tipo;
        this.aniousado = aniousado;
        this.kilometraje = kilometraje;
        this.combus = combus;
        this.traccion = traccion;
        this.transmision = transmision;
        this.imagenes = imagenes;
    }
    
    public Vehiculos(String marca, String modelo, int precio, String tipo, int aniousado, int kilometraje, Combustible combus, String traccion, String transmision) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.tipo = tipo;
        this.aniousado = aniousado;
        this.kilometraje = kilometraje;
        this.combus = combus;
        this.traccion = traccion;
        this.transmision = transmision;
    }

    public CircularDoublyLinkedList<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(CircularDoublyLinkedList<String> imagenes) {
        this.imagenes = imagenes;
    }
 
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAniousado() {
        return aniousado;
    }

    public void setAniousado(int aniousado) {
        this.aniousado = aniousado;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Combustible getCombus() {
        return combus;
    }

    public void setCombus(Combustible combus) {
        this.combus = combus;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculos other = (Vehiculos) obj;
        if (this.precio != other.precio) {
            return false;
        }
        if (this.aniousado != other.aniousado) {
            return false;
        }
        if (this.kilometraje != other.kilometraje) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.traccion, other.traccion)) {
            return false;
        }
        if (!Objects.equals(this.transmision, other.transmision)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        return this.combus == other.combus;
    }
    
    @Override
    public String toString() {
        return "Vehiculos{" + "marca=" + marca + ", modelo=" + modelo + ", precio=" + precio + ", tipo=" + tipo + ", aniousado=" + aniousado + ", kilometraje=" + kilometraje + ", combustible=" + combus + ", traccion=" + traccion + ", transmision=" + transmision + '}';
    }
    
    public void agregarImagen(String url){
        imagenes.addLast(url);
    }
    
    public String obtenerImagenes(int index){
        return imagenes.get(index);
    }
    
    public static Comparator<Vehiculos> VehiculosPorKilometraje(){
        Comparator<Vehiculos> cmp=new Comparator<Vehiculos>(){
            @Override
            public int compare(Vehiculos v1, Vehiculos v2){
                return Integer.compare(v1.getKilometraje(),v2.getKilometraje());
            }
        };
        return cmp;
    }
    
    public static Comparator<Vehiculos> VehiculosPorPrecio(){
        Comparator<Vehiculos> cmp=new Comparator<Vehiculos>(){
            @Override
            public int compare(Vehiculos v1, Vehiculos v2){
                return Integer.compare(v1.getPrecio(),v2.getPrecio());
            }
        };
        return cmp;
    }
    
    public static Comparator<Vehiculos> VehiculosPorMarca(){
        Comparator<Vehiculos> cmp=new Comparator<Vehiculos>(){
            @Override
            public int compare(Vehiculos v1, Vehiculos v2){
                if (v1.getMarca().equals(v2.getMarca()))
                    return 0;
                else
                    return 1;
            }
        };
        return cmp;
    }
    
    public static Comparator<Vehiculos> VehiculosPorModelo(){
        Comparator<Vehiculos> cmp=new Comparator<Vehiculos>(){
            @Override
            public int compare(Vehiculos v1, Vehiculos v2){
                if (v1.getModelo().equals(v2.getModelo()))
                    return 0;
                else
                    return 1;
            }
        };
        return cmp;
    }
    
    public static DoubleLinkedList<Vehiculos> leerVehiculosArchivo(String nomfile){
        DoubleLinkedList<Vehiculos> lista = new DoubleLinkedList();
        try(BufferedReader bf = new BufferedReader(new FileReader(nomfile));)
        {
            String line;
            while((line=bf.readLine())!=null)
            {
               String[] tokens=line.split(",");
               Vehiculos v =new Vehiculos(tokens[0],tokens[1],Integer.parseInt(tokens[2]),(tokens[3]),Integer.parseInt(tokens[4]),Integer.parseInt(tokens[5]),Combustible.valueOf(tokens[6]),tokens[7],tokens[8]);
               lista.addFirst(v);
            }
            return lista;
        }
        catch(IOException io){
            System.out.println("no se puede abrir el canal");
        }
        return lista;
    }
 
    public void guardarArchivo(String nomfile){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(nomfile));){
            bw.write(marca+","+modelo+","+String.valueOf(precio)+","+tipo+","+String.valueOf(aniousado)+","+String.valueOf(kilometraje)+","+combus.name()+","+traccion+","+transmision+"\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void guardarArchivo(DoubleLinkedList<Vehiculos> vs,String nomfile){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(nomfile));){
            vs.forEach(vehi -> {try {
                bw.write(vehi.marca+","+vehi.modelo+","+String.valueOf(vehi.precio)+","+vehi.tipo+","+String.valueOf(vehi.aniousado)+","+String.valueOf(vehi.kilometraje)+","+vehi.combus.name()+","+vehi.traccion+","+vehi.transmision+"\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void borrarVehiculoArchivo(Vehiculos vehiculo,String nomfile){
        DoubleLinkedList<Vehiculos> vs=Vehiculos.leerVehiculosArchivo(nomfile);
        vs.remove(vehiculo);
        Vehiculos.guardarArchivo(vs, nomfile);
        
    }
    public static Comparator<Vehiculos> VehiculosPorTipo(){
        Comparator<Vehiculos> cmp=new Comparator<Vehiculos>(){
            @Override
            public int compare(Vehiculos v1, Vehiculos v2){
                if (v1.getTipo().equals(v2.getTipo()))
                    return 0;
                else
                    return 1;
            }
        };
        return cmp;

}
  

}