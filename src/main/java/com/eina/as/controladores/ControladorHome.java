package com.eina.as.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Fran Menendez Moya on 1/3/16.
 */
@Controller
public class ControladorHome {

    @RequestMapping(value="/home")
    public String redireccionHome(){
        System.out.println("Me ha llegado la peticion de obtener home");
        return "timeline";
    }

    @RequestMapping(value="/perfil")
    public String redireccionPerfil(){
        System.out.println("Me ha llegado la peticion de obtener perfil");
        return "perfil";
    }
}
