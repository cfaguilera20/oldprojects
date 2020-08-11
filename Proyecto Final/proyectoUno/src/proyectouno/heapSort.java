/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectouno;

import java.awt.Canvas;
import java.awt.Graphics;

/**
 *
 * @author dersinc
 */
public class heapSort {
    private int i;    
    private int hsize;
    buildHeap bh = new buildHeap();    
    heapify hf = new heapify();
    graficaA ga = new graficaA();      
    
    public int[] heapsort(int[] Array, int aSize,Graphics g,Canvas canvas1){         
        
        hsize = Array.length;        
        Array = bh.BuildHeap(Array,aSize,g,canvas1);
        for(i = Array.length; i >= 2; i--){
            Array = exchange(i,Array);    
            ga.paint(g,canvas1);
            for(int z = 0; z < aSize; z++){
                ga.grafica(z, Array, g);
            
            } 
            hsize -= 1;
            Array = hf.Heapify(Array,1,hsize,aSize,g,canvas1);             
            
        }
        return Array;
    }
    
    
    private int[] exchange(int pos,int[] array){
        int aux;
        aux = array[0];
        array[0] = array[pos-1];        
        array[pos-1] = aux;
        return array;
    }
    
    

}
