/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.awt.Component;
import java.util.List;
import java.io.File;
import java.io.IOException;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.format.YUVFormat;

/**
 * Clase que hereda de VentanaInterna y que tiene la funcionalidad
 * necesaria para crear una ventana interna y mostrar por pantalla
 * el contenido que está visualizando la webcam del ordenador.
 * 
 * @author Antonio Manuel Fernández Cantos
 */
public class VentanaInternaCamara extends VentanaInterna {

    private Player player;
    /**
     * Constructor de la clase que inicializa el atributo player para que muestre
     * por pantalla lo que está visualizando la cámara web del ordenador. En caso
     * de que no se pueda obtener la imagen de la web cam, se inicializa el atributo
     * player a null.
     */
    private VentanaInternaCamara(VentanaPrincipal vp) {
        super(vp, VentanaInterna.CAMARA, "Cámara");
        initComponents();
        try{
            CaptureDeviceInfo device;
            List<CaptureDeviceInfo> deviceList = CaptureDeviceManager.getDeviceList(new YUVFormat());
            device = deviceList.get(0);
            MediaLocator ml = device.getLocator();
            player = Manager.createRealizedPlayer(ml);
            Component areaVisual = player.getVisualComponent();
            if(areaVisual!= null)
                this.add(areaVisual);
            Component panelControl = player.getControlPanelComponent();
            if(panelControl != null)
                this.add(panelControl);
            player.start();
        }catch(Exception e){
            player = null;
        }
        
    }
    
    /**
     * Método de clase que inicializa y devuelve una instancia de esta misma clase. Si no se puede
     * obtener la imagen de la cámara del portatil, el método devuelve un null para índicar que
     * no se ha podido crear correctamente la instancia.
     * @param vp de tipo VentanaPrincipal. Contiene una instancia de la ventana que está intentando
     * crear una instancia de esta ventana interna.
     * @return Devuelve la ventana interna cámara inciailizada. Si no se puede crear correctamente
     * la instancia devuelve null.
     */
    public static VentanaInternaCamara getInstance(VentanaPrincipal vp){
        VentanaInternaCamara vi = new VentanaInternaCamara(vp);
        if(vi.player != null)
            return vi;
        else
            return null;
    }
    
    /**
     * 
     * @return Devuelve el contenido del atributo player. De tipo Player.
     */
    public Player getPlayer(){
        return this.player;
    }
    
    /**
     * Método que para la reproducción de las imagenes obtenidas por la cámara y pone
     * el puntero player a null.
     */
    private void close(){
        this.player.close();
        this.player = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que se activa cuando la ventana ha sido cerrada. Llama al método close().
     * @param evt 
     */
    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.close();
    }//GEN-LAST:event_formInternalFrameClosed

    /**
     * Método que almacena el nombre del fichero.
     * @param n contiene el nombre del fichero.
     */
    @Override
    public void setNombre(String n) {
        this.nombre=n;
    }

    /**
     * 
     * @return Devuelve el valor del atributo nombre. De tipo string.
     */
    @Override
    public String getNombre() {
       return this.nombre;
    }

    /**
     * 
     * @return Devuelve el tipo de ventana interna creada. En este caso es VentanaInternaCamara.
     */
    @Override
    public int getTipo() {
         return this.tipo;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
