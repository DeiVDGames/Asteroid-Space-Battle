/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author eumartinez
 */
public class Nave extends Personaje {

    public Nave(Coordenada localizacion, String urlImagen, Boolean visible, Boolean estado) {
        super(localizacion, urlImagen, visible, estado);
    }

    @Override
    public void dibujar(Graphics escenario) {

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(this.getUrlImagen()));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        int posX = this.getLocalizacion().getX();
        int posY = this.getLocalizacion().getY();
        escenario.drawImage(img, posX, posY, null);
    }

        private boolean activo = true; // Variable para controlar si la nave está activa o no

    // Método para establecer el estado activo o inactivo de la nave
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    
}
