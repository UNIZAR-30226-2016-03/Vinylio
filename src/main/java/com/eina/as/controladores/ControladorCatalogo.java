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
        int numVinilos = vin.getNumeroVinilos();
        request.setAttribute ("numVinilos", numVinilos);
        request.setAttribute("listaVinilos", listaVinilos);
        request.getSession().setAttribute("numPagina","1");
        //  RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
        //  dispatcher.forward(request,response);
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/home";
        } else {
            return "catalogo";
        }

    }


    @RequestMapping(value= "/catalogo2")
    public String redireccionPerfil2(HttpServletRequest request/*, HttpServletResponse response*/)
            throws Exception{
        String sPagina = (String) request.getSession().getAttribute("numPagina");

        int numPagina = Integer.parseInt(sPagina);
        System.out.println("numPagina= "+ numPagina);

        if ((sPagina==null)|| (sPagina.trim().equals("")) || sPagina.trim().equals("0")) {
            numPagina = 0;
        }
        else{
            numPagina++; // tiene que ser cuando haces el "Ver 25 más" que aumente el numPagina.
            sPagina = Integer.toString(numPagina);
            request.getSession().setAttribute("numPagina",sPagina);
        }


        DAOVinilo vin = new DAOVinilo();
        int numVinilos = vin.getNumeroVinilos();
        request.setAttribute("numVinilos", numVinilos);
        ArrayList <Vinilo> listaVinilos;
        if (numPagina==0){
            listaVinilos= vin.getListaVinilos(0);
        }
        else {
            listaVinilos = vin.getListaVinilos(numPagina - 1);
        }
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