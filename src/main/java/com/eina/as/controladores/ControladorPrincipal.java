package com.eina.as.controladores;

import com.eina.as.modelo.service.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorPrincipal {

    @RequestMapping(value="/timeline")
    public String redireccionTimeline(HttpServletRequest request){
        System.out.println("Me ha llegado la peticion de obtener timeline");
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if(user != null) {
            return "timeline";
        } else{
            return "redirect:/home";
        }
    }
}
