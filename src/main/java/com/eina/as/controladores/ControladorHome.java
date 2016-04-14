package com.eina.as.controladores;

import com.eina.as.modelo.dataAccess.DAOUsuario;
import com.eina.as.modelo.service.Password;
import com.eina.as.modelo.service.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorHome {

    @RequestMapping(value="/home")
    public String redireccionHome(){
        System.out.println("Me ha llegado la peticion de obtener home");
        return "home";
    }

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String registro(@RequestParam("email") String email,
                           @RequestParam("nombreApellidos") String nombreYApellidos,
                           @RequestParam("password") String password) throws Exception{
        System.out.println("Me ha llegado la peticion de registro");
        System.out.println("Email: " + email);
        System.out.println("NombreYApellidos: " + nombreYApellidos);
        System.out.println("Pass: " + password);

        Password p = new Password();
        System.out.println("Longitud de la pass: " + p.getSaltedHash(password).length());
        Usuario user = new Usuario(null,nombreYApellidos,email,p.getSaltedHash(password),null,null,null);

        DAOUsuario daoUsuario = new DAOUsuario();
        daoUsuario.insert(user);
        return "home";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@RequestParam("username") String user,
                        @RequestParam("password") String password,
                        HttpServletRequest request) throws Exception{
        DAOUsuario daoUsuario = new DAOUsuario();
        Password pw = new Password();
        Usuario usuario;
        if(user.contains("@")){
            //Correo
            usuario = daoUsuario.getUserEmail(user);
        } else{
            //Nick
            usuario = daoUsuario.getUserEmail(user);
        }

        if(usuario != null){
            if(pw.check(password,usuario.getPassword())){
                request.getSession().setAttribute("user",usuario);
                return "redirect:/timeline";
            } else{
                return "redirect:/home";
            }
        } else{
            return "redirect:/home";
        }
    }

}
