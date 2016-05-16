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

    @RequestMapping(value = "/coleccion")
    public String redireccionPerfil(HttpServletRequest request/*, HttpServletResponse response*/)
            throws Exception {
        request.removeAttribute("numPaginaC");
        DAOVinilo vin = new DAOVinilo();
        ArrayList<Vinilo> listaVinilos = vin.getListaVinilos(0);
        int numVinilos = vin.getNumeroVinilos();
        request.setAttribute("numVinilos", numVinilos);
        request.setAttribute("listaVinilos", listaVinilos);
        request.getSession().setAttribute("numPaginaC", "1");
        //  RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
        //  dispatcher.forward(request,response);
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/home";
        } else {
            return "coleccion";
        }

    }


    @RequestMapping(value = "/coleccion2")
    public String redireccionPerfil2(HttpServletRequest request/*, HttpServletResponse response*/)
            throws Exception {
        String sPagina = (String) request.getSession().getAttribute("numPaginaC");

        int numPaginaC = Integer.parseInt(sPagina);
        System.out.println("numPaginaC= " + numPaginaC);

        if ((sPagina == null) || (sPagina.trim().equals("")) || sPagina.trim().equals("0")) {
            numPaginaC = 0;
        } else {
            numPaginaC++; // tiene que ser cuando haces el "Ver 25 más" que aumente el numPaginaC.
            sPagina = Integer.toString(numPaginaC);
            request.getSession().setAttribute("numPaginaC", sPagina);
        }


        DAOVinilo vin = new DAOVinilo();
        int numVinilos = vin.getNumeroVinilos();
        request.setAttribute("numVinilos", numVinilos);
        ArrayList<Vinilo> listaVinilos;
        if (numPaginaC == 0) {
            listaVinilos = vin.getListaVinilos(0);
        } else {
            listaVinilos = vin.getListaVinilos(numPaginaC - 1);
        }
        request.setAttribute("listaVinilos", listaVinilos);
        //  RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
        //  dispatcher.forward(request,response);
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/home";
        } else {
            return "coleccion";
        }


    }
}


// comprobando que me haga algo el puto push