# CENAMAD - Aplicación móvil sobre madera

# ==========================================

# 

# INTEGRANTES

# \-----------

# José Miranda

# Christopher Miranda

# 

# 

# DESCRIPCIÓN

# \-----------

# CENAMAD es una aplicación Android desarrollada en Kotlin con Jetpack Compose. Su objetivo es presentar información relacionada con la madera y ofrecer acceso a funciones como inicio de sesión, registro, ubicación GPS y consumo de una API externa.

# 

# 

# FUNCIONALIDADES

# \---------------

# \- Pantalla de inicio y navegación entre secciones.

# \- Registro e inicio de sesión de usuarios.

# \- Validación de campos y reglas de seguridad para los datos ingresados.

# \- Persistencia local de usuarios mediante Room.

# \- Consulta de información sobre madera.

# \- Pantalla de ubicación GPS con mapa.

# \- Consumo de API externa con solicitudes GET y POST.

# \- Pruebas unitarias para las validaciones.

# \- Uso de recursos nativos del dispositivo: ubicación GPS y cámara.

# \- Ícono personalizado de la aplicación.

# 

# 

# ARQUITECTURA Y ORGANIZACIÓN

# \---------------------------

# El proyecto organiza la interfaz, los datos y la lógica de presentación en paquetes separados para mantener las responsabilidades claras y aplicar el patrón MVVM.

# 

# Paquetes principales:

# 

# \- model: entidades y modelos de datos de la aplicación.

# \- view: pantallas y componentes de interfaz hechos con Jetpack Compose.

# \- viewmodel: lógica de presentación y estado de las pantallas.

# \- data: acceso a datos y configuración de Room.

# \- util: validaciones y funciones auxiliares.

# 

# Room se utiliza para guardar la información local de los usuarios. Los ViewModel coordinan la lógica de presentación entre las vistas y las fuentes de datos.

# 

# 

# API EXTERNA

# \-----------

# La aplicación consume JSONPlaceholder (https://jsonplaceholder.typicode.com), un servicio de prueba para APIs REST.

# 

# Operaciones implementadas:

# 

# 1\. GET  /posts

# 2\. GET  /posts/1

# 3\. GET  /comments

# 4\. GET  /albums

# 5\. GET  /photos

# 6\. GET  /todos

# 7\. POST /posts

# 

# Las solicitudes GET consultan recursos de ejemplo. La solicitud POST demuestra el envío de datos. JSONPlaceholder es un servicio de prueba: las publicaciones enviadas mediante POST no quedan almacenadas de forma permanente en el servidor.

# 

# 

# PRUEBAS UNITARIAS

# \-----------------

# El proyecto contiene pruebas unitarias para las validaciones de datos, incluyendo casos válidos e inválidos.

# 

# En Windows, desde la carpeta raíz del proyecto, se pueden ejecutar con:

# 

# &#x20;   .\\gradlew.bat :app:testDebugUnitTest

# 

# En macOS o Linux:

# 

# &#x20;   ./gradlew :app:testDebugUnitTest

# 

# 

# RECURSOS NATIVOS

# \----------------

# \- GPS/ubicación: permite mostrar la ubicación en la pantalla de mapa. Android puede solicitar permiso de ubicación al usuario.

# \- Cámara: utiliza la cámara del dispositivo como recurso nativo. Android puede solicitar permiso de cámara al usuario.

# 

# 

# REQUISITOS

# \----------

# \- Android Studio compatible con la versión de Android Gradle Plugin configurada en el proyecto.

# \- Android SDK instalado.

# \- JDK configurado en Android Studio.

# \- Emulador Android o dispositivo físico.

# \- Conexión a Internet para sincronizar Gradle y utilizar la API externa.

# \- Permisos de ubicación y cámara concedidos al ejecutar las funciones correspondientes.

# 

# 

# CONFIGURACIÓN INICIAL

# \---------------------

# 1\. Clonar el repositorio:

# 

# &#x20;      git clone https://github.com/jose-miranda-plip/DesarrolloMovil.git

# 

# 2\. Abrir en Android Studio la carpeta del proyecto clonado.

# 

# 3\. Esperar a que termine la sincronización de Gradle.

# 

# 4\. Configurar la ruta del SDK de Android localmente. Android Studio normalmente crea el archivo local.properties. Si es necesario, debe estar en la raíz del proyecto y contener una ruta válida, por ejemplo:

# 

# &#x20;      sdk.dir=C:/Android/Sdk

# 

# &#x20;  La ruta debe coincidir con la ubicación real del SDK en ese computador. No se debe subir local.properties al repositorio porque contiene una ruta específica del equipo.

# 

# 5\. Si la aplicación requiere una clave de Google Maps, agregarla en local.properties usando el nombre que lee el build.gradle.kts del módulo app:

# 

# &#x20;      MAPS\_API\_KEY=TU\_CLAVE

# 

# &#x20;  No publicar claves privadas en el repositorio. Si el mapa de la versión actual usa una ubicación de ejemplo sin Google Maps, esta clave no será necesaria para esa pantalla.

# 

# 6\. Seleccionar un emulador o conectar un dispositivo Android y ejecutar la configuración app.

# 

# 

# GENERAR APK FIRMADO (RELEASE)

# \-----------------------------

# En Android Studio:

# 

# 1\. Abrir Build > Generate Signed Bundle / APK.

# 2\. Seleccionar APK y avanzar.

# 3\. Seleccionar un keystore existente o crear uno nuevo.

# 4\. Ingresar la contraseña y los datos solicitados.

# 5\. Seleccionar la variante release.

# 6\. Finalizar el asistente y esperar la generación del APK.

# 

# El APK firmado se genera normalmente dentro de:

# 

# &#x20;   app/release/

# 

# La ubicación exacta aparece al finalizar el asistente de Android Studio.

# 

# El archivo del keystore (.jks o .keystore) y sus contraseñas son privados. No deben subirse al repositorio ni compartirse junto con el APK. Guardar una copia segura del keystore, ya que se necesita para publicar futuras actualizaciones firmadas con la misma identidad.

# 

# 

# GIT Y TRABAJO COLABORATIVO

# \--------------------------

# El código fuente se mantiene en GitHub:

# 

# &#x20;   https://github.com/jose-miranda-plip/DesarrolloMovil

# 

# Se recomienda que ambos integrantes mantengan el repositorio actualizado, trabajen con commits claros y registren en Trello las tareas realizadas y pendientes.

# 

# 

# TECNOLOGÍAS

# \-----------

# \- Kotlin

# \- Android Studio

# \- Jetpack Compose

# \- Room

# \- MVVM

# \- Retrofit y conversión JSON, si están configurados en la versión actual del proyecto

# \- JSONPlaceholder para las solicitudes HTTP

# \- JUnit para pruebas unitarias

# \- Google Maps/servicios de ubicación, según la configuración activa del proyecto

