<!DOCTYPE HTML>
<!--
Prologue by HTML5 UP
html5up.net | @n33co
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>Home</title>
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
                <h2 class="alt">Bienvenido a <strong>XXXXXX</strong>, donde puedes compartir<br />
                    tus mejores partidas con tus amigos.</h2>
                <p>Regístrate ya y disfruta jugando con tus amigos.</p>
            </header>

            <footer>
                <hr class="prettyline">
                <ul>
                    <li><a href="login.php" class="overlayLink" data-action="login-form.html">Iniciar Sesión</a></li>
                    <li><a href="register.php" class="overlayLink2" data-action="registration-form.html">Registro</a></li>
                </ul>
                <hr class="prettyline">

                <!--Empieza codigo de prueba para login-->
                <!-- codigo de login -->
                <div class="overlay" style="display: none;">
                    <div class="login-wrapper">
                        <div class="login-content" id="loginTarget">
                            <a class="close">x</a>
                            <h3>Iniciar Sesión</h3>
                            <form method="post" action="login.php">
                                <label for="username">
                                    Nombre de Usuario:
                                    <input type="text" name="username" id="username" placeholder="Debe contener entre 8 y 20 caracteres" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]$" required />
                                </label>
                                <label for="password">
                                    Contraseña:
                                    <input type="password" name="password" id="password" placeholder="Debe contener al menos 1 mayús. y un número" pattern="(?=^.$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required />
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
                            <form method="post" action="register.php">
                                <label for="email">
                                    Correo electrónico:
                                    <input type="text" name="email" id="email" placeholder="Debe ser una direccion válida" pattern="^[a-zA-Z](@)[a-zA-Z0-9-_\.]$" required />
                                </label>
                                <label for="username">
                                    Nombre de Usuario:
                                    <input type="text" name="username" id="usernameReg" placeholder="Debe contener entre 8 y 20 caracteres" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]$" required />
                                </label>
                                <label for="password">
                                    Contraseña:
                                    <input type="password" name="password" id="passwordReg" placeholder="Debe contener al menos 1 mayús. y un número" pattern="(?=^.$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required />
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
            <li><a href="https://twitter.com" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
            <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
            <li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
            <!--<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>-->
            <li><a href="#" class="icon fa-envelope"><span class="label">Email</span></a></li>
        </ul>
        <li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
    </ul>

</div>

<!-- Scripts -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/jquery.scrolly.min.js"></script>
<script src="assets/js/jquery.scrollzer.min.js"></script>
<script src="assets/js/skel.min.js"></script>
<script src="assets/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="assets/js/main.js"></script>

</body>
</html>