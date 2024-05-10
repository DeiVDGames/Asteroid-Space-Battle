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
 * @author Beca98
 */
public class Asteroide extends Personaje {

    public Asteroide(Coordenada localizacion, String urlImagen, Boolean visible, Boolean estado) {
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

}
