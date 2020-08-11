/*
 * SimplexView.java
 */

package simplex;

import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;


/**
 * The application's main frame.
 */
public class SimplexView extends FrameView {

    public SimplexView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(simplex.SimplexApp.class).getContext().getResourceMap(SimplexView.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(32767, 32767));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setBackground(resourceMap.getColor("jTable2.background")); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [0][0],
            new String [0]
        ));
        jTable2.setColumnSelectionAllowed(true);
        jTable2.setName("jTable2"); // NOI18N
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addGap(72, 72, 72))
        );

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 613, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents
int countVar(String zmax){
    int count = 0;
    for(int i = 0; i < zmax.length(); i++)
        if(zmax.charAt(i) == ' ')
            count++;
    return count + 1;
}
int countRes(String sujA){
    int count = 0;
    for(int i = 0; i < sujA.length(); i++)
        if(sujA.charAt(i) == ',')
            count++;
    return count + 1;
}
int[] coeficientes(String zmax, int count){
    String numTemp = "";
    int j = 0;
    coef = new int[count];
    for(int i = 1 ; i < zmax.length() - 1; i++ ){
        if(zmax.charAt(i) != ' '){
            numTemp += zmax.charAt(i);
        }
        if(zmax.charAt(i) == ' ' || i == zmax.length() - 2){
            coef[j] = Integer.parseInt(numTemp);
            numTemp = "";
            j ++;
        }
    }
     return coef;
}
int[][] coeficientesR(String rest, int count, int numVar){
    String numTemp = "";
    coefr = new int[count][numVar + 1];
    int j = 0;
    int k = 0;
    for(int i = 0 ; i < rest.length() ; i++ ){
         if(rest.charAt(i) != '[' && rest.charAt(i) != ']' && rest.charAt(i) != ' ' && rest.charAt(i) != ',')
            numTemp += rest.charAt(i);
         if(rest.charAt(i) == ' ' || rest.charAt(i) == ']'){
            coefr[j][k] = Integer.parseInt(numTemp);
            numTemp = "";
            k ++;
         }
        if(rest.charAt(i) == ','){
            j ++;
            k = 0;
        }
    }
    return coefr;
}
void iniciaTabla(int NumOfVar, int rest, int[] coef, int[][] coefr){
    //Inicia la Tabla
    String[] s = new String[NumOfVar+2+rest];
        s[0] = "Base Cb";
        s[NumOfVar + rest + 1] = "R";
        for(int i = 1; i <= NumOfVar; i++){
            s[i] = "X" + i + "  " + coef[i - 1];
        }
        for(int i = 1; i <= rest; i++)
            s[NumOfVar+i] = "S" + i;
    jTable2.setModel(new javax.swing.table.DefaultTableModel(new Object [rest+2][2+NumOfVar+rest],s));
     for(int i = 0; i < rest; i++)
            jTable2.setValueAt("S" + (i + 1), i, 0);
    jTable2.setValueAt("Zj" , rest, 0);
    jTable2.setValueAt("Ci + Zj" , rest + 1, 0);
    //Fin inicia la tabla
   zj = new double[rest + NumOfVar + 1]; 
   ss = new int[rest];
   cizj = new double[rest + NumOfVar];
   ci =new int[rest + NumOfVar];
  
    creaIdentidad(NumOfVar,rest);
    llenarRes(coefr,rest,NumOfVar);
}
void llenarRes(int[][] coef,int rest, int numVar){
    for(int i = 0; i < rest; i++)
        for(int j = 0; j < numVar; j++){
             jTable2.setValueAt(coef[i][j], i, j + 1);
        }
    for(int i = 0; i < rest; i++){
        jTable2.setValueAt(coef[i][numVar ], i, numVar + rest + 1);
    }
}
void creaIdentidad(int NumOfVar,int rest){
    for(int i = 0; i < rest; i++)
        for(int j = 0; j < rest; j++){
            if(j == i)
                jTable2.setValueAt(1, i ,NumOfVar + 1 + j);
            else
                jTable2.setValueAt(0, i , NumOfVar + 1 + j);
        }
}
int[] pivote(){
    double div[] = new double[rest];
    int xy[] = new int[2];
    double max;
    double min;
    max = coef1[0];
    for(int i = 0; i < NumOfVar ; i++ )
        if(coef1[i] > max){
            max = coef1[i];
            xy[1] = i;
        }
    for(int i = 0; i < rest ; i++ ){
        div[i]=sim[i][NumOfVar+rest]/sim[i][xy[1]];        
        System.out.println("Divisi  "+div[i]);
    }
    
    min = div[0];
     for(int i = 0; i < rest ; i++ ){
         if(div[i] < min){
             min = div[i];
             xy[0] = i;
         }
    }
    System.out.println("Piv "+xy[0]+"  "+xy[1]);
    
    return xy;
}
void simplex(){
    
    sim = new double[rest][rest + NumOfVar + 2];
    for(int i = 0; i < rest; i++){
        for(int j = 0 ; j < rest + NumOfVar + 1 ; j ++)
            sim[i][j] = Double.parseDouble(""+jTable2.getValueAt(i,j+1 ));
    }
    
    pivote = pivote();
    int fila = pivote[0];
    int col = pivote[1];
    double piv = sim[fila][col];     
    
    for(int i = 0; i < rest + NumOfVar + 1; i++){
        sim[fila][i] = sim[fila][i]/piv;
         jTable2.setValueAt(sim[fila][i], fila , i +1 );
    }
    
    for(int i= 0; i < rest; i++ ){
        double v =-(sim[i][col]);
        for(int j = 0 ; j < rest + NumOfVar + 1; j++){
            if(i != fila){
                sim[i][j] = (sim[fila][j]*v)+sim[i][j];    
            }
        }
    }
    for(int i= 0; i < rest; i++ ){
        for(int j = 0 ; j < rest + NumOfVar + 1; j++){
            jTable2.setValueAt(sim[i][j], i, j + 1);
        }
    }
    
    ss[pivote[0]] = coef[pivote[1]];
    for(int i = 0; i < NumOfVar + rest + 1; i++){
        zj[i] = 0;
    }
    for(int i = 0; i < NumOfVar + rest + 1; i++){
        for(int j = 0; j < rest; j++){
            zj[i] += ss[j] * sim[j][i];            
        }
        jTable2.setValueAt(zj[i], rest , i +1 );
    }
    for(int i = 0; i < NumOfVar; i ++){
        ci[i] = coef[i];
    }
    for(int i = 0; i < rest + NumOfVar ; i++){
        cizj[i] = ci[i] - zj[i];
        jTable2.setValueAt(ci[i] - zj[i], rest + 1 , i +1 );
    }
    
    coef1[col] = -1;
    
  
}
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
private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
// TODO add your handling code here:
    rest  = countRes(jTextField2.getText());
    NumOfVar = countVar(jTextField1.getText());
    iniciaTabla(NumOfVar,rest,coeficientes(jTextField1.getText(), NumOfVar),coeficientesR(jTextField2.getText(), rest, NumOfVar));
    coef1 =coeficientes(jTextField1.getText(), NumOfVar);
    control = -1;
    
}//GEN-LAST:event_jButton1MouseClicked

private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
// TODO add your handling code here:
    control ++;
    if(control < NumOfVar)
        this.simplex();
}//GEN-LAST:event_jButton2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
