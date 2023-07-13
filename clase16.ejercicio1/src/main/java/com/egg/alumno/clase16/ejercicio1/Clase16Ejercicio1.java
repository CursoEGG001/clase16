/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.egg.alumno.clase16.ejercicio1;

import libreria.servicios.AutorServicios;

/**
 *
 * @author pc
 */
public class Clase16Ejercicio1 {

    public static void main(String[] args) {
        AutorServicios au = new AutorServicios();
        System.out.println("{[Create un Autor]}");
        au.crearAutor();
        System.out.println("{[Buscate un Autor]}");
        System.out.println(au.buscarAutor());
        System.out.println("{[Borrate un autor]}");
        au.eliminarAutor();
        System.out.println("{[Cambiate un Autor]}");
        au.modificarAutor();
    }
}
