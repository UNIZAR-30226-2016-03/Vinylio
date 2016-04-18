<%@ page import="com.eina.as.modelo.service.Vinilo" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE HTML>
<!--
Prologue by HTML5 UP
html5up.net | @n33co
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
	<title>Vinylio</title>
	<meta charset="utf-8" />
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@ page pageEncoding="UTF-8"%>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!--[if lte IE 8]><script src="../resources/perfil/assets/js/ie/html5shiv.js"></script><![endif]-->
	<link rel="stylesheet" href="../resources/perfil/assets/css/main.css" />
	<!--[if lte IE 8]><link rel="stylesheet" href="../resources/perfil/assets/css/ie8.css" /><![endif]-->
	<!--[if lte IE 9]><link rel="stylesheet" href="../resources/perfil/assets/css/ie9.css" /><![endif]-->
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
	</script>

	<!-- Acaba script de prueba empieza style deprueba-->

</head>
<body>

<!-- Header -->
<div id="header">

	<div class="top">

		<!-- Logo -->
		<div id="logo">
			<span class="image avatar48"><img src="images/usuarioAlex.jpg" alt="" /></span>
			<h1 id="title">Usuario Anónimo</h1>
			<p>Coleccionista Iniciado</p>
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
				<li><a href="/timeline" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Inicio</span></a></li>
				<li><a href="/catalogo" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-user">Catálogo</span></a></li>
				<li><a href="#contact" id="contact-link" class="skel-layers-ignoreHref"><span class="icon fa-envelope">Contáctanos</span></a></li>
			</ul>
		</nav>

	</div>

	<div class="bottom">

		<!-- Social Icons -->
		<ul class="icons">
			<li><a href="https://twitter.com/Vinylio_" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
			<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
			<li><a href="https://github.com/UNIZAR-30226-2016-03/Vinylio" class="icon fa-github"><span class="label">Github</span></a></li>
			<!--<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>-->
			<li><a href="https://mail.google.com/mail/u/0/?view=cm&fs=1&tf=1&source=mailto&to=vinylio.raytech@gmail.com" class="icon fa-envelope"><span class="label">Email</span></a></li>
		</ul>

	</div>

</div>

<!-- Main -->
<div id="main">


	<!-- Catálogo -->
	<section id="about" class="three">
		<div class="container">

			<header>
				<h2>Catálogo</h2>
			</header>

			<p>Aquí podrás encontrar 25 vinilos.
				Para ver los siguientes 25 vinilos <br>haz click en <strong>Ver más</strong>.</p>


			<div class="tablePropia">
				<table >

					<%ArrayList <String[]> listaVinilos = (ArrayList<String[]>)request.getAttribute("listaVinilos");
						request.removeAttribute("listaVinilos");
						for (int i = 0; i < listaVinilos.size(); i++) {
							String[] vin = listaVinilos.get(i);
					%>

					<tr>
						<td><img src="<%=vin[6]%>" alt="" height="100" width="100"/></td><td><%=vin[1]%></td><td><%=vin[2]%></td>
						<td><%=vin[3]%></td><td><%=vin[4]%></td><td><%=vin[5]%></td>
					<tr/>
					<%
						}
					%>
					</tr>
						<!-- Por si tengo que recuperar esto
                        <tr>
                            <td><img src="images/vinilo_placeholder.png" alt="" height="100" width="100"/></td><td>titulo</td><td>autor</td>
                            <td>genero</td><td>fecha</td><td>discografica</td>
                            <td>rpm</td>
                        </tr>
                         -->

				</table>
			</div>
			<%
				if (listaVinilos.size()>=25) {
			%>
					<footer>
					<a href="/catalogo2" class="button scrolly">Ver los siguientes 50</a>
					</footer>
			<% } else{ %>
					<footer>
						<a href="" class="button scrolly">Ya no hay más vinilos en la colección global</a>
					</footer>

			<% } %>

            </div>
        </section>

        <!-- Contact -->
	<section id="contact" class="four">
		<div class="container">

			<header>
				<h2>Contacto</h2>
			</header>

			<p>¿Alguno de tus vinilos no aparece en el catálogo? :( <br />
				No pasa nada, puedes contactar con un administrador y él
				se encargará de añadirlo.<br />
				En el formulario te especificaremos lo que tienes que enviarnos.
				No te agobies, solo los datos con <strong>asterisco*</strong> son obligatorios, pero harás nuestro trabajo mas fácil cuantos más datos incluyas.</p>

			<form method="post" action="#">
				<div class="row">
					<div class="6u 12u$(mobile)"><input type="text" name="name" placeholder="Tu Nombre" /></div>
					<div class="6u$ 12u$(mobile)"><input type="text" name="email" placeholder="vinylio.raytech@gmail.com" /></div>
					<div class="12u$">
										<textarea name="message" placeholder="Título*:
Artista*:
Género:
Fecha de lanzamiento:
Discográfica:
Imagen (URL):
RPM:
Nº de lanzamiento:"></textarea>
					</div>
					<div class="12u$">
						<input type="submit" value="Send Message" />
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