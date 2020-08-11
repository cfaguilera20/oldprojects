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
public class heapify {    
    private int l;
    private int r;
    private int largest; 
    graficaA ga = new graficaA();      
    
    public int[] Heapify(int[] Array,int i,int hsize,int aSize,Graphics g, Canvas canvas1){        
        l = 2 * i;
        r = 2 * i + 1;
        if ((l <= hsize) && (Array[l-1] > Array[i-1])){
            largest = l;
        }
        else
            largest = i;
        if ((r <= hsize) && (Array[r-1] > Array[largest-1])){
            largest = r;
        }
        if (largest != i){
            Array = exchange(i,largest,Array);
            ga.paint(g, canvas1);
            for(int z = 0; z < aSize; z++){
                ga.grafica(z, Array, g);
            
            } 
            Array = Heapify(Array, largest,hsize,aSize,g,canvas1);
        }
        
        return Array;
    }
    
    private int[] exchange(int pos, int lar,int[] array){
        int aux;
        aux = array[lar-1];
        array[lar-1] = array[pos-1];        
        array[pos-1] = aux;
        return array;
    }

}
