package com.eina.as.controladores;

import com.eina.as.modelo.dataAccess.DAOColeccion;
import com.eina.as.modelo.dataAccess.DAOVinilo;
import com.eina.as.modelo.service.Usuario;
import com.eina.as.modelo.service.Vinilo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ControladorPrincipal {

    @RequestMapping(value="/timeline")
    public String redireccionTimeline(HttpServletRequest request) throws Exception{
        System.out.println("Me ha llegado la peticion de obtener timeline");
        Usuario user = (Usuario) request.getSession().getAttribute("user");



        if(user == null) {
            return "redirect:/home";
        } else{
            System.out.println(user.getEmail());
            DAOVinilo vin = new DAOVinilo();
            DAOColeccion coleccion = new DAOColeccion();
            ArrayList<Vinilo> listaVinilos= vin.getListaVinilos(0);
            ArrayList<Vinilo> coleccionVinilos= coleccion.getListaVinilos(user);
            request.setAttribute("listaVinilos", listaVinilos);
            request.setAttribute("coleccionVinilos",coleccionVinilos);
            return "principal";
        }
    }

    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request){
        System.out.println("Me ha llegado la peticion de logout");
        request.getSession().invalidate();
        return "redirect:/home";
    }
}
