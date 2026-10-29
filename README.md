# CENAMAD – Conocimiento sobre madera

Aplicación móvil Android desarrollada con Kotlin y Jetpack Compose para la asignatura
**DSY1105 – Desarrollo de Aplicaciones Móviles** (Evaluación Parcial 2).

## Integrantes
- [Nombre integrante 1] – Registro y base de datos
- [Nombre integrante 2] – Login, perfil y recursos nativos

## Descripción
CENAMAD entrega información sobre tipos de madera y permite a los usuarios crear una cuenta,
iniciar sesión, administrar su perfil y consultar ubicaciones mediante GPS.

## Funcionalidades
- Pantalla de inicio con acceso a información de maderas y a inicio de sesión.
- Registro de usuarios con validación por campo (nombre, correo, contraseña y confirmación),
  mensajes de error animados, mostrar/ocultar contraseña e indicador de carga.
- Almacenamiento local con Room; las contraseñas se guardan como hash SHA-256.
- Control de correos duplicados al registrar.
- Inicio de sesión contra la base de datos local.
- Perfil de usuario con foto desde la cámara (permiso en tiempo de ejecución).
- Pantalla con GPS / Google Maps (permiso en tiempo de ejecución).

## Tecnologías
Kotlin · Jetpack Compose (Material 3) · Room · ViewModel · Coroutines · Google Maps Compose · Play Services Location

## Cómo ejecutar
1. Clonar el repositorio y abrir la carpeta `MyApplication` en Android Studio.
2. Agregar la clave de Google Maps en `local.properties`: `MAPS_API_KEY=tu_clave_aqui`
3. Esperar la sincronización de Gradle.
4. Elegir un emulador o dispositivo con Android 7.0 (API 24) o superior y presionar Run.