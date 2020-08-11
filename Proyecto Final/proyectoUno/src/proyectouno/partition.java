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
public class partition {
    int[] array;
    graficaA ga = new graficaA();
    public int Partition(int[] array,int p, int r,Graphics g, Canvas canvas1, int aSize){
        this.array = array;
        
        int x = this.array[p-1];
        int i = p - 1;
        int j = r + 1;
        while(i < j){
            i++;while (array[i-1] < x) i++;
            j--; while (array[j-1] > x) j--;
            if (i < j){
                array = exchange(i, j, array); 
                ga.paint(g,canvas1);
                for(int z = 0; z < aSize; z++){
                    ga.grafica(z, array, g);
            
                }                   
            }
                
                
        }
        return j;
    }
    public int[] returnA(){
        return array;
    }
    private int[] exchange(int i,int j,int[] array){
        int aux;
        aux = array[i-1];
        array[i-1] = array[j-1];        
        array[j-1] = aux;
        return array;
    }

}
