/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.AMFC.iu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import sm.AMFC.atributos.Atributos;
import sm.AMFC.graficos.Curva2D;
import sm.AMFC.graficos.Elipse2D;
import sm.AMFC.graficos.Figura;
import sm.AMFC.graficos.Linea2D;
import sm.AMFC.graficos.Poligono2D;
import sm.AMFC.graficos.Punto2D;
import sm.AMFC.graficos.Rectangulo2D;



/**
 * Clase que representa el lienzo de dibujo donde se alojan las figuras
 * con sus atributos. Tiene la funcionalidad necesaria para crear, almacenar y representar
 * las figuras en el lienzo.
 * @author Antonio Manuel Fernández Cantos
 */
public class Lienzo2D extends javax.swing.JPanel {

    /**
     * Almacena todas las figuras creadas.
     */
    private ArrayList<Figura> misFiguras;
    /**
     * Figura actual que se está creando o modificando.
     */
    private Figura currentShape;
    /**
     * Tipo de figura seleccionada.
     */
    private int figura;
    /**
     * Edición de figura activada/desactivada.
     */
    private boolean editar;
    /**
     * Atributos que debe tener la figura que se crea justo en ese momento.
     */
    private Atributos atributosActual;
    /**
     * Representa el ancho del lienzo de dibujo.
     */
    protected int ancho;
    /**
     * Representa el alto del lienzo de dibujo.
     */
    protected int alto;
    /**
     * Almacena el paso por el que va la creación de una figura.
     */
    private int step;
    /**
     * Almacena un punto para el modificado de posición de las figuras.
     */
    private Point2D punt; 
    
    /**
     * Constantes de clase que representan el tipo de figura seleccionada.
     */
    public static final int PUNTO = 1;
    public static final int LINEA = 2;
    public static final int RECTANGULO = 3;
    public static final int ELIPSE = 4;
    public static final int POLIGONO = 5;
    public static final int CURVA = 6;
    /**
     * Constructor de la clase que inicializa los valores por defecto.
     * 
     */
    public Lienzo2D() {
        initComponents();

        misFiguras = new ArrayList();
        figura=1; // Por defecto el punto
        editar=false;
        atributosActual = new Atributos();
        ancho = 500;
        alto= 500;
        step=0;
    }
    
    /**
     * 
     * @return Devuelve el ancho del lienzo. 
     */
    public int getAncho(){
        return this.ancho;
    }
    
    /**
     * 
     * @return Devuelve el alto del lienzo. 
     */
    public int getAlto(){
        return this.alto;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(java.awt.Color.white);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Método que obtiene una figura que contiene un punto y la devuelve.
     * @param p coordenada que contiene dicha figura.
     * @return Devuelve la figura que contiene el punto. Null en caso de no contener el punto.
     */
    private Figura getSelectedShape(Point2D p){
        
        for(Figura s:misFiguras){
            if(s.contains(p))
                return s;
        }
        
        return null;
    }
    
    /**
     * Almacena una instancia atributos pasada por parámetro.
     * @param atributos contiene la instancia a almacenar.
     */
    public void setAtributos(Atributos atributos){
        this.atributosActual = atributos;
    }
    
    /**
     * Método que devuelve una instancia de tipo atributos. Si está activa la opción editar,
     * devuelve los atributos de la figura actual para que se pueda editar sus atributos. En caso
     * de que la opción editar no esté activa, devuelve los atributos actuales del lienzo.
     * @return Si la opción editar está activa, devuelve los atributos de la figura a editar, en caso 
     * contrario devuelve los atributos con los que se dibujará la siguiente figura.
     */
    public Atributos getAtributos(){
        if(editar) // Devolvemos el obj atributos del shape
            if(this.currentShape != null)
                return this.currentShape.getAtributos();
            else
                return null;
        else
            return this.atributosActual;
    }
    
    /**
     * Método que se activa cuando el usuario presiona con el ratón sobre el lienzo.
     * Si la opción editar figura está activa, se obtiene la figura que quiere seleccionar
     * el usuario, y calculamos el punto donde se quiere mover dicha figura.
     * En caso de que la opción editar no esté activada se crea la figura (en el caso de rectángulo,
     * linea, curva, punto, polígono y elipse) o se modifica la curva o polígono en función del paso
     * en el que se encuentre la figura.
     * @param evt 
     */
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed

        if(editar){ // Opción editar activada
            // Obtenemos el shape seleccionado
            this.currentShape = this.getSelectedShape(evt.getPoint());
            if(this.currentShape != null){
                // Obtenemos la localización de la figura a modificar
                Point2D puntoAux = this.currentShape.getLocation();
                // Calculamos la diferencia entre el punto de la figura y el punto donde
                // se quiere mover la figura.
                this.punt = new Point2D.Double(puntoAux.getX() - evt.getPoint().x, 
                            puntoAux.getY() - evt.getPoint().y);
                

            }
        }
        else{
            if(this.currentShape != null){ // Si la figura ha sido creada
                // Estamos en el paso 1 y opción de curva.
                if(step == 1 && this.currentShape.getTipo() == Figura.CURVA){
                    // Almacenamos el punto final de la curva.
                    ((Curva2D)this.currentShape).setPuntoFinal(evt.getPoint());
                    this.step++;
                }
                // Estamos con al figura polígono creada.
                else if(this.currentShape.getTipo() == Figura.POLIGONO){
                    // Añadimos un punto al polígono
                    ((Poligono2D)this.currentShape).setPunto(evt.getPoint());
                    this.step++;
                }
                
            }

            else{ // Creamos la figura.
                this.createShape(evt.getPoint());
                this.step++;
            }
        }
        repaint();
    }//GEN-LAST:event_formMousePressed

    /**
     * Método que se activa cuando el usuario tiene presionado el rato y está "arrastrando"
     * por el lienzo el puntero. Actualiza los valores de las figuras con el punto donde
     * se encuentra en ese momento el ratón.
     * @param evt 
     */
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if(! this.editar){ // Opción editar desactivada.
            if(this.currentShape.getTipo() == Figura.CURVA){ // Figura curva
                if(this.step == 1) // Paso 1 actualizamos el punto final de la curva
                    ((Curva2D)this.currentShape).setPuntoFinal(evt.getPoint());
                else // Estamos en el paso 2, punto de control de la curva
                    ((Curva2D)this.currentShape).setPuntoControl(evt.getPoint());
            }
            // Actualizamos el último punto del polígono.
            else if(this.currentShape.getTipo() == Figura.POLIGONO){
                ((Poligono2D)this.currentShape).setPunto(evt.getPoint());
            }
            else // Actualizamos las demás figuras.
                this.updateShape(evt.getPoint());
        }
        else // Opción editar activada, modificamos la figura seleccionada.
            this.updateShape(evt.getPoint());
        
        repaint();
            
        
        
    }//GEN-LAST:event_formMouseDragged

    /**
     * Método que se activa cuando el usuario suelta el botón del ratón.
     * Si la opción editar no está activada, normalmente ponemos los valores por defecto
     * currentShape a null y step a 0, excepto en polígono que se añade un punto más
     * a la figura polígono.
     * @param evt 
     */
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if(! this.editar){ // No estamos editando
            if(this.currentShape.getTipo() == Figura.CURVA){ // Figura curva
                if(this.step == 1) // Paso 1
                    this.step++; // vamos al segundo paso
                else{ // Valores por defecto.
                    this.step=0;
                    this.currentShape=null;
                }
            }
            // Opción polígono
            else if(this.currentShape.getTipo()==Figura.POLIGONO){
                // Añadimos el punto.
                ((Poligono2D)this.currentShape).addPunto(evt.getPoint());
                this.step++; // Siguiente paso
            }
            else{ // Valores por defecto.
                this.step=0;
                this.currentShape=null;
            }
        }
        else
            this.punt = null;
    }//GEN-LAST:event_formMouseReleased

    /**
     * Método que se activa cuando el usuario hace click sobre el lienzo.
     * Solo lo utilizamos cuando el usuario hace doble click en la figura polígono,
     * entonces cerramos la creación del polígono.
     * @param evt 
     */
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
       
        if(!this.editar){ // No estamos editando
            if(evt.getClickCount() == 2){ // Doble click
                // Es el polígono.
                if(this.currentShape != null && this.currentShape.getTipo() == Figura.POLIGONO){
                    this.step=0; // El paso lo ponemos a 0
                    // Añadimos el punto donde se hace doble click
                    ((Poligono2D)this.currentShape).addPunto(evt.getPoint());
                    this.currentShape = null; // Figura actual a null.
                }
            }
        }
    }//GEN-LAST:event_formMouseClicked

    /**
     * Método que actualiza la opción editar del lienzo.
     * @param editar indica si está activa la opción editar.
     */
    public void setEditar(boolean editar){
        this.editar=editar;
        if(this.editar == false)
            this.currentShape=null; // Figura a null en el caso de que no se quiera editar.
    }
    
    /**
     * 
     * @return Devuelve si el lienzo está en opción editar. 
     */
    public boolean getEditar(){
        return this.editar;
    }

    /**
     * 
     * @return Devuelve el tipo de figura seleccionada. 
     */
    public int getFigura(){
        return this.figura;
    }

    /**
     * Método que actualiza la figura seleccionada.
     * @param opc indica la figura seleccionada.
     */
    public void setFigura(int opc){
        this.figura=opc;
        this.currentShape=null;
    }


    /**
     * Método que actualiza la figura que se está creando/editando en ese momento.
     * @param punto indica la coordenada a añadir/editar de la figura.
     */
    private void updateShape(Point2D punto){
        
        if(this.editar){ // Opción editar
            if(this.currentShape != null){ // Si hay una figura seleccionada
                Point2D puntoActualizado;
                // Obtenemos el punto nuevo donde ha sido desplazada la figura.
                puntoActualizado = new Point2D.Double(punto.getX() + this.punt.getX(),
                            punto.getY() + this.punt.getY()); 
                // Actualizamos la localización de la figura.
                this.currentShape.setLocation(puntoActualizado);
  
            }
                
            
        }
        else{ // Añadimos el segundo punto a la figura.
            this.currentShape.setP2(punto);
        }
        repaint();
    }
    
   /**
    * Método para crear una figura seleccionada.
    * @param p coordenada inicial donde se encuentra la figura.
    */
    private void createShape(Point2D p){
        
        if(! this.editar){ // No estamos editando
            // Clonamos los atributos actuales para posteriormente añadirselos a la figura.
            // Clonamos para que la figura creada sea la única poseedora de una instancia atributos.
           Atributos atributos = this.atributosActual.clone();
     
            switch(this.figura){
                case Lienzo2D.PUNTO: // Figura punto
                    this.currentShape = new Punto2D(p);
                    atributos.setLineaDiscontinua(false);
                    this.currentShape.setAtributos(atributos);
                    this.misFiguras.add(this.currentShape); // Añadimos al vector
                    break;
                case Lienzo2D.LINEA: // Figura linea
                    this.currentShape = new Linea2D(p, p);
                    this.currentShape.setAtributos(atributos);
                    this.misFiguras.add(this.currentShape);
                    break;
                case Lienzo2D.RECTANGULO: //Figura rectángulo
                    this.currentShape = new Rectangulo2D(p, 0, 0);
                    ((Rectangulo2D)this.currentShape).setAtributos(atributos);
                    this.misFiguras.add(this.currentShape);
                    break;
                case Lienzo2D.ELIPSE: // Figura elipse.
                    this.currentShape = new Elipse2D(p, 0, 0);
                    this.currentShape.setAtributos(atributos);
                    this.misFiguras.add(this.currentShape);
                    break;
                case Lienzo2D.POLIGONO: // Figura polígono.
                    this.currentShape = new Poligono2D();
                    this.currentShape.setAtributos(atributos);
                    this.misFiguras.add(this.currentShape);
                    break;
                case Lienzo2D.CURVA: // Figura curva.
                    this.currentShape = new Curva2D(p,p,p);
                    this.currentShape.setAtributos(atributos);
                    this.misFiguras.add(this.currentShape);
                    break;
            }
        
        repaint();
        
        }
    }
    
    /**
     * Método en el cual dibuja en el lienzo las figuras creadas hasta ese momento.
     * @param g 
     */
    @Override
    public void paint(Graphics g){
        
        super.paint(g); 
        // Indicamos la parte del lienzo donde se puede dibujar.
        Rectangle2D rect = new Rectangle2D.Float();
        rect.setRect(1, 1, ancho, alto);
        
        
        Graphics2D g2d = (Graphics2D)g;
        // Añadimos al Graphics2D la parte donde se puede dibujar.
        g2d.clip(rect);
        
        // Dibujamos las figuras
        for(Figura s:misFiguras) {
            // Llamamos la método pintar de la figura.
            s.pintar(g2d);
            // Si estamos editando y la figura de edición se corresponde con la figura
            // que toca dibujar en ese momento, dibujamos el rectangulo indicando que
            // esa figura actual está seleccionada para su edición.
            if(this.editar && this.currentShape == s){
                // Obtenemos el rectangulo donde se encuentra la figura.
                Rectangle2D r = s.getBounds2D();
                // Obtenemos la esquina superior izquierda y la "agrandamos un poco"
                Point2D p = new Point2D.Double(r.getX()-4, r.getY()-4);
                Rectangulo2D rec = new Rectangulo2D(p, (int) r.getWidth()+8,(int) r.getHeight()+8);
                // Dibujamos el rectángulo alrededor de la figura seleccionada con linea discontinua
                // de color negro.
                g2d.setPaint(Color.black);
                float tam[] = {7};
                g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 5.5f, tam, 0.0f));
                g2d.draw(rec);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
