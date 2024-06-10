/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import TDAs.CircularDoublyLinkedList;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author edu-g
 */
public class Vehiculos {
    
    private String marca, modelo, traccion, transmision;
    private int precio, aniousado, kilometraje; //Poner mas de 20000 pero menos de 40000
    private tipoVehiculo tipo; 
    private Combustible combus;
    private CircularDoublyLinkedList<String> imagenes;
    
    public Vehiculos(String marca, String modelo, int precio, tipoVehiculo tipo, int aniousado, int kilometraje, Combustible combus, String traccion, String transmision, CircularDoublyLinkedList<String> imagenes) {
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
    
    public Vehiculos(String marca, String modelo, int precio, tipoVehiculo tipo, int aniousado, int kilometraje, Combustible combus, String traccion, String transmision) {
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

    public tipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(tipoVehiculo tipo) {
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

}
