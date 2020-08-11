/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blackjack;

import java.awt.Canvas;
import javax.swing.ImageIcon;

/**
 *
 * @author carlos
 */

/*
 * 
 * Trebol - 1-13
 * Diamantes - 14 - 26 
 * Corazones - 27 - 39
 * Espadas - 40 - 52
 *  JQK ==> 10
 *  AS ==> 1 o 11
 * 
 */

public class carta {
    
    public void ponCarta(Canvas canvas1,int cartaNum){        
         String direccion = "/home/carlos/Escritorio/Cartas/";
         ImageIcon imagen;
         int alto ;
         int largo;
         imagen = new ImageIcon(direccion+"c1.png");
         alto = imagen.getIconHeight();
         largo = imagen.getIconWidth();
         
         imagen.paintIcon(canvas1, canvas1.getGraphics(),0,0);
         imagen = new ImageIcon(direccion+"c2.png");
         imagen.paintIcon(canvas1, canvas1.getGraphics(),25,0);
    }

}
