package com.eina.as.controladores;

import com.eina.as.modelo.dataAccess.DAOBiblioteca;
import com.eina.as.modelo.dataAccess.DAOUsuario;
import com.eina.as.modelo.dataAccess.DAOVinilo;
import com.eina.as.modelo.service.Usuario;
import com.eina.as.modelo.service.Vinilo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Controller
public class ControladorBusquedaCatalogo {

    // lo de value /catalogo que hace en verdad? Si cargo directamente la pagina catalogo.jsp cargaría este metodo?
    // Lo que creo casi seguro que hace es que si lo llamas con /catalogo o /catalogo2 va a hacerte una cosa de estas?

    @RequestMapping(value="/busquedaCatalogo", method= RequestMethod.POST)
    public String login(@RequestParam("username") String user,
                        HttpServletRequest request) throws Exception{
        DAOUsuario daoUsuario = new DAOUsuario();
        DAOVinilo daoVinilo = new DAOVinilo();
        Usuario usuario;
        DAOBiblioteca biblioteca = new DAOBiblioteca();
        Vinilo vinilo;
        usuario = daoUsuario.getUserEmail(user);
        // codigo de comportamiento si login o no login
        if(usuario != null){
            request.getSession().setAttribute("user",usuario);
            return "redirect:/busquedaCatalogo";
        }
        else{
            return "redirect:/principal";
        }
    }


    @RequestMapping(value= "/busquedaCatalogo2")
    public String redireccionBusquedaPerfil2(HttpServletRequest request/*, HttpServletResponse response*/)
            throws Exception{
        String sPagina = request.getParameter("numPagina");
        int numPagina;
        if ((sPagina==null)|| (sPagina.trim().equals(""))) {
            numPagina = 0;
        }
        else{
            numPagina = 1 + Integer.parseInt(sPagina); // tiene que ser cuando haces el "Ver 25 más" que aumente el
            request.setAttribute("numPagina", numPagina);                                            // numPagina.
        }
        DAOVinilo vin = new DAOVinilo();
        ArrayList<Vinilo> listaVinilos= vin.getListaVinilos(numPagina);
        request.setAttribute("listaVinilos", listaVinilos);
        //  RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
        //  dispatcher.forward(request,response);
        return "catalogo";
    }
}


// comprobando que me haga algo el puto push