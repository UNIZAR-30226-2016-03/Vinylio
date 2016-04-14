package com.eina.as.modelo.service;

public class Usuario {

    private String nombreApellidos, email, password, URL_foto, biografia,lugar;

    private int id_usuario,nacimiento;
    public Usuario(int id_usuario, int nacimiento, String nombreApellidos, String email, String password, String URL_foto,
                   String biografia, String lugar) {
        this.id_usuario = id_usuario;
        this.nombreApellidos = nombreApellidos;
        this.email = email;
        this.password = password;
        this.URL_foto = URL_foto;
        this.nacimiento = nacimiento;
        this.lugar = lugar;
        this.biografia = biografia;
    }

    public Usuario(String nick, String correo, String password, String nombreApellidos, String urlImagen) {
        this.id_usuario = id_usuario;
        this.correo = correo;
        this.password = password;
        this.nombreApellidos = nombreApellidos;
        this.urlImagen = urlImagen;
    }

    public String getNick() {

        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getlugar() {
        return lugar;
    }

    public void setlugar(String lugar) {
        this.lugar = lugar;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getUrlTwitter() {
        return urlTwitter;
    }

    public void setUrlTwitter(String urlTwitter) {
        this.urlTwitter = urlTwitter;
    }

    public String getUrlFacebook() {
        return urlFacebook;
    }

    public void setUrlFacebook(String urlFacebook) {
        this.urlFacebook = urlFacebook;
    }

    public String getUrlPersonal() {
        return urlPersonal;
    }

    public void setUrlPersonal(String urlPersonal) {
        this.urlPersonal = urlPersonal;
    }
}
