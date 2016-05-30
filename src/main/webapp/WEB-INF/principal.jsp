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
	<title>Vinylio</title>
	<link rel="shortcut icon" href="../resources/iconos/favicon.ico" />
	<meta charset="utf-8" />
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@ page pageEncoding="UTF-8"%>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!--[if lte IE 8]><script src="../resources/principal/assets/js/ie/html5shiv.js"></script><![endif]-->
	<link rel="stylesheet" href="../resources/principal/assets/css/main.css" />
	<!--[if lte IE 8]><link rel="stylesheet" href="../resources/principal/assets/css/ie8.css" /><![endif]-->
	<!--[if lte IE 9]><link rel="stylesheet" href="../resources/principal/assets/css/ie9.css" /><![endif]-->
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
                        <!--<span class="input-group-btn">

                                    <!--<button onclick="buscar()" class="icon fa-search" type="button">
                                        <span class=" glyphicon glyphicon-search"></span>
                                    </button>-->

                    <!--</span>-->
					</div>
				</div>
				</li>
                <!--<li><a href="#" input type="button" onclick="buscar()">Buscar</a></li>-->
				<li><a href="#" if="buscar" class="skel-layers-ignoreHref" onclick="buscar()"><span class="icon fa-search">Buscar</span></a></li>
				<li><a href="#top" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Inicio</span></a></li>
				<li><a href="#portfolio" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-user">Mi Colección</span></a></li>
				<li><a href="#about" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-th">Catálogo</span></a></li>
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

	<!-- Portfolio -->
	<section id="portfolio" class="two">
		<div class="container">

			<header>
				<h2>Mi Colección</h2>
			</header>

			<p>Aquí podrás ver y eliminar vinilos de tu colección. <br />
				Para ver la lista completa, haz click en <strong><a href="/coleccion">Ver más</a></strong>.</p>

			<div class="row">
				<%ArrayList<Vinilo> coleccionVinilos = (ArrayList<Vinilo>)request.getAttribute("coleccionVinilos");
					request.removeAttribute("coleccionVinilos");
					if(coleccionVinilos.size()>=2){
						int i=0;
						while( i < 6 && coleccionVinilos.size() > i) {
							if(i < coleccionVinilos.size() && coleccionVinilos.size() < (i+2)){
								Vinilo col = coleccionVinilos.get(i);
				%>
				<div class="4u 12u$(mobile)">
					<article class="item">
						<a href="#" class="image fit"><img src="<%=col.getImagen()%>" alt="" /></a>
						<header>
							<h3><b><%=col.getAutor()%></b></h3>
							<h3><%=col.getTitulo()%></h3>
						</header>
					</article>
				</div>
				<%
				}
							else{
								Vinilo col = coleccionVinilos.get(i);
								Vinilo col2 = coleccionVinilos.get(i+1);
				%>
				<div class="4u 12u$(mobile)">
					<article class="item">
						<a href="#" class="image fit"><img src="<%=col.getImagen()%>" alt="" /></a>
						<header>
							<h3><b><%=col.getAutor()%></b></h3>
							<h3><%=col.getTitulo()%></h3>
						</header>
					</article>
					<article class="item">
						<a href="#" class="image fit"><img src="<%=col2.getImagen()%>" alt="" /></a>
						<header>
							<h3><%=col2.getAutor()%></h3>
							<h3><%=col2.getTitulo()%></h3>
						</header>
					</article>
				</div>
				<%			}i=i+2; }
					}
				else if (coleccionVinilos.size()==1){
					Vinilo col = coleccionVinilos.get(0);
				%>
				<div class="4u 12u$(mobile)">
					<article class="item">
						<a href="#" class="image fit"><img src="<%=col.getImagen()%>" alt="" /></a>
						<header>
							<h3><b><%=col.getAutor()%></b></h3>
							<h3><%=col.getTitulo()%></h3>
						</header>
					</article>
				</div>
				<%
					}
					else{}
				%>
			</div
				<footer>
					<a href="/coleccion" class="button scrolly">Ver más.</a>
				</footer>

		</div>
	</section>

	<!-- Catálogo -->
	<section id="about" class="three">
		<div class="container">

			<header>
				<h2>Catálogo</h2>
			</header>

			<p>Aquí podrás encontrar las últimas novedades y añadirlas a tu colección.<br/>
				Para ver la lista completa haz click en <strong><a href="/catalogo">Ver más</a></strong>.</p>

			<div class="row">
				<%ArrayList<Vinilo> listaVinilos = (ArrayList<Vinilo>)request.getAttribute("listaVinilos");
					request.removeAttribute("listaVinilos");
					if(!listaVinilos.isEmpty()){
						for (int i = 0; i < 6; i=i+2) {
							Vinilo vin = listaVinilos.get(i);
							Vinilo vin2 = listaVinilos.get(i+1);
				%>
				<div class="4u 12u$(mobile)">
					<article class="item">
						<a href="#" class="image fit"><img src="<%out.println(vin.getImagen());%>" alt="" /></a>
						<header>
							<h3><b><%out.println(vin.getAutor());%></b></h3>
							<h3><%out.println(vin.getTitulo());%></h3>
						</header>
					</article>
					<article class="item">
						<a href="#" class="image fit"><img src="<%out.println(vin2.getImagen());%>" alt="" /></a>
						<header>
							<h3><%out.println(vin2.getAutor());%></h3>
							<h3><%out.println(vin2.getTitulo());%></h3>
						</header>
					</article>
				</div>
				<% }} %>
			</div>
				<footer>
					<a href="/catalogo" class="button scrolly">Ver más.</a>
				</footer>

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

			<form method="post" enctype="text/plain" action="mailto:vinylio.raytech@gmail.com">
				<div class="row">
					<div class="6u 12u$(mobile)"><input type="text" name="name" value="<%= user.getNombreApellidos() %>" readonly="readonly"/></div>
					<div class="6u$ 12u$(mobile)"><input type="email" name="email" value="<%= user.getEmail() %>" readonly="readonly"/></div>
					<div class="6u 12u$(mobile)"><input type="text" name="titulo" placeholder="Titulo*" required minlength="1" maxlength="64" pattern="^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$"/></div>
					<div class="6u$ 12u$(mobile)"><input type="text" name="artista" placeholder="Artista*" required minlength="1" maxlength="64" pattern="^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$"/></div>
					<div class="6u 12u$(mobile)"><input type="text" name="genero" placeholder="Genero" maxlength="32" pattern="^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$"/></div>
					<div class="6u$ 12u$(mobile)"><input type="text" name="lanzamiento" placeholder="Fecha de Lanzamiento (dd/mm/aa)" pattern="(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d"/></div>
					<div class="6u 12u$(mobile)"><input type="text" name="discografica" placeholder="Discográfica" maxlength="32" pattern="^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$"/></div>
					<div class="6u$ 12u$(mobile)"><input type="text" name="imagen" placeholder="Imagen (URL)*" required minlength="1" pattern=".{1,127}"/></div>
					<div class="6u 12u$(mobile)"><input type="text" name="rpm" placeholder="RPM (33,45)"  maxlength="3" pattern="[0-9]"/></div>
					<div class="6u$ 12u$(mobile)"><input type="text" name="numlanzamiento"  placeholder="Numero de Lanzamiento" pattern=".{1,12}"/></div>

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
<script src="../resources/home/assets/js/jquery.min.js"></script>
<script src="../resources/home/assets/js/jquery.scrolly.min.js"></script>
<script src="../resources/home/assets/js/jquery.scrollzer.min.js"></script>
<script src="../resources/home/assets/js/skel.min.js"></script>
<script src="../resources/home/assets/js/util.js"></script>
<!--[if lte IE 8]><script src="../resources/home/assets/js/ie/respond.min.js"></script><![endif]-->
<script src="../resources/home/assets/js/main.js"></script>

</body>
</html>