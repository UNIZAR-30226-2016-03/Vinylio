package com.eina.as.modelo.service;

import java.util.Date;

/**
 * Created by lenovo on 14/04/2016.
 */
public class Vinilo {
    private int idVinilo;
    private String titulo, autor, genero;
    private int fecha, RPM;
    private String discografica, imagen, numLanzamiento;

    public Vinilo(int idVinilo, String titulo, String autor, String genero, int fecha, String discografica, String imagen,
                  int RPM, String numLanzamiento) {
        this.idVinilo = idVinilo;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.fecha = fecha;
        this.discografica = discografica;
        this.imagen = imagen;
        this.RPM = RPM;
        this.numLanzamiento = numLanzamiento;
    }

    public Vinilo(String titulo, String autor, String genero, int fecha, String discografica, String imagen,
                  int RPM, String numLanzamiento) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.fecha = fecha;
        this.discografica = discografica;
        this.imagen = imagen;
        this.RPM = RPM;
        this.numLanzamiento = numLanzamiento;
    }

    public Vinilo(String titulo, String autor, String imagen){
        this.titulo = titulo;
        this.autor = autor;
        this.imagen = imagen;
    }

    public int getIdVinilo() {
        return idVinilo;
    }

    public void setIdVinilo(int idVinilo) {
        this.idVinilo = idVinilo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public String getDiscografica() {
        return discografica;
    }

    public void setDiscografica(String discografica) {
        this.discografica = discografica;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getRPM() {
        return RPM;
    }

    public void setRPM(int RPM) {
        this.RPM  = RPM;
    }

    public String getNumLanzamiento() {
        return numLanzamiento;
    }

    public void setNumLanzamiento(String numLanzamiento) {
        this.numLanzamiento= numLanzamiento;
    }
}
