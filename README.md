# WebRedSocial

## Tecnologia SCRUM
* [Trello] (https://trello.com/b/WyAGMviN/trabajo-web)

## Plantillas
* [Read Only] (http://html5up.net/read-only)
* [Strata] (http://html5up.net/strata)

## Configuracion para lanzar el proyecto (IMPORTANTE!!!!!)
* [Descarga Tomcat] (http://ftp.cixug.es/apache/tomcat/tomcat-8/v8.0.30/bin/apache-tomcat-8.0.30.zip)
* Crear configuracion en run/debug, darle al '+', TomcatServer -> local
* En application server coger la ruta del tomcat que acabais de bajar
* En after launch poner http://localhost:8080/home
* Ir a project structure y en artifacts crear un war exploded from modules y seleccionar el proyecto
* Crear otro artifact war archive y que coja el war anterior
* Volver a run configuration y en la pestaña deployment del tomcat darle al '+' y seleccionar el War (NO EXPLODED) creado
* Guardar y cerrar, y correr tomcat, deberia funcionar
* 

## Resolver problemas 
* Servlet no detectado (no imprime ni los println): Project settings -> modules -> click en Spring -> borrar el spring application context -> darle al '+' -> pinchar en la raiz del proyecto -> guardar
* JSP's no detectados (lineas en rojo al pinchar el modulo web en project settings): hacer doble click en la ruta -> seleccionar la carpeta web dentro del directorio
* 

### Spring
* [Tutoriales completisimos] (https://www.youtube.com/watch?v=PykKv4aHAzA&index=1&list=PLnSATAFQN94BCL_rFWd0oOjrx8Hwj_91L)
* [Docs](http://spring.io/docs)
