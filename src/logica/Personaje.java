/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Graphics;

/**
 *
 * @author eumartinez
 */
public abstract class Personaje {

    private Coordenada localizacion;
    private String urlImagen;
    private Boolean visible;
    private Boolean estado;

    public abstract void dibujar(Graphics escenario);

    public void mover(int x, int y) {
        this.localizacion.setX(x);
        this.localizacion.setY(y);
    }

    public Personaje(Coordenada localizacion, String urlImagen, Boolean visible, Boolean estado) {
        this.localizacion = localizacion;
        this.urlImagen = urlImagen;
        this.visible = visible;
        this.estado = estado;
    }

    public Coordenada getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Coordenada localizacion) {
        this.localizacion = localizacion;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

}
