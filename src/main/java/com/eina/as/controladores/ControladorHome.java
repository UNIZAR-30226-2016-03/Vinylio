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
    public String redireccionHome(HttpServletRequest request){
        System.out.println("Me ha llegado la peticion de obtener home");
        String fallo = "naa";
        request.getSession().setAttribute("fallo", fallo);
        return "home";
    }

    @RequestMapping(value="/homer")
    public String redireccionHomer(){
        System.out.println("Me ha llegado la peticion de obtener homer");

        return "home";
    }

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String registro(@RequestParam("email") String email,
                           @RequestParam("nombreApellidos") String nombreApellidos,
                           @RequestParam("password2") String password,
                           HttpServletRequest request) throws Exception{
        System.out.println("Me ha llegado la peticion de registro");
        System.out.println("Email: " + email);
        System.out.println("NombreYApellidos: " + nombreApellidos);
        System.out.println("Pass: " + password);

        Password p = new Password();
        System.out.println("Longitud de la pass: " + p.getSaltedHash(password).length());
        Usuario user = new Usuario("",nombreApellidos,email,p.getSaltedHash(password),"http://s.mvsdn.com/img/users/avatar/4r/4rhnD0Y6K_big.jpg","","");
        DAOUsuario daoUsuario = new DAOUsuario();
        String fallo;

        if(daoUsuario.existe(user)){
            System.out.println("Error email repetido");
            fallo = "repetido";
            request.getSession().removeAttribute("fallo");
            request.getSession().setAttribute("fallo",fallo);
            return "redirect:/homer";
        }
        else {
            System.out.println("Todo bien todo correcto");
            fallo = "registroOK";
            daoUsuario.insert(user);
            request.getSession().removeAttribute("fallo");
            request.getSession().setAttribute("fallo",fallo);
            return "redirect:/homer";
        }
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@RequestParam("username") String user,
                        @RequestParam("password") String password,
                        HttpServletRequest request) throws Exception{
        DAOUsuario daoUsuario = new DAOUsuario();
        Password pw = new Password();
        Usuario usuario;
        usuario = daoUsuario.getUserEmail(user);
        String fallo;
        // codigo de comportamiento si login o no login
        if(usuario != null){
            if(pw.check(password,usuario.getPassword())){
                request.getSession().setAttribute("user",usuario);
                return "redirect:/timeline";
            } else{
                System.out.println("Error contraseña");
                fallo = "contrasenya";
                request.getSession().removeAttribute("fallo");
                request.getSession().setAttribute("fallo",fallo);
                return "redirect:/homer";
            }
        } else{
            System.out.println("error email");
            fallo ="email";
            request.getSession().removeAttribute("fallo");
            request.getSession().setAttribute("fallo",fallo);
            return "redirect:/homer";
        }
    }

}
