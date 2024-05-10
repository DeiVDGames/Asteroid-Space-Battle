/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Graphics;
import logica.Asteroide;
import logica.Coordenada;
import logica.Misil;
import logica.Nave;

/**
 *
 * @author eumartinez
 */
public class Juego {

    private Nave nave; //Solo vamos a necesitar una nave 
    private Asteroide asteroides[] = new Asteroide[4]; //Array de asteroides 
    private Misil misiles[] = new Misil[5]; //Array de misiles 
    private Graphics escenario; 
    private int ancho;
    private int alto;
    private int puntaje=0;
    private int vidas=3;
    private int tiempo=0;

    public Juego(Graphics escenario, int ancho, int alto) {
        this.escenario = escenario;
        this.ancho = ancho;
        this.alto = alto;

        //creamos la nave 
        Coordenada cordNave = new Coordenada(200, 500); //coordenada inicial de la nave
        nave = new Nave(cordNave, "Imagenes/nave2.png", true, true);

        //Creamos los asteroides que vamos a usar en el juego 
        for (int i = 0; i < asteroides.length; i++) {

            Coordenada cordAsteroide = new Coordenada(aleatorioX(), aleatorioY());

            this.asteroides[i] = new Asteroide(cordAsteroide, "Imagenes/asteroide.png", true, true);

        }

        //Creamos los misiles que va ha disparar la nave 
        Coordenada cordMisil = new Coordenada(450, 450);

        for (int i = 0; i < misiles.length; i++) {

            this.misiles[i] = new Misil(cordMisil, "Imagenes/misil.png", true, true);

        }    
    }
    
    
    




    
//-----------------------------------------------------------------------------------------------------------------------
    //Método para asignar una nueva coordenada al misil en el eje X dependiendo de la posicion de la nave
    public void newCoordenadaMisil(int i) {
        int posX = this.nave.getLocalizacion().getX();
        Coordenada newCordMisil = new Coordenada(posX+15, 450);
        this.misiles[i].setLocalizacion(newCordMisil);

    }
//-----------------------------------------------------------------------------------------------------------------------
    //Método para disparar el misil 

    public void dispararMisil() {

        for (int i = 0; i < misiles.length; i++) {

            int posX = this.misiles[i].getLocalizacion().getX();
            int posY = this.misiles[i].getLocalizacion().getY();
            destruirAsteroides(i);
            this.misiles[i].mover(posX, posY - 20);
            destruirAsteroides(i);

        }

    }

//------------------------------------------------------------------------------------------------------------------------
    //Este método lo usamos para comparar la coordena del misil con la del asteroide, si son iguales eliminamos el asteroide
    public void destruirAsteroides(int i) {

    int posMisilX = this.misiles[i].getLocalizacion().getX();
    int posMisilY = this.misiles[i].getLocalizacion().getY();

    for (int j = 0; j < asteroides.length; j++) {
        int posAsteroideX = this.asteroides[j].getLocalizacion().getX();
        int posAsteroideY = this.asteroides[j].getLocalizacion().getY();

        // Verificar si el misil alcanza la misma fila que el asteroide
        if (posMisilY <= posAsteroideY && posMisilY >= posAsteroideY - 10) {
            this.asteroides[j].mover(aleatorioX(), aleatorioY() - 150); // reposiciona los asteroides
            Puntaje(100);
            }
        }
     
    }
//-----------------------------------------------------------------------------------------------------------------------        
    public void destruirNave(int posAsteroideX){
        
        //la posicion de la nave en Y siempre es la misma 360
        
        int posNaveX;
        
        posNaveX = this.nave.getLocalizacion().getX();
       
        if (posNaveX == posAsteroideX || posNaveX == posAsteroideX-20){
            
            Vidas();
        }
        
    }
//-----------------------------------------------------------------------------------------------------------------------       

    //Método para mostrar los misile en pantalla 
    public void mostrarMisiles() {
        for (int i = 0; i < misiles.length; i++) {
            this.misiles[i].dibujar(escenario);
        }
    }
//------------------------------------------------------------------------------------------------------------------------    
    //Método copia del Método "mostrarMisiles"

    public void dibujarAsteroides() {
        for (int i = 0; i < asteroides.length; i++) {

            this.asteroides[i].dibujar(escenario);

        }
    }
//-----------------------------------------------------------------------------------------------------------------------
    //Método para mostrar los asteroides de formar aleatoria 

    public void mostrarAsteroides() {

        for (int i = 0; i < asteroides.length; i++) {

            this.asteroides[i].dibujar(escenario);

        }

    }
//------------------------------------------------------------------------------------------------------------------------    
    //Método para mover los asteroides 

    public void moverAsteroides() {

          if (!juegoActivo) return; // Si el juego está inactivo, no mover los asteroides

        
    for (int i = 0; i < asteroides.length; i++) {
        int posX = this.asteroides[i].getLocalizacion().getX();
        int posY = this.asteroides[i].getLocalizacion().getY();

        this.asteroides[i].mover(posX, posY + 10);
        this.dibujar();

        // Si el asteroide llega al límite inferior de la pantalla, lo reposiciona en la parte superior
        if (posY >= alto) {
            this.asteroides[i].setLocalizacion(new Coordenada(aleatorioX(), aleatorioY() - 150));
        }

        // Verificar colisión con la nave si el asteroide está en la parte inferior
        if (posY == 500) {
            destruirNave(posX);
        }
    
        }

    }





    
//-----------------------------------------------------------------------------------------------------------------------    
    //Método dibujar 
    public void dibujar() {

        this.nave.dibujar(escenario);
       // this.misil.dibujar(escenario);

    }

//-----------------------------------------------------------------------------------------------------------------------
    //Método para mover la nave
    public void moverNave(int i) {

        int posX = this.nave.getLocalizacion().getX();
        int posY = this.nave.getLocalizacion().getY();
        int newPosX = posX + i;
        if (newPosX >= 0 && newPosX <= 400) {
            this.nave.mover(posX+i, 500);  
            this.dibujar();
        }

    }
//------------------------------------------------------------------------------------------------------------------------    
    
    public int numeroMisiles() {

        int nMisiles = this.misiles.length;

        return nMisiles;
    }

//------------------------------------------------------------------------------------------------------------------------    
    public static int aleatorioX() {

        int VectorEjeX[] = {20,40,60,80,100,120,140,160,180,200,220,240,260,280,300,320,340,360,380};

        int aleatorio = (int) (Math.random() * 19);

        int cordX = VectorEjeX[aleatorio];

        return cordX;

    }
//-------------------------------------------------------------------------------------------------------------------------
    public static int aleatorioY() {

        int cordY = (int) (Math.random() * 50) + 10;

        return cordY * 2;

    }
//--------------------------------------------------------------------------------------------------------------------------    
    /*
    public void cronometro() throws InterruptedException {

        
        tiempo++;
            
       
       //Thread.sleep(1000);
    
    }
    */
//----------------------------------------------------------------------------------------------------------------------- 
    public void Vidas(){
        
        
        this.vidas--;
        
    }
  
//-----------------------------------------------------------------------------------------------------------------------    
    private boolean juegoTerminado = false; // Variable para controlar si el juego ya se detuvo o no
    private boolean juegoActivo = true;
    
public void Puntaje(int puntaje) {
    this.puntaje = puntaje + this.puntaje;

    // Verificar si la puntuación alcanzó 10000 para detener el juego
    if (this.puntaje >= 50000 && !juegoTerminado) {
        detenerJuego();
    }
}

    private void detenerJuego() {
        juegoActivo = false; // Establecer el juego como inactivo

        // Mostrar un mensaje de "Juego terminado"
        System.out.println("Juego terminado!");
        System.out.println("Gracias por jugar.");

        // Desactivar la capacidad de disparar misiles
        for (int i = 0; i < misiles.length; i++) {
            misiles[i].setActivo(false);
        }

        // Desactivar la capacidad de mover la nave
        nave.setActivo(false);
    }

    
//-----------------------------------------------------------------------------------------------------------------------
   
    
    
    public int getTiempo() {
        return tiempo;
    }
    
    public int getPuntaje() {
        return puntaje;
    }

    public int getVidas() {
        return vidas;
    }


    
    
}
