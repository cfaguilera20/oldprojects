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
public class quickSort {
    
    private int i;   
    partition pr = new partition();
    
    public int[] quicksort(int[] array, int p, int r,Graphics g, Canvas canvas1){
        int q;
        if (p < r){
            q = pr.Partition(array,p,r,g,canvas1,r);
            array = pr.returnA();
            array = quicksort(array,p,q,g,canvas1);
            array = quicksort(array,q+1,r,g,canvas1);    
                   
        }
        return array;
    }
    
    
    private int[] exchange(int pos,int[] array){
        int aux;
        aux = array[0];
        array[0] = array[pos-1];        
        array[pos-1] = aux;
        return array;
    }
    
    

}
