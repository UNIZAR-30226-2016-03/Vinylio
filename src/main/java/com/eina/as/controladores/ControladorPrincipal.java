package com.eina.as.controladores;

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
        DAOVinilo vin = new DAOVinilo();
        ArrayList<Vinilo> listaVinilos= vin.getListaVinilos(0);
        request.setAttribute("listaVinilos", listaVinilos);
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        System.out.println(user.getEmail());
        if(user == null) {
            return "redirect:/home";
        } else{
            return "principal";
        }
    }
}
