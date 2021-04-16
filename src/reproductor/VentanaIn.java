/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor;

import ListaMusic.ListaCirM;
import ListaMusic.ListaM;
import ListaMusic.NodoCirM;
import ListaMusic.NodoM;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
   private ListaCirM listcir = new ListaCirM();
   private NodoCirM actual2 = null;
   private Short x=0;
   private Cplayer player;
   private DefaultListModel lista_modelo = new DefaultListModel();
   private String ultimalista = "vacio";
   private boolean cambios = false;
   public boolean detenido = false;
   
   
    public VentanaIn() {
        
        setTitle("Reproductor MP3");
        setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/icon.jpg"));
        setIconImage(icon);
        initComponents();
        setLocationRelativeTo(null);
        nombre_can.setEditable(false);
        jSlider1.setEnabled(false);
        
        lista_can.addMouseListener(new MouseAdapter(){
         public void mouseClicked (MouseEvent evt){
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
        
        try {
            BufferedReader tec = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\config"));
            String aux = tec.readLine();
            
            if (aux.equals("si")) {
                aux = tec.readLine();
                
                if (!aux.equals("vacio")) {
                    cargarLista(aux);
                }
                
            } else {
                cargarListaIn.setSelected(false);
                
            }
            
        } catch (Exception ex) {
            
        }
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                if (!list.IsEmpety() && cambios) {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Guardar?");
                    if (opcion == JOptionPane.YES_OPTION) {
                        if (ultimalista.equals("vacio")) {
                            ultimalista = crearArchivolista();
                        }
                        if (ultimalista == null) {
                            ultimalista = "vacio";
                        } else {
                            guardarLista(ultimalista);
                        }
                    }
                    
                }
                try {
                    BufferedWriter bw = new BufferedWriter(
                            new FileWriter(System.getProperty("user.dir") + "\\config"));
                    if (cargarListaIn.isSelected()) {
                        bw.write("si\r\n");
                        bw.write(ultimalista + "\r\n");
                    } else {
                        bw.write("No\r\n");
                    }
                    bw.close();
                    
                } catch (Exception e) {
                    
                }
                System.exit(0);
            }
            
        });
        
        player = new Cplayer(this);
    }

    public void cargarLista(String ruta){
        
        try{
            FileInputStream fis = new FileInputStream(new File(ruta));
            BufferedReader tec = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String input[];
            tec.readLine();
            
            while (tec.ready()) {
                input = tec.readLine().split("<");
                System.out.println(input[0] + " , " + input[1]);
                list.insertar(input[0], input[1]);
                lista_modelo.addElement(input[0]);
            }
            ultimalista = ruta;
            cambios=false;
            
            
        }catch(FileNotFoundException ex){
           JOptionPane.showMessageDialog(null, "Error\nal cargar la lista!!", "Alerta", 1);
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error!!", "Alerta", 1);
        }
        lista_can.setModel(lista_modelo);
    }
    
    public void guardarLista(String dire){
        try{
            BufferedWriter tec = new BufferedWriter(new FileWriter(dire));
            tec.write("\r\n");
            
            NodoM aux =list.primera;
            
            while(aux != null){
                tec.append(aux.nombre+"<"+aux.direccion+"\r\n");
                aux = aux.suguiente;
                
            }
            tec.close();
            cambios= false;
            
        }catch(Exception e){
                System.out.println("Error"+e);
        }
    }
    
    public String crearArchivolista(){
        String n = JOptionPane.showInputDialog("Ingresa El Nombre De La Lista");
          if (n == null || n.isEmpty()) {
            return null;
            }
        
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = chooser.showOpenDialog(this);
        File ruta;

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            ruta = chooser.getSelectedFile();
        } else {
            return null;
        }
        File save = new File(ruta.getAbsolutePath() + "\\" + n + ".lis");
        if (save.exists()) {
            save.delete();
        }
        return save.getAbsolutePath();
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
        repeticion = new javax.swing.JButton();
        jMenuBar3 = new javax.swing.JMenuBar();
        cargarListaInicio = new javax.swing.JMenu();
        cargarListaIn = new javax.swing.JCheckBoxMenuItem();
        guardarlista = new javax.swing.JCheckBoxMenuItem();
        cargarlista = new javax.swing.JCheckBoxMenuItem();
        salir = new javax.swing.JCheckBoxMenuItem();
        jMenu7 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jScrollPane1.setViewportView(lista_can);

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 12)); // NOI18N
        jLabel1.setText("LISTA DE CANCIONES");

        anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior1.jpg"))); // NOI18N
        anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorActionPerformed(evt);
            }
        });

        play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/play1.jpg"))); // NOI18N
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });

        siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/siguiente1.jpg"))); // NOI18N
        siguiente.setMaximumSize(new java.awt.Dimension(50, 50));
        siguiente.setMinimumSize(new java.awt.Dimension(50, 50));
        siguiente.setPreferredSize(new java.awt.Dimension(50, 50));
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/volumen1.jpg"))); // NOI18N

        jSlider1.setForeground(new java.awt.Color(51, 51, 255));
        jSlider1.setToolTipText("");
        jSlider1.setValue(100);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        agregar.setBackground(new java.awt.Color(153, 153, 255));
        agregar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        agregar.setText("Abrir Archivo");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        eliminar.setBackground(new java.awt.Color(153, 153, 255));
        eliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eliminar.setText("Quitar ");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        detener.setBackground(new java.awt.Color(153, 153, 255));
        detener.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        detener.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/stop1.jpg"))); // NOI18N
        detener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detenerActionPerformed(evt);
            }
        });

        nombre_can.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_canActionPerformed(evt);
            }
        });

        repeticion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/repeticion1.jpg"))); // NOI18N
        repeticion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repeticionActionPerformed(evt);
            }
        });

        cargarListaInicio.setBackground(new java.awt.Color(255, 51, 51));
        cargarListaInicio.setText("ARCHIVO");

        cargarListaIn.setText("Cargar Lista Al Abrir");
        cargarListaIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarListaInActionPerformed(evt);
            }
        });
        cargarListaInicio.add(cargarListaIn);

        guardarlista.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        guardarlista.setText("Guardar Lista");
        guardarlista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarlistaActionPerformed(evt);
            }
        });
        cargarListaInicio.add(guardarlista);

        cargarlista.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        cargarlista.setText("Cargar Lista");
        cargarlista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarlistaActionPerformed(evt);
            }
        });
        cargarListaInicio.add(cargarlista);

        salir.setText("SALIR");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        cargarListaInicio.add(salir);

        jMenuBar3.add(cargarListaInicio);
        jMenuBar3.add(jMenu7);

        setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(repeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(agregar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(eliminar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(play, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(detener, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nombre_can, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombre_can, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(play, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(siguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(repeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(detener, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorActionPerformed
        
        if(actual== null){
            return;
        }
        if(actual.anterior==null){
            return;
        }
         actual=actual.anterior;
         
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
                JOptionPane.showMessageDialog(null, "No son archivo(s) mp3", "Alerta", 0);
            }
            if (repetidos) {
                JOptionPane.showMessageDialog(null, "Son archivos repetidos", "Alerta", 0);
            }
            
        }
    }//GEN-LAST:event_agregarActionPerformed

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        
        detenido = true;
        if(list.IsEmpety()){
             JOptionPane.showMessageDialog(null, "Agrega Archivos MP3", "ALERTA", 1);
        }else{
            if(actual==null){
                actual=list.primera;
            
            }
            try{
                if(x == 0){
                  player.control.open(new URL("file:///"+actual.direccion));
                  player.control.play();
                  
                  System.out.println("INICIA CANCION");
                  nombre_can.setText(actual.nombre);
                  jSlider1.setEnabled(true);
                  x=1;
                  play.setIcon(new ImageIcon(getClass().getResource("/imagenes/pausa1.jpg")));
                 
                }else{
                    if(x == 1){
                    player.control.pause();
                    System.out.println("PAUSA");
                    x = 2;
                    play.setIcon(new ImageIcon(getClass().getResource("/imagenes/play1.jpg")));
                    
                    }else{
                        player.control.resume();
                        System.out.println("CONTINUA");
                        x = 1;
                        play.setIcon(new ImageIcon(getClass().getResource("/imagenes/pausa1.jpg")));
                    }
                
                }
            }catch(BasicPlayerException ex){
                 JOptionPane.showMessageDialog(null, "Error al abrir\nLa Cancion", "Alerta", 1);
                 x = 0;
            } catch (MalformedURLException ex) {
                Logger.getLogger(VentanaIn.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error Al Abrir La Ruta\nDe La Cancion", "Alerta", 1);
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
        
        if(q == -1){
            JOptionPane.showMessageDialog(null, "Hay un\nerror!!!", "Alerta", 1);
        }else{
            lista_modelo.remove(q);
            list.borrar(actual);
            detenerActionPerformed(evt);
            
            if(list.IsEmpety()){
                actual=null;
                nombre_can.setText("");
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
        play.setIcon(new ImageIcon(getClass().getResource("/imagenes/play1.jpg")));
        try {
            player.control.stop();
            x = 0;
            jSlider1.setEnabled(false);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(VentanaIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        detenido = false;
        
    }//GEN-LAST:event_detenerActionPerformed

    
    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
        
        if(actual==null){
            
            return;
        }
         if(actual.suguiente==null){
         
             return;
         }
         actual=actual.suguiente;
        
        x=0;
        playActionPerformed(evt);
        
    }//GEN-LAST:event_siguienteActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        // TODO add your handling code here:
         try {
            player.control.setGain((double) jSlider1.getValue() / 100);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(VentanaIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jSlider1StateChanged

    private void guardarlistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarlistaActionPerformed
        if(list.IsEmpety()){
            JOptionPane.showMessageDialog(null, "Sin Canciones!!!", "Alerta", 1);
            return;
        }
        guardarLista(crearArchivolista());
    }//GEN-LAST:event_guardarlistaActionPerformed

    private void cargarlistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarlistaActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("archivo lis", "lis"));
        int seleccion = chooser.showOpenDialog(this);
        
        if(seleccion == JFileChooser.APPROVE_OPTION){
            detenerActionPerformed(evt);
            list.clear();
            lista_modelo.clear();
            actual=list.primera;
            
            String name = chooser.getSelectedFile().getName();
            if (name.length() < 4 || !name.substring(name.length() - 4, name.length()).equalsIgnoreCase(".lis")) {
                JOptionPane.showMessageDialog(null, "no es una lista", "alerta", 0);
                return;
            }
            
            cargarLista(chooser.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_cargarlistaActionPerformed

    private void repeticionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repeticionActionPerformed
          
    }//GEN-LAST:event_repeticionActionPerformed

    private void cargarListaInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarListaInActionPerformed
        agregarActionPerformed(evt);
    }//GEN-LAST:event_cargarListaInActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);        
    }//GEN-LAST:event_salirActionPerformed

    private void nombre_canActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_canActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_canActionPerformed

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
    private javax.swing.JCheckBoxMenuItem cargarListaIn;
    private javax.swing.JMenu cargarListaInicio;
    private javax.swing.JCheckBoxMenuItem cargarlista;
    private javax.swing.JButton detener;
    private javax.swing.JButton eliminar;
    private javax.swing.JCheckBoxMenuItem guardarlista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JList<String> lista_can;
    private javax.swing.JTextField nombre_can;
    private javax.swing.JButton play;
    private javax.swing.JButton repeticion;
    private javax.swing.JCheckBoxMenuItem salir;
    private javax.swing.JButton siguiente;
    // End of variables declaration//GEN-END:variables

    private void siguienteActionPerformed(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
