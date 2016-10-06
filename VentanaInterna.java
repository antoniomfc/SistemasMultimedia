/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.io.File;

/**
 * Clase abstracta VentanaInterna de la que heredan los atributos y funcionalidad
 * otras Ventana de tipo JInternal frame. Estas ventanas que heredan de VentanaInterna
 * son las que podemos visualizar en el escritorio de VentanaPrincipal. Actualmente hay
 * cinco tipos de VentanaInterna que son: VentanaInternaImagen, VentanaInternaReproductor,
 * VentanaInternaJMFPlayer, VentanaInternaGrabador y VentanaInternaCamara.
 * @author Antonio Manuel Fernández Cantos
 */
public abstract class VentanaInterna extends javax.swing.JInternalFrame {

    /**
     * Atributo de tipo VentanaPrincipal que almacena la instancia de la
     * VentanaPrincipal que contiene el JInternal frame.
     */
    protected VentanaPrincipal vp;
    /**
     * Atributo de tipo int que almacena el tipo de ventana interna usada.
     */
    protected int tipo;
    
    /**
     * Atributo de tipo string que almacena el nombre del archivo que se está utilizando
     * alguna de las ventanas internas.
     */
    protected String nombre;
    /**
     * Atributo de clase que se utiliza para indicar la ventana interna es de tipo
     * imagen.
     */
    public static final int IMAGEN = 1;
    /**
     * Atributo de clase que se utiliza para indicar la ventana interna es de tipo
     * audio.
     */
    public static final int AUDIO = 2;
    /**
     * Atributo de clase que se utiliza para indicar la ventana interna es de tipo
     * video.
     */
    public static final int VIDEO = 3;
    /**
     * Atributo de clase que se utiliza para indicar la ventana interna es de tipo
     * grabador.
     */
    public static final int GRABAR = 4;
    /**
     * Constructor que es llamado por las ventanas internas que heredan de esta.
     * Es llamado como super() y crea los atributos por defecto.
     */
    public static final int CAMARA = 5;
    
    /**
     * Constructor de la clase que inicializa los valores por defecto.
     */
    public VentanaInterna() {
        initComponents();
        this.vp=null;
        this.tipo=0;
        this.nombre="";
    }
    
    /**
     * Constructor de la clase que es llamado por las ventanas internas que heredan de esta
     * @param vp de tipo VentanaPrincipal, contiene la instancia en la cual contiene
     * a la propia ventana interna.
     * @param tipo de tipo entero, indica el tipo de ventana interna creada.
     */
    public VentanaInterna(VentanaPrincipal vp, int tipo, String nombre){
        initComponents();
        this.vp=vp;
        this.tipo=tipo;
        this.nombre = nombre;
    }
    
    /**
     * Método abstracto que recibe un string y almacena su valor en el atributo
     * nombre.
     * @param n de tipo String. Su valor es almacenado en el atributo nombre.
     */
    abstract public void setNombre(String n);
    
    /**
     * 
     * @return Devuelve el valor del atributo nombre.
     */
    abstract public String getNombre();
    /**
     * Método abstracto que devuelve el tipo de la ventana interna.
     * @return devuelve un entero.
     */
    abstract public int getTipo();
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        getContentPane().setLayout(new java.awt.FlowLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}