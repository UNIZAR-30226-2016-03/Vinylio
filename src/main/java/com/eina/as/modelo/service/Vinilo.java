package com.eina.as.modelo.service;

import java.util.Date;

/**
 * Created by lenovo on 14/04/2016.
 */
public class Vinilo {
    private int idVinilo;
    private String titulo, autor, genero;
    private Date fecha;
    private String discografica,imagen;
    private int rpm;
    private Date lanzamiento;

    public Vinilo(int idVinilo, String titulo, String autor, String genero, Date fecha, String discografica, String imagen, int rpm, Date lanzamiento) {
        this.idVinilo = idVinilo;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.fecha = fecha;
        this.discografica = discografica;
        this.imagen = imagen;
        this.rpm = rpm;
        this.lanzamiento = lanzamiento;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public Date getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(Date lanzamiento) {
        this.lanzamiento = lanzamiento;
    }
}
