<!DOCTYPE HTML>
<!--
Prologue by HTML5 UP
html5up.net | @n33co
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>Home - Vinylio</title>
    <link rel="shortcut icon" href="../resources/iconos/favicon.ico" />
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page pageEncoding="UTF-8"%>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!--[if lte IE 8]><script src="../resources/home/assets/js/ie/html5shiv.js"></script><![endif]-->
    <link rel="stylesheet" href="../resources/home/assets/css/main.css" />
    <!--[if lte IE 8]><link rel="stylesheet" href="../resources/home/assets/css/ie8.css" /><![endif]-->
    <!--[if lte IE 9]><link rel="stylesheet" href="../resources/home/assets/css/ie9.css" /><![endif]-->
    <!-- Empieza script de prueba -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <!-- script viejo-->
    <script>
        $(document).ready(function() {
            $("#loginLink").click(function( event ){
                event.preventDefault();
                $(".overlay").fadeToggle("fast");
            });

            $(".overlayLink").click(function(event){
                event.preventDefault();
                var action = $(this).attr('data-action');

                $("#loginTarget").load("ajax/" + action);

                $(".overlay").fadeToggle("fast");
            });

            $(".overlayLink2").click(function(event){
                event.preventDefault();
                var action = $(this).attr('data-action');

                $("#registerTarget").load("ajax/" + action);

                $(".overlayReg").fadeToggle("fast");
            });

            $(".close").click(function(){
                $(".overlay").fadeToggle("fast");
            });

            $(".closeReg").click(function(){
                $(".overlayReg").fadeToggle("fast");
            });

            $(document).keyup(function(e) {
                if(e.keyCode == 27 && $(".overlay").css("display") != "none" ) {
                    event.preventDefault();
                    $(".overlay").fadeToggle("fast");
                }
            });
        });
    </script>
     <!--Acaba script de prueba empieza style deprueba-->

</head>
<body>


<!-- Main -->
<div id="main">

    <!-- Intro -->
    <section id="top" class="one dark cover">
        <div class="container">

            <header>
                <h2 class="alt">Bienvenido a <strong>Vinylio</strong>, una nueva manera<br />
                    de mantener tu colección de Vinilos.</h2>
                <p>Regístrate ahora y comienza a añadir vinilos	a tu colección personal.</p>
            </header>
            <!-- Codigo para feedback fallos -->
            <%
                String fallo = (String) request.getSession().getAttribute("fallo");
                if(fallo.equals("contrasenya")) {
            %>
            <div class="container" style="background: tomato;border-radius: 5px;">
                <p class="alt">¡Oops! La contraseña introducida no es correcta.</p>
            </div>
            <%
            }
                else if(fallo.equals("email")){
            %>
            <div class="container" style="background: tomato;border-radius: 5px;">
                <p class="alt">¡Vaya! El email introducido no está registrado.<br />Haz click en <strong>Registro</strong> justo debajo, ¡Es gratis!</p>
            </div>
            <%
            }
                else if(fallo.equals("repetido")){
            %>
            <div class="container" style="background: tomato;border-radius: 5px;">
                <p class="alt">Hmm... Parece que el Email introducido ya está registrado.</p>
            </div>
            <%
            }
                else if(fallo.equals("registroOK")){
            %>
            <div class="container" style="background: mediumseagreen;border-radius: 5px;">
                <p class="alt">¡Yay! Registro completado satisfactoriamente.<br />Haz click en <strong>Iniciar Sesion</strong> y empieza tu colección.</p>
            </div>
            <%
                }
                else{}
            %>

            <!-- Fin codigo feedback fallos -->
            <footer>
                <hr class="prettyline">
                <ul>
                    <li><a href="/timeline" class="overlayLink" data-action="login-form.html">Iniciar Sesión</a></li>
                    <li><a href="/timeline" class="overlayLink2" data-action="registration-form.html">Registro</a></li>
                </ul>
                <hr class="prettyline">

                <!--Empieza codigo de prueba para login-->
                <!-- codigo de login -->
                <div class="overlay" style="display: none;">
                    <div class="login-wrapper">
                        <div class="login-content" id="loginTarget">
                            <a class="close">x</a>
                            <h3>Iniciar Sesión</h3>
                            <form method="post" action="/login">
                                <label for="username">
                                    Email:
                                    <input type="email" name="username" id="username" placeholder="Tu email" pattern="^[a-zA-Z0-9-_\.@][a-zA-Z0-9-_\.@][a-zA-Z0-9-_\.@][a-zA-Z0-9-_\.@][a-zA-Z0-9-_\.@]*$" required />
                                </label>
                                <label for="password">
                                    Contraseña:
                                    <input type="password" name="password" id="password" placeholder="Tu contraseña" pattern="^[a-zA-Z0-9-_\.][a-zA-Z0-9-_\.][a-zA-Z0-9-_\.][a-zA-Z0-9-_\.][a-zA-Z0-9-_\.][a-zA-Z0-9-_\.][a-zA-Z0-9-_\.]*$" required />
                                </label>
                                <button type="submit">Entrar</button>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- codigo de registro-->
                <div class="overlayReg" style="display: none;">
                    <div class="login-wrapper">
                        <div class="login-content" id="registerTarget">
                            <a class="closeReg">x</a>
                            <h3>Registro</h3>
                            <form method="post" action="/register">
                                <label for="email">
                                    Correo electrónico:
                                    <input type="email" name="email" id="email" placeholder="Debe ser una direccion válida" pattern="^[a-zA-Z0-9-_\.]+[@][a-zA-Z0-9-_\.]+[.][a-zA-Z0-9-_\.]+$" required />
                                </label>
                                <label for="nombreApellidos">
                                    Nombre y Apellidos:
                                    <input type="text" name="nombreApellidos" id="nombreApellidos" placeholder="No puede ser nulo, todo el mundo tiene un nombre ;)" pattern="^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$" required />
                                </label>
                                <label for="password">
                                    Contraseña (6-20 caracteres):
                                    <input type="password" name="password2" id="password2" placeholder="Al menos 1 mayuscula, 1 minuscula y 1 numero" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" minlength="6" maxlength="20" required />
                                </label>
                                <button type="submit">Registrarse</button>
                            </form>
                        </div>
                    </div>
                </div>


            </footer>

        </div>
    </section>
</div>

<!-- Footer -->
<div id="footer">

    <!-- Copyright -->
    <ul class="copyright">
        <ul class="icons">
            <li><a href="https://twitter.com/Vinylio_" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
            <li><a href="https://www.facebook.com/VinylioRaytech/" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
            <li><a href="https://github.com/UNIZAR-30226-2016-03/Vinylio" class="icon fa-github"><span class="label">Github</span></a></li>
            <!--<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>-->
            <li><a href="https://mail.google.com/mail/u/0/?view=cm&fs=1&tf=1&source=mailto&to=vinylio.raytech@gmail.com" class="icon fa-envelope"><span class="label">Email</span></a></li>
        </ul>
        <li>&copy; RayTech. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
    </ul>

</div>

<!-- Scripts -->
<script src="../resources/home/assets/js/jquery.min.js"></script>
<script src="../resources/home/assets/js/jquery.scrolly.min.js"></script>
<script src="../resources/home/assets/js/jquery.scrollzer.min.js"></script>
<script src="../resources/home/assets/js/skel.min.js"></script>
<script src="../resources/home/assets/js/util.js"></script>
<!--[if lte IE 8]><script src="../resources/home/assets/js/ie/respond.min.js"></script><![endif]-->
<script src="../resources/home/assets/js/main.js"></script>

</body>
</html>