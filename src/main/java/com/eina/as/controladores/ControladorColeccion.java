package com.eina.as.controladores;

import com.eina.as.modelo.dataAccess.DAOVinilo;
import com.eina.as.modelo.dataAccess.DAOColeccion;
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
public class ControladorColeccion {


    // lo de value /catalogo que hace en verdad? Si cargo directamente la pagina catalogo.jsp cargaría este metodo?
    // Lo que creo casi seguro que hace es que si lo llamas con /catalogo o /catalogo2 va a hacerte una cosa de estas?

    @RequestMapping(value = "/coleccion")
    public String redireccionColeccion(HttpServletRequest request/*, HttpServletResponse response*/)
            throws Exception {
        System.out.println("Me ha llegado la peticion de coleccion");
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        request.getSession().setAttribute("tipoOrdenacion", "titulo");
        String resultado = "naa";
        request.getSession().setAttribute("resultadoEliminar",resultado);

        if (user == null) {
            return "redirect:/home";
        } else {
            request.removeAttribute("numPaginaC");
            DAOColeccion coleccion = new DAOColeccion();
            ArrayList<Vinilo> listaVinilos = coleccion.getListaVinilos(user,0, "titulo");
            int numVinilos = coleccion.getNumeroVinilos(user);
            request.setAttribute("numVinilos", numVinilos);
            request.setAttribute("listaVinilos", listaVinilos);
            request.getSession().setAttribute("numPaginaC", "1");
            //  RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
            //  dispatcher.forward(request,response);
            return "coleccion";
        }

    }


    // metodo que va a cambiar el ordenar de valor y te redirecciona a la primera pantalla con los vinilos ordenados
    // segun le hayas dicho.
    @RequestMapping(value= "/coleccionOtroOrden", method=RequestMethod.POST)
    public String redireccionCatalogo(@RequestParam("ordenacionDesplegable") String orden,
                                      HttpServletRequest request) throws Exception{
        System.out.println("El orden elegido es: " + orden );
        System.out.println("Me ha llegado la peticion de coleccion");
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        String resultado = "naa";
        request.getSession().setAttribute("resultadoEliminar",resultado);
        if (user == null) {
            return "redirect:/home";
        } else {
            request.getSession().setAttribute("tipoOrdenacion", "fecha");
            request.removeAttribute("numPaginaC");
            DAOColeccion coleccion = new DAOColeccion();
            ArrayList<Vinilo> listaVinilos = coleccion.getListaVinilos(user,0, orden);
            int numVinilos = coleccion.getNumeroVinilos(user);
            request.setAttribute("numVinilos", numVinilos);
            request.setAttribute("listaVinilos", listaVinilos);
            request.getSession().setAttribute("numPaginaC", "1");
            //  RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
            //  dispatcher.forward(request,response);
            return "coleccion";
        }

    }

    @RequestMapping(value = "/coleccionr")
    public String redireccionColeccionr(HttpServletRequest request/*, HttpServletResponse response*/)
            throws Exception {
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        String orden = (String) request.getSession().getAttribute("tipoOrdenacion");

        if (user == null) {
            return "redirect:/home";
        } else {
            request.removeAttribute("numPaginaC");
            DAOColeccion coleccion = new DAOColeccion();
            ArrayList<Vinilo> listaVinilos = coleccion.getListaVinilos(user,0, orden);
            int numVinilos = coleccion.getNumeroVinilos(user);
            request.setAttribute("numVinilos", numVinilos);
            request.setAttribute("listaVinilos", listaVinilos);
            request.getSession().setAttribute("numPaginaC", "1");
            //  RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
            //  dispatcher.forward(request,response);
            return "coleccion";
        }

    }


    @RequestMapping(value = "/coleccion2")
    public String redireccionPerfil2(HttpServletRequest request/*, HttpServletResponse response*/)
            throws Exception {
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/home";
        } else {
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
            DAOColeccion vin = new DAOColeccion();
            int numVinilos = vin.getNumeroVinilos(user);
            request.setAttribute("numVinilos", numVinilos);
            ArrayList<Vinilo> listaVinilos;
            String orden = (String) request.getSession().getAttribute("tipoOrdenacion");
            if (numPaginaC == 0) {
                listaVinilos = vin.getListaVinilos(user, 0, orden);
            } else {
                listaVinilos = vin.getListaVinilos(user, numPaginaC-1, orden);
            }
            request.setAttribute("listaVinilos", listaVinilos);
            //  RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
            //  dispatcher.forward(request,response);
            return "coleccion";

        }
    }

    @RequestMapping(value="/eliminarVinilo", method= RequestMethod.POST)
    public void anadirVinilo(@RequestParam("nombre") String id,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception{
        System.out.println("Me ha llegado la peticion de eliminar vinilo");
        System.out.println("primer" + id);
        Usuario user;
        Vinilo vin;
        DAOColeccion daoColeccion = new DAOColeccion();
        PrintWriter out = response.getWriter();
        String orden = (String) request.getSession().getAttribute("tipoOrdenacion");
        ArrayList<Vinilo> listaVins = (ArrayList<Vinilo>) request.getSession().getAttribute("listaVinilos");
        System.out.println("tamaño listavins "+ listaVins.size());
        user = (Usuario) request.getSession().getAttribute("user");
        int numVin = Integer.parseInt(id);
        vin = listaVins.get(numVin-1);
        System.out.println("titulitis " + vin.getTitulo());
        if(daoColeccion.existe(vin,user)){
            daoColeccion.delete(user,vin);
            System.out.println("exitico");
            String resultado = "exito";
            request.getSession().setAttribute("resultadoEliminar", resultado);
            out.println("exito");
        }
        else{
            String resultado = "fracaso";
            System.out.println("fracasico");
            request.getSession().setAttribute("resultadoEliminar", resultado);
            out.println("fracaso");
        }


    }
}


// comprobando que me haga algo el puto push