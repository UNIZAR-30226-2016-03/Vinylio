package com.eina.as.controladores;

import com.eina.as.modelo.dataAccess.DAOUsuario;
import com.eina.as.modelo.dataAccess.DAOVinilo;
import com.eina.as.modelo.service.Password;
import com.eina.as.modelo.service.Usuario;
import com.eina.as.modelo.service.Vinilo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@Controller
public class ControladorCatalogo {

    // lo de value /catalogo que hace en verdad? Si cargo directamente la pagina catalogo.jsp cargaría este metodo?
    // Lo que creo casi seguro que hace es que si lo llamas con /catalogo o /catalogo2 va a hacerte una cosa de estas?

    @RequestMapping(value= "/catalogo")
    public String redireccionPerfil(HttpServletRequest request/*, HttpServletResponse response*/)
            throws Exception{
        request.removeAttribute("numPagina");
        DAOVinilo vin = new DAOVinilo();
        ArrayList <Vinilo> listaVinilos= vin.getListaVinilos(0);

        request.setAttribute("listaVinilos", listaVinilos);
        request.setAttribute("numPagina", 1);
        //  RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
        //  dispatcher.forward(request,response);
        return "catalogo";
    }


    @RequestMapping(value= "/catalogo2")
    public String redireccionPerfil2(HttpServletRequest request/*, HttpServletResponse response*/)
            throws Exception{
        String sPagina = (String) request.getAttribute("numPagina");
        System.out.println(sPagina);
        int numPagina;
        if ((sPagina==null)|| (sPagina.trim().equals(""))) {
            numPagina = 0;
        }
        else{
            numPagina = 1 + Integer.parseInt(sPagina); // tiene que ser cuando haces el "Ver 25 más" que aumente el
            request.setAttribute("numPagina", numPagina);                                            // numPagina.
        }
        DAOVinilo vin = new DAOVinilo();
        System.out.println(numPagina);
        ArrayList <Vinilo> listaVinilos= vin.getListaVinilos(numPagina);
        request.setAttribute("listaVinilos", listaVinilos);
        //  RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
        //  dispatcher.forward(request,response);
        return "catalogo";
    }


}


// comprobando que me haga algo el puto push