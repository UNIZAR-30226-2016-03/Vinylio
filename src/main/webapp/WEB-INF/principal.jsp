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
				<li><a href="#top" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Inicio</span></a></li>
				<li><a href="#portfolio" id="portfolio-link" class="skel-layers-ignoreHref"><span class="icon fa-th">Mi Colección</span></a></li>
				<li><a href="#about" id="about-link" class="skel-layers-ignoreHref"><span class="icon fa-user">Catálogo</span></a></li>
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

	<!-- Intro -->
	<section id="top" class="one dark cover">
		<div class="container">

			<header>
				<h2 class="alt">Bienvenido a <strong>Vinylio</strong>, una nueva manera<br />
					de mantener tu colección de Vinilos.</h2>
				<p>Regístrate ahora y comienza a añadir vinilos	a tu colección personal.</p>
			</header>

			<footer>
				<a href="login.php" class="button scrolly">Entrar</a>
				<ul>
					<li><a href="login.php" class="overlayLink" data-action="login-form.html">Iniciar Sesión</a></li>
					<li><a href="register.php" class="overlayLink" data-action="registration-form.html">Registro</a></li>
				</ul>
				<!--Empieza codigo de prueba para login-->
				<div class="overlay" style="display: none;">
					<div class="login-wrapper">
						<div class="login-content" id="loginTarget">
							<a class="close">x</a>
							<h3>Login</h3>
							<form method="post" action="login.php">
								<label for="username">
									Username:
									<input type="text" name="username" id="username" placeholder="Debe contener entre 8 y 20 caracteres" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]$" required />
								</label>
								<label for="password">
									Password:
									<input type="password" name="password" id="password" placeholder="Debe contener al menos 1 mayús. y un número" pattern="(?=^.$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required />
								</label>
								<button type="submit">Sign in</button>
							</form>
						</div>
					</div>
				</div>

			</footer>

		</div>
	</section>

	<!-- Portfolio -->
	<section id="portfolio" class="two">
		<div class="container">

			<header>
				<h2>Mi Colección</h2>
			</header>

			<p>
				Para ver la lista completa, haz click en <strong>Ver más</strong>.</p>

			<div class="row">
				<div class="4u 12u$(mobile)">
					<article class="item">
						<a href="#" class="image fit"><img src="images/gkot.jpg" alt="" /></a>
						<header>
							<h3>Tenderness</h3>
							<h3>Gotta Keep On Trying</h3>
						</header>
					</article>
					<article class="item">
						<a href="#" class="image fit"><img src="images/vinilo_placeholder.png" alt="" /></a>
						<header>
							<h3>The Yarbirds</h3>
							<h3>For Your Love</h3>
						</header>
					</article>
				</div>
				<div class="4u 12u$(mobile)">
					<article class="item">
						<a href="#" class="image fit"><img src="images/pic04.jpg" alt="" /></a>
						<header>
							<h3>Magna Nullam</h3>
						</header>
					</article>
					<article class="item">
						<a href="#" class="image fit"><img src="images/pic05.jpg" alt="" /></a>
						<header>
							<h3>Natoque Vitae</h3>
						</header>
					</article>
				</div>
				<div class="4u$ 12u$(mobile)">
					<article class="item">
						<a href="#" class="image fit"><img src="images/pic06.jpg" alt="" /></a>
						<header>
							<h3>Dolor Penatibus</h3>
						</header>
					</article>
					<article class="item">
						<a href="#" class="image fit"><img src="images/pic07.jpg" alt="" /></a>
						<header>
							<h3>Orci Convallis</h3>
						</header>
					</article>
				</div>
			</div>
			<footer>
				<a href="#portfolio" class="button scrolly">Ver más.</a>
			</footer>

		</div>
	</section>

	<!-- Catálogo -->
	<section id="about" class="three">
		<div class="container">

			<header>
				<h2>Catálogo</h2>
			</header>

			<p>Aquí podrás encontrar las últimas novedades.
				Para ver la lista completa haz click en <strong>Ver más</strong> al pie de la página.</p>

			<div class="row">
				<div class="4u 12u$(mobile)">
					<article class="item">
						<a href="#" class="image fit"><img src="images/gkot.jpg" alt="" /></a>
						<header>
							<h3>Tenderness</h3>
							<h3>Gotta Keep On Trying</h3>
						</header>
					</article>
					<article class="item">
						<a href="#" class="image fit"><img src="images/vinilo_placeholder.png" alt="" /></a>
						<header>
							<h3>The Yarbirds</h3>
							<h3>For Your Love</h3>
						</header>
					</article>
				</div>
				<div class="4u 12u$(mobile)">
					<article class="item">
						<a href="#" class="image fit"><img src="images/pic04.jpg" alt="" /></a>
						<header>
							<h3>Magna Nullam</h3>
						</header>
					</article>
					<article class="item">
						<a href="#" class="image fit"><img src="images/pic05.jpg" alt="" /></a>
						<header>
							<h3>Natoque Vitae</h3>
						</header>
					</article>
				</div>
				<div class="4u$ 12u$(mobile)">
					<article class="item">
						<a href="#" class="image fit"><img src="images/pic06.jpg" alt="" /></a>
						<header>
							<h3>Dolor Penatibus</h3>
						</header>
					</article>
					<article class="item">
						<a href="#" class="image fit"><img src="images/pic07.jpg" alt="" /></a>
						<header>
							<h3>Orci Convallis</h3>
						</header>
					</article>
				</div>
			</div>
			<footer>
				<a href="#portfolio" class="button scrolly">Ver más.</a>
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