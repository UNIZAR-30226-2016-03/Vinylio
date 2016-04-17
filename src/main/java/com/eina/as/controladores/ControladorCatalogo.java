package com.eina.as.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ControladorCatalogo {

    @RequestMapping(value= "/catalogo")
    public String redireccionPerfil(){
        System.out.println("Me ha llegado la peticion de mostrar catalogo");
        return "catalogo";
    }
}


// comprobando que me haga algo el puto push