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
public class buildHeap {        
    private int i;
    private int hsize;
    heapify hf = new heapify();    
    graficaA ga = new graficaA(); 
    
    public int[] BuildHeap(int[] array,int aSize,Graphics g, Canvas canvas1){        
        hsize = array.length;
        for(i = (int)(array.length / 2); i >= 1; i--){
            this.hf.Heapify(array, i,hsize,aSize,g,canvas1);   
            ga.paint(g, canvas1);
            for(int z = 0; z < aSize; z++){                
                ga.grafica(z, array, g);
            
            } 
        } 
        return array;        
    }
        
    

}
