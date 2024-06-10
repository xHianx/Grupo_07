/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TDAs;

/**
 *
 * @author edu-g
 */
public interface List <E>{
    public boolean addFirst(E e);
    
    public boolean addLast(E e);
    
    public E removeFirst();
    
    public E removeLast();
    
    public int size();
    
    public boolean isEmpty();
    
    public void clear();
    
    public boolean add(E element, int index);
    
    public E remove(int index);
    
    public E get(int index);
    
    public E set(int index, E element);
    
    @Override
    public String toString();
    
    
}
