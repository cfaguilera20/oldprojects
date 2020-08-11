
// El siguiente codigo es el necesario para maximixar una Zmax de n variables y m restricciones.

//Esta funcion cuenta las variables que tiene la funcion Zmax

int countVar(String zmax){				// Le mandamos como argumento la cadena de texto contenida en en jTextField1
    int count = 0;						// Variable que lleva la cuenta de el numero de variables.
    for(int i = 0; i < zmax.length(); i++)		// Se recorre la cadena recibida y para cada caracter igual a un espacio ' ' que se encuentre se aumenta el contador
        if(zmax.charAt(i) == ' ')
            count++;
    return count + 1;					// Se le agrea uno para obtener el valor real de las variables
}

//Esta funcion cuenta el numero de restricciones

int countRes(String sujA){				// Le mandamos como argumento la cadena de texto contenida en en jTextField2
    int count = 0;						// Variable que lleva la cuenta de el numero de variables.
    for(int i = 0; i < sujA.length(); i++)		// Se recorre la cadena recibida y para cada caracter igual a un espacio ',' que se encuentre se aumenta el contador
        if(sujA.charAt(i) == ',')
            count++;
    return count + 1;					// Se le agrea uno para obtener el valor real de las variables y se retorna el valor
}

//Esta funcion obtiene los coeficientes de Zmax

int[] coeficientes(String zmax, int count){				// Le mandamos como argumento el numero de variables  obtenido con countVar y la cadena de texto contenida en jTextField1
    String numTemp = "";								// Esta variable va acomulando el numero de cada varible
    int j = 0;										// Creamos una varible del tipo entero para llevar el control de el lugar de cada coeficiente
    coef = new int[count];							// Creamos un arreglo unidimensional para guardar el valor de cada variable
    for(int i = 1 ; i < zmax.length() - 1; i++ ){				// Se recorre la cadena recibida
        if(zmax.charAt(i) != ' '){							// Si el caracter en la posision i es diferente de ' ' se acomula el numero en la variable numTemp
            numTemp += zmax.charAt(i);
        }
        if(zmax.charAt(i) == ' ' || i == zmax.length() - 2){		// Si el caracter es igual a ' ' o ya se termino de recorrer la cadena asignamos el numTemp a su posicion asignada, esta es determinada por j
            coef[j] = Integer.parseInt(numTemp);
            numTemp = "";								// Reiniciamos la variable para obtener el nuevo coeficiente
            j ++;										// Aumentamos j para asignar la variable siguiente en su posicion
        }
    }
     return coef;									// retorna el arreglo con los coeficientes
}

//Esta funcion obtiene los coeficientes de las restricciones.

int[][] coeficientesR(String rest, int count, int numVar){	// Le mandamos como argumentos la cadena jTexField2, el numero de restricciones y el numero de variables
    String numTemp = "";								// Esta variable va acomulando el numero de cada varible
    coefr = new int[count][numVar + 1];				// Creamos un arreglo bidimensional para guardar el valor de cada variable
    int j = 0;										// Creamos las variables j y k para llevar el control de las filas y las columnas en que se va almacenando los coeficientes
    int k = 0;
    for(int i = 0 ; i < rest.length() ; i++ ){				// Recorremos la cadena recibida
         if(rest.charAt(i) != '[' && rest.charAt(i) != ']' && rest.charAt(i) != ' ' && rest.charAt(i) != ',')		// Si el caracter en la posision i es diferente de ' ', ',','[' y de ']' se acomula el numero en la variable numTemp
            numTemp += rest.charAt(i);
         if(rest.charAt(i) == ' ' || rest.charAt(i) == ']'){		// Si el caracter coincide con ' ' o ']' se agrega numTemp a su lugar en la tabla de coeficientes
            coefr[j][k] = Integer.parseInt(numTemp);			// Se convierte la variable numTemp a entero para poder almacenarla en la tabla
            numTemp = "";								// Reiniciamos la variable para obtener el nuevo coeficiente
            k ++;										// Pasamos a la siguiente columna
         }
        if(rest.charAt(i) == ','){							// Si el caracter es igual a ',' pasamos a la siguiente fila y regresamos a la primer columna
            j ++;
            k = 0;
        }
    }
    return coefr;									// Retorna el arreglo con los coeficientes.
}

// Esta funcion establece las condiciones iniciales de nuestra area de trabajo osea de la tabla, asigna nombres a las filas y a las columnas y rellena la tabla con los datos de las funciones anteriores.

void iniciaTabla(int NumOfVar, int rest, int[] coef, int[][] coefr){ 	// A esta funcion le madamos como argumento el numero de variables, restricciones y sus respectivos coeficientes
    //Inicia la Tabla
    String[] s = new String[NumOfVar+2+rest];	// Se crea un arreglo de tipo String de tama;o NumOfVar mas 2 mas numero de restricciones esto se usa para poner el encabezado de la tabla
        s[0] = "Base Cb";						// Se asigna nombre de primera columnda
        s[NumOfVar + rest + 1] = "R";			// Se asigna nombre de ultima columna
        for(int i = 1; i <= NumOfVar; i++){		// Pone el encabezado de las variables y su valor
            s[i] = "X" + i + "  " + coef[i - 1];
        }
        for(int i = 1; i <= rest; i++)				// Pone el encabezado de las S
            s[NumOfVar+i] = "S" + i;
    jTable2.setModel(new javax.swing.table.DefaultTableModel(new Object [rest+2][2+NumOfVar+rest],s)); // Modifica la dimension de la tabla y le mandamos el arreglo String anterior
     for(int i = 0; i < rest; i++)				// Da nombre a las filas
            jTable2.setValueAt("S" + (i + 1), i, 0);
    jTable2.setValueAt("Zj" , rest, 0);			// Da nombre a la fila Zj
    jTable2.setValueAt("Ci - Zj" , rest + 1, 0);		// Da nombre a la fila Ci-Zj
    //Fin inicia la tabla
   zj = new double[rest + NumOfVar + 1]; 		// Inicializa la matriz zj
   ss = new int[rest];						// Inicializa la matriz ss sirve para multiplicar las variables que van saliendo por la tabla de los datos
   cizj = new double[rest + NumOfVar];		// Inicializa la matriz cizj
   ci =new int[rest + NumOfVar];				// Inicializa la matriz ci

    creaIdentidad(NumOfVar,rest);				// Manda llamar la funcion crear identidad
    llenarRes(coefr,rest,NumOfVar);			// Mada llamar la funcion llenarRes que pone los datos de las restricciones en la tabla
}

// Esta funcion llena la tabla con las restricciones

void llenarRes(int[][] coef,int rest, int numVar){
    for(int i = 0; i < rest; i++)					// Llena los datos de las restricciones
        for(int j = 0; j < numVar; j++){
             jTable2.setValueAt(coef[i][j], i, j + 1);
        }
    for(int i = 0; i < rest; i++){				// Llena la columna R
        jTable2.setValueAt(coef[i][numVar ], i, numVar + rest + 1);
    }
}

// Esta funcion llena la tabla con la matriz identidad

void creaIdentidad(int NumOfVar,int rest){
    for(int i = 0; i < rest; i++)
        for(int j = 0; j < rest; j++){
            if(j == i)
                jTable2.setValueAt(1, i ,NumOfVar + 1 + j);
            else
                jTable2.setValueAt(0, i , NumOfVar + 1 + j);
        }
}

// Esta funcion busca el pivote y nos regresa las coordenadas de este en nuestra tabla

int[] pivote(){
    double div[] = new double[rest];		// Este arreglo guarda las divisiones de cada iteracion para determinar la fila
    int xy[] = new int[2];					// En este arreglo guardo las coordenadas
    double max;						// Esta variable la utilizo para determinar el valor maximo de las variables de zmax
    double min;						// Esta se utiliza para determinar el min del arreglo de divisiones
    max = coef1[0]; 						// se toma como max lo que este en la posicion 0 solo para facilitar las cosas
    for(int i = 0; i < NumOfVar ; i++ )		// Determina el maximo y la coordenada
        if(coef1[i] > max){
            max = coef1[i];
            xy[1] = i;
        }
    for(int i = 0; i < rest ; i++ ){
        div[i]=sim[i][NumOfVar+rest]/sim[i][xy[1]];      // Hace las divisiones
    }

    min = div[0];						// se toma como min lo que este en la posicion 0 solo para facilitar las cosas
     for(int i = 0; i < rest ; i++ ){			// Determina el minimo y la coordenada
         if(div[i] < min){
             min = div[i];
             xy[0] = i;
         }
    }

    return xy;					// Retorna las coordenadas
}



void simplex(){

    sim = new double[rest][rest + NumOfVar + 2]; // creamos una tabla en la cual vamos a hacer las operaciones del simplex
    for(int i = 0; i < rest; i++){
        for(int j = 0 ; j < rest + NumOfVar + 1 ; j ++)
            sim[i][j] = Double.parseDouble(""+jTable2.getValueAt(i,j+1 )); // obtenemos los datos de la tabla inicializada y los asignamos a la tabla
    }

    pivote = pivote();		//obtenemos coordenadas del pivote
    int fila = pivote[0];
    int col = pivote[1];
    double piv = sim[fila][col];     // Obtenemos el valor del pivote

    for(int i = 0; i < rest + NumOfVar + 1; i++){ // Divide la fila del pivote entre el pvivote y muestra actualiza la tabla del entorno grafico
        sim[fila][i] = sim[fila][i]/piv;
         jTable2.setValueAt(sim[fila][i], fila , i +1 );
    }

    for(int i= 0; i < rest; i++ ){ 			// Hacemos ceros arriva y abajo del pivote y modifica la tabla
        double v =-(sim[i][col]);
        for(int j = 0 ; j < rest + NumOfVar + 1; j++){
            if(i != fila){
                sim[i][j] = (sim[fila][j]*v)+sim[i][j];
            }
        }
    }
    for(int i= 0; i < rest; i++ ){
        for(int j = 0 ; j < rest + NumOfVar + 1; j++){ //Actualiza la tabla del entorno grafico
            jTable2.setValueAt(sim[i][j], i, j + 1);
        }
    }

    ss[pivote[0]] = coef[pivote[1]]; 		// Modifica nuestra matriz ss para descartar la variable de nuevas iteraciones
    for(int i = 0; i < NumOfVar + rest + 1; i++){  	// Es necesario inicializar zj para cada iteracion
        zj[i] = 0;
    }
    for(int i = 0; i < NumOfVar + rest + 1; i++){ //Calcula zj y actualiza datos en entorno grafico
        for(int j = 0; j < rest; j++){
            zj[i] += ss[j] * sim[j][i];
        }
        jTable2.setValueAt(zj[i], rest , i +1 );
    }
    for(int i = 0; i < NumOfVar; i ++){ //Obtiene Ci
        ci[i] = coef[i];
    }
    for(int i = 0; i < rest + NumOfVar ; i++){ // Calcula ci - zj y modifica la tabla en el entorno grafico
        cizj[i] = ci[i] - zj[i];
        jTable2.setValueAt(ci[i] - zj[i], rest + 1 , i +1 );
    }

    coef1[col] = -1; // la variable utilizada la actualiza a -1 para evitar volverla a utilizar


}

//Declaracion de variables.

int pivote[];
int rest;
int NumOfVar;
int coef[];
int coef1[];
int coefr[][];
int ss[];
double sim[][];
double zj[];
double cizj[];
int ci[];
int control = -1;


    @SuppressWarnings("empty-statement")
private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
// TODO add your handling code here:
    rest  = countRes(jTextField2.getText());  // obtiene numero de restricciones
    NumOfVar = countVar(jTextField1.getText()); // obtiene numero de variables
    iniciaTabla(NumOfVar,rest,coeficientes(jTextField1.getText(), NumOfVar),coeficientesR(jTextField2.getText(), rest, NumOfVar)); //inicial tabla
    coef1 =coeficientes(jTextField1.getText(), NumOfVar); //inicializa tabla para auxiliarnos en los calculos cd ci - zj
    control = -1; //para realizar otro sistema sin reiniciar el programa

}

private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
// TODO add your handling code here:
    control ++; //cuenta para detener la iteraciones
    if(control < NumOfVar) //si el numero de iteraciones es igual al de varibles se detiene
        this.simplex(); // llama el metodo simplex
}
