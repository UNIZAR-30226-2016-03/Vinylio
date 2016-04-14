package com.eina.as.modelo.service;

public class Usuario {

    private String nombreApellidos, email, password, URL_foto, biografia,lugar,nacimiento;
    public Usuario(String nacimiento, String nombreApellidos, String email, String password, String URL_foto,
                   String biografia, String lugar) {
        this.nombreApellidos = nombreApellidos;
        this.email = email;
        this.password = password;
        this.URL_foto = URL_foto;
        this.nacimiento = nacimiento;
        this.lugar = lugar;
        this.biografia = biografia;
    }

    public Usuario(String email, String password, String nombreApellidos, String URL_foto) {
        this.email = email;
        this.password = password;
        this.nombreApellidos = nombreApellidos;
        this.URL_foto = URL_foto;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlFoto() {
        return URL_foto;
    }

    public void setUrlFoto(String URL_foto) {
        this.URL_foto = URL_foto;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
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

}
