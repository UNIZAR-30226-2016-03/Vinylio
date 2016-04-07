package com.eina.as.modelo.service;

public class Usuario {

    private String nick, nombreApellidos, correo, password, urlImagen, fechaNacimiento, localizacion, biografia,
                         urlTwitter, urlFacebook, urlPersonal;

    public Usuario(String nick, String nombreApellidos, String correo, String password, String urlImagen,
                   String fechaNacimiento, String localizacion, String biografia, String urlTwitter,
                   String urlFacebook, String urlPersonal) {
        this.nick = nick;
        this.nombreApellidos = nombreApellidos;
        this.correo = correo;
        this.password = password;
        this.urlImagen = urlImagen;
        this.fechaNacimiento = fechaNacimiento;
        this.localizacion = localizacion;
        this.biografia = biografia;
        this.urlTwitter = urlTwitter;
        this.urlFacebook = urlFacebook;
        this.urlPersonal = urlPersonal;
    }

    public Usuario(String nick, String correo, String password, String nombreApellidos, String urlImagen) {
        this.nick = nick;
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

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
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
