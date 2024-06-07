/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo_07;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author rlaur
 */
public class ArrayListGrupo7<E> implements List<E> {
    
    private int capacidad=100;
    private E[] elementos=null;
    private int n=0;
    
    public ArrayListGrupo7(){
        elementos=(E[]) new Object[capacidad];
        n=0;
    }

    @Override
    public int size() {
       return n;
    }

    @Override
    public boolean isEmpty() {
        return n==0;
    }
    
    public boolean isFull(){
        return n==capacidad;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(E e) {
        if (e == null)
            return false;
        else if (this.isEmpty()){
            elementos[n++]=e;
            return true;
        }
        else if (this.isFull())
            this.addCapacity();
        elementos[n++]=e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        n=0;
    }

    @Override
    public E get(int index) {
        if (!this.isEmpty() && index<n)
            return elementos[index];
        return null;
            
    }

    @Override
    public E set(int index, E element) {
       if (index<0 || index>n)
           throw new IndexOutOfBoundsException();
       E eviejo=elementos[index];
       elementos[index]=element;
       return eviejo;
    }
    
    private void addCapacity(){
        E[] nuevo=(E[])new Object[capacidad*2];
        for (int i=0; i<n; i++){
           nuevo[i]=elementos[i]; 
        }
        elementos=nuevo;
        capacidad*=2;
    }

    @Override
    public void add(int index, E element) {
        if (element==null)
            throw new NullPointerException();
        else if(index<0 || index>this.n)
            throw new IndexOutOfBoundsException();
        else if(this.isEmpty())
            elementos[n++]=element;
        else if (this.isFull())
            this.addCapacity();
        for (int i=n; i>=index; i--){
            elementos[i+1]=elementos[i];
        }
        elementos[index]=element;
        n++;
    }

    @Override
    public E remove(int index) {
        E eremove=null;
        if (this.isEmpty() || index>n || index<0)
            throw new IndexOutOfBoundsException();
        eremove=elementos[index];
        for (int i=index; i<n-1; i++)
            elementos[i]=elementos[i+1];
        n--;
        return eremove;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
