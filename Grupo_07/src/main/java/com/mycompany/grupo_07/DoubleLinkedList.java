/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo_07;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;


/**
 *
 * @author edu-g
 */
public class DoubleLinkedList<E> implements List<E>, Iterable<E>{

    private DoubleNodeList<E> last;
    private DoubleNodeList<E> first;
   
    DoubleNodeList<E> getLast(){
        return last;
    }
    
    DoubleNodeList<E> getFirst(){
        return first;
    }
    
    @Override
    public boolean addFirst(E element) {
        if(element != null){
            if(size() == 0) {
                first = new DoubleNodeList();
                first.setContent(element);
                last = first;
            }
            else{
                DoubleNodeList<E> node = new DoubleNodeList();
                node.setContent(element);
                node.setNext(first);
                first.setPrevious(node);
                first = node;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addLast(E element) {
        if(element != null){
            if(size() == 0) {
                first = new DoubleNodeList();
                first.setContent(element);
                last = first;
            }
            else{
                DoubleNodeList<E> node = new DoubleNodeList();
                node.setContent(element);
                node.setPrevious(last);
                last.setNext(node);
                last = node;
            }
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        int counter = (first != null)? 1: 0;
        if(counter == 0) return counter;
        
        DoubleNodeList<E> node = first;
        while(node != last){
            node = node.getNext();
            counter++;
        }
        return counter;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }
        
        DoubleNodeList<E> node = first;
        first = node.getNext();
        first.setPrevious(null);
        
        return node.getContent();
    }

    @Override
    public E removeLast() {
        if(isEmpty()) return null;
        
        DoubleNodeList<E> node = last;
        last = node.getPrevious();
        last.setNext(null);
        
        return node.getContent();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public E remove(int index) {
        if(isEmpty()) return null;
        if(index > size()-1 || index < -1) throw new IndexOutOfBoundsException("Índice no admitido.");
        if(index == 0) return removeFirst();
        if(index == -1 || index == size()-1) return removeLast();
        
        DoubleNodeList<E> node = first;
        for(int i = 0; i<index; i++){
            node = node.getNext();
        }
        
        node.getPrevious().setNext(node.getNext());
        node.getNext().setPrevious(node.getPrevious());
        
        return node.getContent();
    }
    
    @Override
    public boolean add(E element, int index) {
        if(element == null) return false;
        if(index > size()-1 || index < -1) throw new IndexOutOfBoundsException("Índice no admitido.");
        if(index == 0) return addFirst(element);
        if(index == -1 || index == size()-1) return addLast(element);
        
        DoubleNodeList<E> new_node = new DoubleNodeList();
        new_node.setContent(element);
        
        DoubleNodeList<E> node = first;
        for(int i = 0; i<index; i++){
            node = node.getNext();
        }
        
        new_node.setPrevious(node.getPrevious());
        node.getPrevious().setNext(new_node);
        new_node.setNext(node);
        node.setPrevious(new_node);
        
        return true;
    }

    public Iterator<E> iterator() {
       return new Iterator<E>() {
            DoubleNodeList<E> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (node == null) {
                    throw new NoSuchElementException();
                }
                E content = node.getContent();
                node = (node == last) ? null : node.getNext();
                return content;
            }
        };
    }
    
    

    @Override
    public E get(int index) {
        if(isEmpty()) return null;
        if(index > size()-1 || index < -1) throw new IndexOutOfBoundsException("Índice no admitido.");
        if(index == 0) return getFirst().getContent();
        if(index == -1) return getLast().getContent();
        
        DoubleNodeList<E> node = first;
        for(int i = 0; i<index; i++){
            node = node.getNext();
        }
        
        return node.getContent();
    }
    
    @Override
    public E set(int index, E e){
        if(isEmpty()) return null;
        if(index > size()-1 || index < -1) throw new IndexOutOfBoundsException("Índice no admitido.");
        if(index == -1) index = size()-1;
        
        DoubleNodeList<E> node = first;
        for(int i = 0; i<index; i++){
            node = node.getNext();
        }
        
        E replaced_element = node.getContent();
        node.setContent(e);
        
        return replaced_element;
    }
    
    @Override
    public void clear(){
        if(isEmpty()) return;
        
        first = last = null;
    }
    
    @Override
    public String toString(){
        if(isEmpty()) return "[Empty List]";
        DoubleNodeList<E> node = first;
        String s = "["+node.getContent().toString();
        
        while(node != last){
            node = node.getNext();
            s += ", "+node.getContent().toString();
        }
        return s+"]";
    }

    public int indexOf(E e) {
        if(e == null) return -2;
        if(isEmpty()) return 0;
        if(e == last.getContent()) return size()-1;
        
        DoubleNodeList<E> node = first;
        int index = 0;
        while(node != last){
            if(node.getContent() == e) return index;
            index++;
            node = node.getNext();
        }
        
        return -2;
    }
    
    public boolean contains(E e){
        if(isEmpty()) return false;
        DoubleNodeList<E> node = first;
        while(node!=null){
            if(e.equals(node.getContent())) return true;
            node = node.getNext();
        }
        return false;
    }

    public DoubleLinkedList<E> getPage(int page, int items){
        DoubleLinkedList<E> list = new DoubleLinkedList<>();
        
        DoubleNodeList<E> current = first;
        int start = page * items;
        int end = start + items;
        int index = 0;
        
        while(current != null){
            if(index >= start && index < end){
                list.addLast((E) current.getContent());
            }
            
            if(index >= end){
                break;
            }
            
            current = current.getNext();
            index++;
        }
        
        return list;
    }
    
    @Override
    public void forEach(Consumer<? super E> action){
        DoubleNodeList<E> current = first;
        
        while(current != null){
            action.accept(current.getContent());
            current = current.getNext();
        }
    }
    
    public DoubleLinkedList<E> findAll(DoubleLinkedList<E> elementos, Comparator cmp, E otroElemento){
        DoubleLinkedList resultados=new DoubleLinkedList();
        elementos.forEach(elemento -> {
            if(cmp.compare(elemento, otroElemento)==0)
            resultados.addFirst(elemento);}
        );
        return resultados;
    }
    
}
