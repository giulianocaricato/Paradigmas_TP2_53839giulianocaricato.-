# Paradigmas_TP2_53839giulianocaricato.-

**Universidad:** UTN - Facultad Regional Mendoza  
**Materia:** Paradigmas de Programación  
**Tema:** Programación Orientada a Objetos en Java

## Descripción

Sistema de gestión de eventos universitarios desarrollado en Java. Permite administrar eventos, salas, actividades, estudiantes e inscripciones.

El proyecto incorpora excepciones, persistencia de objetos, interfaces, métodos genéricos, clases anidadas e hilos.

## Ejercicio 1: Excepciones y serialización

- Organización de las clases mediante packages.
- Implementación de CupoExcedidoException.
- Manejo de excepciones con try-catch-finally.
- Serialización y deserialización de eventos.
- Validación de los datos de los estudiantes.

## Ejercicio 2: Interfaces

- Implementación de la interfaz Certificable.
- Incorporación de la clase Curso.
- Generación de certificados para talleres y cursos.
- Las charlas no son certificables.

## Ejercicio 3: Genéricos

Implementación de métodos genéricos para filtrar actividades por tipo y calcular sus costos de materiales.

Se utilizan métodos parametrizados acotados y wildcards.

## Ejercicio 4: Clases anidadas e hilos

- Implementación de TicketDeAcceso como clase anidada de Inscripcion.
- Generación de tickets para inscripciones confirmadas.
- Implementación de EnvioTicketsThread.
- Simulación del envío concurrente de tickets mediante mensajes de consola.

## Ejecución

1. Abrir el proyecto en IntelliJ IDEA.
2. Configurar el JDK correspondiente.
3. Ejecutar el método main de App.java.

## Persistencia

Los eventos se guardan en archivos .dat mediante serialización de objetos Java.

## Tecnologías utilizadas

- Java
- IntelliJ IDEA
- Programación Orientada a Objetos
