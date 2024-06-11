package TDAs;

import com.mycompany.borrador.NodeDoubly;
import java.util.Iterator;
import java.util.ListIterator;



public class CircularDoublyLinkedList<E>{
    
    NodeDoubly<E> last;
    
    public CircularDoublyLinkedList() {
        
    this.last = new NodeDoubly<>(null);
    this.last.setNext(this.last);
    this.last.setPrevious(this.last);
      
   }

    public CircularDoublyLinkedList(E content) {
        
        this.last = new NodeDoubly<>(content);
        this.last.setNext(this.last);
        this.last.setPrevious(this.last);
        
    }
    
   
    public int getIndex(E e) {
        if(e == null) return -2;
        if(isEmpty()) return 0;
        if(e == last.getContent()) return size()-1;
        
        NodeDoubly<E> node = last.getNext();
        int index = 0;
        while(node != last){
            if(node.getContent() == e) return index;
            index++;
            node = node.getNext();
        }
        
        return -2;
    }
    
    public boolean addLast(E e){
        if (e==null) {
            return false;
        }
  
       NodeDoubly<E> n = new NodeDoubly<>(e);
       n.setPrevious(this.last);
       n.setNext(this.last.getNext());
       
       if(last.getContent()==null){
           last=n;
       }
       
       this.last.setNext(n);
       this.last = n;
       this.last.getNext().setPrevious(n);      
    
       return true;
    }
    
    
    public int size() {
        int counter = (last != null)? 1: 0;
        if(counter == 0) return counter;
        
        NodeDoubly<E> node = last.getNext();
        while(node != last){
            node = node.getNext();
            counter++;
        }
        return counter;
    }
    
   
    @Override
    public String toString(){
        
        String s = "";
        
        for (NodeDoubly<E> v = this.last.getNext(); v!= this.last; v=v.getNext()){
            s += v.getContent().toString() + " ";
        }
        s += last.getContent();
        return s;
    }
    
 
    public E remove(int index){
        
        if (index<0 || index>this.size()-1) return null;
        if (index==this.size()-1) {//removeLast
            E e = this.last.getContent();
            this.last.getPrevious().setNext(this.last.getNext());
            this.last.getNext().setPrevious(this.last.getPrevious());
            this.last = this.last.getPrevious();
            return e;
        }
        if(index==0){//removeFirst
            E e = this.last.getNext().getContent();
            this.last.getNext().getNext().setPrevious(this.last);
            this.last.setNext(this.last.getNext().getNext());
            return e;
        }
        int i = 0;
        E e = null;
        for(NodeDoubly<E> v= this.last.getNext(); i <= index; v=v.getNext()){
            if (i==index){
                e = v.getContent();
                v.getPrevious().setNext(v.getNext());
                v.getNext().setPrevious(v.getPrevious());
            }
            i++;
        }
        return e;
    }

    public boolean addFirst(E e) {
        if (e!= null){
            NodeDoubly<E> newNode= new NodeDoubly<>(e);
            
            newNode.setNext(last);
            newNode.setPrevious(last.getPrevious());
            last.getPrevious().setNext(newNode);
            last.setPrevious(newNode);
        }
        return false;
    }

    public E removeFirst() {
        E e=last.getContent();
        last.getNext().setPrevious(last.getPrevious());
        last.getPrevious().setNext(last.getNext());
        last=last.getNext();
        return e;
    }

    public E removeLast() {
        if(isEmpty()) return null;
        
        NodeDoubly<E> node = last;
        last.getPrevious().setNext(last.getNext());
        last.getNext().setPrevious(last.getPrevious());
        
        last = last.getPrevious();
        return node.getContent();
    }

    public boolean isEmpty() {
        return last.getContent()==null;
    }

    public void clear() {
        if(isEmpty()) return;
        
        last = null;
    }

    public boolean add(int index, E element) throws Exception {
    
       int size = size();
        if (element != null && index >= 0 && index <= size) {
            if (index == 0) {
                addFirst(element);
                return true;
            } else if (index == size) {
                addLast(element);
                return true;
            } else {
                NodeDoubly<E> n = last;
                for (int i = 0; i < index - 1; i++) {
                    n = n.getNext();
                }
                NodeDoubly<E> newNode = new NodeDoubly<>(element);
                newNode.setNext(n.getNext());
                n.setNext(newNode);
                return true;
            }
        }
        return false;
    
    }

    public E set(int index, E element) throws Exception {
        int size = size();
        if (index >= 0 && index < size) {
            NodeDoubly<E> n = last;
            for (int i = 0; i < index; i++) {
                n = n.getNext();
            }
            E old = n.getContent();
            n.setContent(element);
            return old;
        }
        
        return null;
    }

    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
           
                NodeDoubly<E> cursor=last.getNext();

                @Override
                public boolean hasNext() {
                    return cursor!=null;
                }

                @Override
                public E next() {
                    if(cursor == last){
                    cursor = null;
                    return last.getContent();
                }
                cursor = cursor.getNext();
                
                return cursor.getPrevious().getContent();
                }
            };
        
        return it;
    }

    public ListIterator<E> listIterator(){
        if(isEmpty()) return null;
        return new ListIterator<E>(){
            
            NodeDoubly<E> node = last.getNext();
            
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if(node == last){
                    
                    node = last.getNext();
                    
                    return last.getContent();
                }
                node = node.getNext();
                
                return node.getPrevious().getContent();
            }

            @Override
            public boolean hasPrevious() {
                return node != null;
            }

            @Override
            public E previous() {
                if(node == last.getNext()){
                    node = last;
                    return last.getNext().getContent();
                }
                node = node.getPrevious();
                
                return node.getNext().getContent();
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            
        };
    }
    
    public E get(int index) {
        if (index<0 || index>this.size()-1) return null;
        
        if (index==this.size()-1) {//getLast
            return this.last.getContent();
        }
        if(index==0){//getFirst
            return this.last.getNext().getContent();
        }
        int i = 0;
        
        NodeDoubly<E> e = null;
        
        for(NodeDoubly<E> v = this.last.getNext(); i <= index; v=v.getNext()){
            if (i==index){
                e = v;
            }
            i++;
        }
        return e.getContent();
    }

    public boolean add(E element, int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
