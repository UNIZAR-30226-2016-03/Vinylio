package com.eina.as.controladores;

import com.eina.as.modelo.dataAccess.DAOColeccion;
import com.eina.as.modelo.dataAccess.DAOUsuario;
import com.eina.as.modelo.service.Usuario;
import com.eina.as.modelo.service.Vinilo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;


@Controller
public class ControladorBusquedaColeccion {

    // lo de value /catalogo que hace en verdad? Si cargo directamente la pagina catalogo.jsp cargaría este metodo?
    // Lo que creo casi seguro que hace es que si lo llamas con /catalogo o /catalogo2 va a hacerte una cosa de estas?

    @RequestMapping(value="/buscar", method= RequestMethod.POST)
    public void buscar(@RequestParam("usernameB") String user,
                        HttpServletRequest request,
                        HttpServletResponse response) throws Exception{
        System.out.println("me ha llegado la peticion de busqueda coleccion");
        DAOUsuario daoUsuario = new DAOUsuario();
        request.removeAttribute("numPaginaB");
        System.out.println("No ha petado "+user);
        Usuario usuario;
        PrintWriter out = response.getWriter();
        usuario = daoUsuario.getUserEmail(user);
        // codigo de comportamiento si login o no login
        if(usuario != null){
            DAOColeccion vin = new DAOColeccion();
            ArrayList<Vinilo> listaVinilos = vin.getListaVinilos(usuario,0,"titulo");
            int numVinilos = vin.getNumeroVinilos(usuario);
            request.getSession().setAttribute("numVinilosB", numVinilos);
            request.getSession().setAttribute("listaVinilosB", listaVinilos);
            request.getSession().setAttribute("numPaginaB", "1");
            request.getSession().setAttribute("tipoOrdenacionB", "titulo");
            request.getSession().setAttribute("userB",usuario);
            System.out.println("Hasta aqui llega");
            out.println("exito");
        }
        else{
            System.out.println("volvemos");
            out.println("fracaso");
        }
    }


    @RequestMapping(value="/busquedaColeccion")
    public String busquedaColeccion(HttpServletRequest request) throws Exception{
        Usuario user = (Usuario) request.getSession().getAttribute("userB");
        if (user == null) {
            return "redirect:/home";
        } else {
            return "busquedaColeccion";
        }
    }

    @RequestMapping(value= "/busquedaColeccion2")
    public String busquedaColeccion2(HttpServletRequest request/*, HttpServletResponse response*/)
            throws Exception {
        Usuario user = (Usuario) request.getSession().getAttribute("userB");
        if (user == null) {
            return "redirect:/home";
        } else {
            String sPagina = (String) request.getSession().getAttribute("numPaginaB");
            int numPaginaC = Integer.parseInt(sPagina);
            System.out.println("numPaginaB= " + numPaginaC);
            if ((sPagina == null) || (sPagina.trim().equals("")) || sPagina.trim().equals("0")) {
                numPaginaC = 0;
            } else {
                numPaginaC++; // tiene que ser cuando haces el "Ver 25 más" que aumente el numPaginaC.
                sPagina = Integer.toString(numPaginaC);
                request.getSession().setAttribute("numPaginaB", sPagina);
            }
            DAOColeccion vin = new DAOColeccion();
            int numVinilos = vin.getNumeroVinilos(user);
            request.setAttribute("numVinilosB", numVinilos);
            ArrayList<Vinilo> listaVinilos;
            String orden = (String) request.getSession().getAttribute("tipoOrdenacionB");
            if (numPaginaC == 0) {
                listaVinilos = vin.getListaVinilos(user, 0, orden);
            } else {
                System.out.println(numPaginaC);
                listaVinilos = vin.getListaVinilos(user, numPaginaC-1, orden);
            }
            request.getSession().setAttribute("listaVinilosB", listaVinilos);
            //  RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
            //  dispatcher.forward(request,response);
            return "busquedaColeccion";

        }
    }
}