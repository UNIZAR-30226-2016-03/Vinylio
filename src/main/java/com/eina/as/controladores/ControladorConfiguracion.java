package com.eina.as.controladores;

import com.eina.as.modelo.dataAccess.DAOUsuario;
import com.eina.as.modelo.dataAccess.DAOVinilo;
import com.eina.as.modelo.service.Password;
import com.eina.as.modelo.service.Usuario;
import com.eina.as.modelo.service.Vinilo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ControladorConfiguracion {

    @RequestMapping(value = "/config")
    public String redireccionTimeline(HttpServletRequest request) throws Exception {
        System.out.println("Me ha llegado la peticion de obtener configuracion");
        Usuario user = (Usuario) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/home";
        } else {
            return "configuracion";
        }
    }

    @RequestMapping(value = "/guardarCambios")
    //public String guardarCambios(HttpServletRequest request,
    // @RequestParam("urlPerfil") MultipartFile file) throws Exception{
    public String guardarCambios(HttpServletRequest request) throws Exception {
        System.out.println("Me ha llegado la peticion de guardar cambios");

        String nombreYApellidos = request.getParameter("nombreApellidos");
        String localizacion = request.getParameter("localizacion");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String bio = request.getParameter("Text1");
        String urlImagen = request.getParameter("urlPerfilWeb");
        /*
        if (!file.isEmpty()) {
            BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            String path = request.getSession().getServletContext().getRealPath("/");
            System.out.println("Path: " + path + "resources/" + file.getOriginalFilename());
            System.out.println(System.getProperty("user.dir"));
            System.out.println(new java.io.File(".").getCanonicalPath());
            File destination = new File(path + "resources/" + file.getOriginalFilename()); // something like C:/Users/tom/Documents/nameBasedOnSomeId.png
            ImageIO.write(src, "jpg", destination);
        }*/

        DAOUsuario daoUsuario = new DAOUsuario();
        boolean cambioEmail = false;

        Usuario previo = (Usuario) request.getSession().getAttribute("user");

        if ((!email.equalsIgnoreCase(""))) {
            daoUsuario.updateUser(previo, "email", email);
            cambioEmail = true;
        }
        if (cambioEmail) {
            Usuario cambiado2 = daoUsuario.getUserEmail(email);
            if ((!nombreYApellidos.equalsIgnoreCase(""))) {
                daoUsuario.updateUser(cambiado2, "nombreApellidos", nombreYApellidos);
            }
            if ((!localizacion.equalsIgnoreCase(""))) {
                daoUsuario.updateUser(cambiado2, "lugar", localizacion);
            }
            if ((!password.equalsIgnoreCase(""))) {
                Password pw = new Password();
                daoUsuario.updateUser(cambiado2, "password", pw.getSaltedHash(password));
            }
            if ((!fechaNacimiento.equalsIgnoreCase(""))) {
                daoUsuario.updateUser(cambiado2, "nacimiento", fechaNacimiento);
            }
            if ((!bio.equalsIgnoreCase(""))) {
                daoUsuario.updateUser(cambiado2, "biografia", bio);
            }
            if ((!urlImagen.equalsIgnoreCase(""))) {
                daoUsuario.updateUser(cambiado2, "URL_foto", urlImagen);
            }
        } else {
            if ((!nombreYApellidos.equalsIgnoreCase(""))) {
                daoUsuario.updateUser(previo, "nombreApellidos", nombreYApellidos);
            }
            if ((!localizacion.equalsIgnoreCase(""))) {
                daoUsuario.updateUser(previo, "lugar", localizacion);
            }
            if ((!password.equalsIgnoreCase(""))) {
                Password pw = new Password();
                daoUsuario.updateUser(previo, "password", pw.getSaltedHash(password));
            }
            if ((!fechaNacimiento.equalsIgnoreCase(""))) {
                daoUsuario.updateUser(previo, "nacimiento", fechaNacimiento);
            }
            if ((!bio.equalsIgnoreCase(""))) {
                daoUsuario.updateUser(previo, "biografia", bio);
            }
            if ((!urlImagen.equalsIgnoreCase(""))) {
                daoUsuario.updateUser(previo, "URL_foto", urlImagen);
            }
        }

        if(cambioEmail){
            request.getSession().setAttribute("user",daoUsuario.getUserEmail(email));
        } else{
            request.getSession().setAttribute("user",previo);
        }
        return "redirect:/config";
    }
}
