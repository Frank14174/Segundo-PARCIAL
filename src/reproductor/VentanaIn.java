/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor;

import ListaMusic.ListaM;
import ListaMusic.NodoM;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 *
 * @author Franklin
 */
public class VentanaIn extends javax.swing.JFrame {

   
   private ListaM list = new ListaM(); 
   private NodoM actual = null;
   private Short x=0;
   private Cplayer player;
   private DefaultListModel lista_modelo = new DefaultListModel();
   private String ultimalista ="vacio";
   private boolean cambios = false;
   public boolean detenido = false;
   
   
    public VentanaIn() {
        
        setTitle("Reproductor");
        setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/icono.jpg"));
        setIconImage(icon);
        initComponents();
        setLocationRelativeTo(null);
        nombre_can.setEditable(false);
        jSlider1.setEnabled(false);
        
        lista_can.addMouseListener(new MouseAdapter(){
         public void mouseClick (MouseEvent evt){
             JList lista =(JList) evt.getSource();
             if(evt.getClickCount()==2){
                 int inicio = lista.locationToIndex(evt.getPoint());
                 if(inicio != -1){
                     actual = list.ObtenerCa(inicio);
                     x=0;
                    playActionPerformed(null);
                 }
             
             }
             
         }   
        });
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lista_can = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        anterior = new javax.swing.JButton();
        play = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        agregar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        detener = new javax.swing.JButton();
        nombre_can = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jScrollPane1.setViewportView(lista_can);

        jLabel1.setText("LISTA DE CANCIONES");

        anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior.jpg"))); // NOI18N
        anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorActionPerformed(evt);
            }
        });

        play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/play.jpg"))); // NOI18N
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });

        siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/siguiente.jpg"))); // NOI18N
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/volumen.jpg"))); // NOI18N

        jSlider1.setOrientation(javax.swing.JSlider.VERTICAL);

        agregar.setText("Abrir archivo");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        eliminar.setText("Quitar ");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        detener.setText("Detener");
        detener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detenerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(81, 81, 81)
                        .addComponent(nombre_can, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(agregar)
                                        .addGap(81, 81, 81)
                                        .addComponent(eliminar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(77, 77, 77)
                                        .addComponent(play, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(detener))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombre_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(anterior, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(play, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(siguiente, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregar)
                    .addComponent(eliminar)
                    .addComponent(detener))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorActionPerformed
        
        if(actual== null){
            return;
        }
        if(actual.anterior==null){
            actual=actual.anterior;
         return;
        }
        
        if(actual.suguiente==null){
        actual=actual.suguiente;
        return;
        }
        x=0;
        playActionPerformed(evt);
    }//GEN-LAST:event_anteriorActionPerformed

    public void eventoSiguiente(){
        siguienteActionPerformed(null);
    }
    
    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
      
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo MP3", "mp3", "mp3"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        int seleccion = fileChooser.showOpenDialog(this);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File files[] = fileChooser.getSelectedFiles();
            boolean noMp3 = false, repetidos = false;
            cambios = true;
            
            for(File file : files){
            String name = file.getName();
                if(name.length()<4 || !name.substring(name.length() - 4, name.length()).equalsIgnoreCase(".mp3")){
                    noMp3=true;
                    continue;
             }
                if(list.buscar(file.getName(), file.getPath())){
                    repetidos = true;
                    continue;
                }
                list.insertar(file.getName(), file.getPath());
                System.out.println(file.getName());
                System.out.println(file.getPath());
                lista_modelo.addElement(file.getName());
                lista_can.setModel(lista_modelo);
            }
            if (noMp3) {
                JOptionPane.showMessageDialog(null, "No son archivo(s) mp3", "alerta", 0);
            }
            if (repetidos) {
                JOptionPane.showMessageDialog(null, "Son archivos repetidos", "alerta", 0);
            }
            
        }
    }//GEN-LAST:event_agregarActionPerformed

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        // TODO add your handling code here
        
        detenido = true;
        if(list.IsEmpety()){
             JOptionPane.showMessageDialog(null, "VACIO", "ALERTA", 1);
        }else{
            if(actual==null){
                actual=list.primera;
            
            }
            try{
                if(x==0){
                  player.control.open(new URL("file:///"+actual.direccion));
                  player.control.play();
                  
                  System.out.println("INICIA CANCION");
                  nombre_can.setText(actual.nombre);
                  jSlider1.setEnabled(true);
                  x=1;
                  play.setIcon(new ImageIcon(getClass().getResource("/imagenes/pausa.jpg")));
                 
                }else{
                    if(x==1){
                    player.control.pause();
                    System.out.println("PAUSA");
                    x=2;
                    play.setIcon(new ImageIcon(getClass().getResource("/imagenes/play.jpg")));
                    
                    }else{
                        player.control.resume();
                        System.out.println("CONTINUA");
                        x=1;
                        play.setIcon(new ImageIcon(getClass().getResource("/imagenes/pausa.jpg")));
                    }
                
                }
            }catch(BasicPlayerException ex){
                 JOptionPane.showMessageDialog(null, "error al abrir\nla cancion", "alerta", 1);
                 x = 0;
            } catch (MalformedURLException ex) {
                Logger.getLogger(VentanaIn.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "error al abrir la direccion\nde la cancion", "alerta", 1);
                x = 0;
            }
        }
        detenido=false;
        
    }//GEN-LAST:event_playActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        
        if(list.IsEmpety()){
            return;
        }
        int q = list.indice(actual);
        
        if(q==-1){
            JOptionPane.showMessageDialog(null, "ha ocurrido un\nerror!!!", "alerta", 1);
        }else{
            lista_modelo.remove(q);
            list.borrar(actual);
            detenerActionPerformed(evt);
            
            if(list.IsEmpety()){
                actual=null;
                nombre_can.setText("...");
            }else{
                if(list.tama==1){
                    actual=list.primera;
                    
                }else{
                    if(actual.suguiente==null){
                        actual=actual.anterior;
                    }else{
                        actual=actual.suguiente;
                    }
                }
                nombre_can.setText(actual.nombre);
            }
               
        }
            cambios=true;
    }//GEN-LAST:event_eliminarActionPerformed

    private void detenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detenerActionPerformed
        // TODO add your handling code here:
        detenido = true;
        play.setIcon(new ImageIcon(getClass().getResource("/imagenes/play.jpg")));
        try {
            player.control.stop();
            x = 0;
            jSlider1.setEnabled(false);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(VentanaIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        detenido = false;
        
    }//GEN-LAST:event_detenerActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {                                      
        try {
            player.control.setGain((double) jSlider1.getValue() / 100);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(VentanaIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
        
        if(actual==null){
        
            return;
        }
        if(actual.suguiente==null){
          
            actual=actual.suguiente;
        }else{
            int index = (int) (Math.random()*list.tama);
            actual= list.ObtenerCa(index);
        }
        x=0;
        playActionPerformed(evt);
        
    }//GEN-LAST:event_siguienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton anterior;
    private javax.swing.JButton detener;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JList<String> lista_can;
    private javax.swing.JTextField nombre_can;
    private javax.swing.JButton play;
    private javax.swing.JButton siguiente;
    // End of variables declaration//GEN-END:variables

    private void siguienteActionPerformed(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
