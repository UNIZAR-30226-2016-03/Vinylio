package com.eina.as.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Fran Menendez Moya on 5/4/16.
 */
@Controller
public class ControladorPerfil {

    @RequestMapping(value="/perfil")
    public String redireccionPerfil(){
        System.out.println("Me ha llegado la peticion de obtener perfil");
        return "perfil";
    }
}
