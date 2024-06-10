/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDAs;

/**
 *
 * @author edu-g
 */
public class DoubleNodeList<E> {
    
    private E content;
    private DoubleNodeList<E> next;
    private DoubleNodeList<E> previous;
    
    public DoubleNodeList(){
        next = null;
        previous = null;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public DoubleNodeList<E> getNext() {
        return next;
    }

    public void setNext(DoubleNodeList<E> next) {
        this.next = next;
    }
    
    public DoubleNodeList<E> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNodeList<E> previous) {
        this.previous = previous;
    }
    
}
