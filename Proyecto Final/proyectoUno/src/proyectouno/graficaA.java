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
public class graficaA extends Canvas{
    private int maxx;
    private int maxy;
    Canvas canvas1;
    
    public void paint (Graphics g,Canvas canvas1)
    {   
        this.canvas1 = canvas1;
        canvas1.update(g);
        this.maxx = canvas1.getWidth();
        this.maxy = canvas1.getHeight();
        g.drawLine(0, maxy-20, maxx, maxy-20);
        g.drawLine(20, 0,20 , maxy);
        
        
    }  
    public void grafica(int i, int [] array,Graphics g){
        this.maxx = canvas1.getWidth()-20;
        this.maxy = canvas1.getHeight()-20;
        g.drawLine(i+20, maxy - array[i], i+20, maxy-array[i] );
        
    }
    
    
}