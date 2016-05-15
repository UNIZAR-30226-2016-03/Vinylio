package com.eina.as.controladores;

import com.eina.as.modelo.dataAccess.DAOVinilo;
import com.eina.as.modelo.service.Usuario;
import com.eina.as.modelo.service.Vinilo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Controller
public class ControladorColeccion {

    // lo de value /catalogo que hace en verdad? Si cargo directamente la pagina catalogo.jsp cargaría este metodo?
    // Lo que creo casi seguro que hace es que si lo llamas con /catalogo o /catalogo2 va a hacerte una cosa de estas?

    @RequestMapping(value= "/coleccion")
    public String redireccionPerfil(HttpServletRequest request/*, HttpServletResponse response*/)
            throws Exception{
        request.removeAttribute("numPagina");
        DAOVinilo vin = new DAOVinilo();
        ArrayList <Vinilo> listaVinilos= vin.getListaVinilos(0);
        int numVinilos = vin.getNumeroVinilos();
        request.setAttribute("numVinilos", numVinilos);
        request.setAttribute("listaVinilos", listaVinilos);
        request.setAttribute("numPagina", 1);
        //  RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
        //  dispatcher.forward(request,response);
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/home";
        } else {
            return "coleccion";
        }

    }


    @RequestMapping(value= "/coleccion2")
    public String redireccionPerfil2(HttpServletRequest request/*, HttpServletResponse response*/)
            throws Exception{
        String sPagina = (String) request.getAttribute("numPagina");
        int numPagina;
        if ((sPagina==null)|| (sPagina.trim().equals(""))) {
            numPagina = 0;
        }
        else{
            numPagina = 1 + Integer.parseInt(sPagina); // tiene que ser cuando haces el "Ver 25 más" que aumente el
            request.setAttribute("numPagina", numPagina);                                            // numPagina.
        }
        DAOVinilo vin = new DAOVinilo();
        int numVinilos = vin.getNumeroVinilos();
        request.setAttribute("numVinilos", numVinilos);
        ArrayList <Vinilo> listaVinilos= vin.getListaVinilos(numPagina);
        request.setAttribute("listaVinilos", listaVinilos);
        //  RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
        //  dispatcher.forward(request,response);
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/home";
        } else {
            return "catalogo";
        }

    }


}


// comprobando que me haga algo el puto push