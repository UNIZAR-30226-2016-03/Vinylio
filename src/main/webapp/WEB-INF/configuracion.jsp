<%@ page import="com.eina.as.modelo.service.Usuario" %>
<%@ page import="com.eina.as.modelo.service.Vinilo" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE HTML>
<!--
Prologue by HTML5 UP
html5up.net | @n33co
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
	<title>Configuracion - Vinylio</title>
	<link rel="shortcut icon" href="../resources/iconos/favicon.ico" />
	<meta charset="utf-8" />
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@ page pageEncoding="UTF-8"%>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!--[if lte IE 8]><script src="../resources/configuracion/assets/js/ie/html5shiv.js"></script><![endif]-->
	<link rel="stylesheet" href="../resources/configuracion/assets/css/main.css" />
	<!--[if lte IE 8]><link rel="stylesheet" href="../resources/configuracion/assets/css/ie8.css" /><![endif]-->
	<!--[if lte IE 9]><link rel="stylesheet" href="../resources/configuracion/assets/css/ie9.css" /><![endif]-->
	<!-- Empieza script de prueba -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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

			$(".close").click(function(){
				$(".overlay").fadeToggle("fast");
			});

			$(document).keyup(function(e) {
				if(e.keyCode == 27 && $(".overlay").css("display") != "none" ) {
					event.preventDefault();
					$(".overlay").fadeToggle("fast");
				}
			});
		});


		function buscar(){
			var elemento = document.getElementById("navBusqueda").value;
			$.post("/buscar", {usernameB:elemento}, function(result){
				result = result.trim();
				if(result == 'exito'){
					window.location.replace("/busquedaColeccion");
				} else{
					alert("El usuario "+elemento+" no existe.");
					window.location.replace("/timeline");
				}
			});
		}
	</script>

	<!-- Acaba script de prueba empieza style deprueba-->

</head>
<body>

<!-- Header -->
<div id="header">

	<div class="top">
		<%
			Usuario user = (Usuario) request.getSession().getAttribute("user");
		%>
		<!-- Logo -->
		<div id="logo">
			<span class="image avatar48"><img src="<%out.println(user.getUrlFoto());%>" alt="" /></span>
			<h1 id="title"><%out.println(user.getNombreApellidos());%></h1>
			<ul class="icons" style="text-align: right;">
				<a href="/config" id="config"  class="icon fa-gear"></a>
				<a href="/logout" id="logout" class="icon fa-sign-out" style="padding-left: 15px;"></a>
			</ul>
			<p><%out.println(user.getlugar());%></p>
			<p><%out.println(user.getBiografia());%></p>
		</div>

		<!-- Nav -->
		<nav id="nav">
			<!--

                Prologue's nav expects links in one of two formats:

                1. Hash link (scrolls to a different section within the page)

                   <li><a href="#foobar" id="foobar-link" class="icon fa-whatever-icon-you-want skel-layers-ignoreHref"><span class="label">Foobar</span></a></li>

                2. Standard link (sends the user to another page/site)

                   <li><a href="http://foobar.tld" id="foobar-link" class="icon fa-whatever-icon-you-want"><span class="label">Foobar</span></a></li>

            -->
			<ul>
				<li><div id="custom-search-input">
					<div class="input-group col-md-12">
						<input type="text" placeholder="Buscar" id="navBusqueda" maxlength="64" name="navBusqueda" style="padding-right: 3px;
   																													 	border-radius: 3px;
    																													margin-right: 10px;">
					</div>
				</div>
				</li>
				<li><a href="#" if="buscar" class="skel-layers-ignoreHref" onclick="buscar()"><span class="icon fa-search">Buscar</span></a></li>
				<li><a href="/timeline" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Inicio</span></a></li>
				<li><a href="/coleccion" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-user">Mi Colección</span></a></li>
				<li><a href="/catalogo" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-th">Catálogo</span></a></li>
				<li><a href="#contact" id="contact-link" class="skel-layers-ignoreHref"><span class="icon fa-envelope">Contáctanos</span></a></li>
			</ul>
		</nav>

	</div>

	<div class="bottom">

		<!-- Social Icons -->
		<ul class="icons">
			<li><a href="https://twitter.com/Vinylio_" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
			<li><a href="https://www.facebook.com/VinylioRaytech/" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
			<li><a href="https://github.com/UNIZAR-30226-2016-03/Vinylio" class="icon fa-github"><span class="label">Github</span></a></li>
			<!--<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>-->
			<li><a href="https://mail.google.com/mail/u/0/?view=cm&fs=1&tf=1&source=mailto&to=vinylio.raytech@gmail.com" class="icon fa-envelope"><span class="label">Email</span></a></li>
		</ul>

	</div>

</div>

<!-- Main -->
<div id="main">

	<!-- Intro -->
	<section id="top" class="one dark cover">

	</section>
	<!-- Contact -->
	<section id="contact" class="four">
		<div class="container">

			<header>
				<h2>Configuracion</h2>
			</header>

			<p>Aquí puedes cambiar tus datos. <br />
				Tranquilo, de momento no hemos recibido ninguna orden del FBI.</p>

			<form method="post" action="/guardarCambios">
				<div class="row">
					<div class="6u 12u(xsmall)">
						<label for="idnombreApellidos">Nombre y Apellidos</label>
						<input type="text" name="nombreApellidos" id="idnombreApellidos" maxlength="64" pattern="^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$" placeholder="Nombre y Apellidos" value="<% out.println(user.getNombreApellidos()); %>"/>
					</br>
						<label for="idLocalizacion">Ubicacion</label>
						<input type="text" name="localizacion" id="idLocalizacion" maxlength="32" pattern="^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$" placeholder="Localizacion" value="<% out.println(user.getlugar()); %>"/>
					</br>
						<label for="idemail">Correo</label>
						<input type="email" name="email" id="idemail" maxlength="64" placeholder="Email" value="<% out.println(user.getEmail()); %>"/>
					</div>
					<div class="6u 12u(xsmall)">
						<label for="idpassword">Contraseña (6-20 caracteres)</label>
						<input type="password" name="password" id="idPassword" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" minlength="6" maxlength="20" placeholder="Al menos 1 mayuscula, 1 minuscula y 1 numero" />
					</br>
						<label for="idfechaNacimiento">Fecha de nacimiento</label>
						<input type="text" name="fechaNacimiento" id="idfechaNacimiento" pattern="(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d" placeholder="DD/MM/AAAA" value="<% out.println(user.getNacimiento()); %>"/>
					</div>
					<div class="12u">
						<label for="idBiografia">Biografia (Máximo 255 carácteres)</label>
						<textarea name="Text1" cols="5" rows="5" id="idBiografia" pattern="^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$" placeholder="Biografía" maxlength="255" ><% out.println(user.getBiografia()); %></textarea>
					</div>
					<div class="6u12u">
						<label for="idUrlPerfilWeb">URL Avatar (Recomendamos .jpg)</label>
						<input type="url" name="urlPerfilWeb" id="idUrlPerfilWeb" value="<% out.println(user.getUrlFoto()); %>"/>
					</div>
					<div class="12u$">
						<input type="submit" value="Enviar" />
						<input type="reset" value="Reset"/>
					</div>
				</div>
			</form>

		</div>
	</section>

</div>

<!-- Footer -->
<div id="footer">

	<!-- Copyright -->
	<ul class="copyright">
		<li>&copy; Raytech. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
	</ul>

</div>

<!-- Scripts -->
<script src="../resources/configuracion/assets/js/jquery.min.js"></script>
<script src="../resources/configuracion/assets/js/jquery.scrolly.min.js"></script>
<script src="../resources/configuracion/assets/js/jquery.scrollzer.min.js"></script>
<script src="../resources/configuracion/assets/js/skel.min.js"></script>
<script src="../resources/configuracion/assets/js/util.js"></script>
<!--[if lte IE 8]><script src="../resources/configuracion/assets/js/ie/respond.min.js"></script><![endif]-->
<script src="../resources/configuracion/assets/js/main.js"></script>

</body>
</html>