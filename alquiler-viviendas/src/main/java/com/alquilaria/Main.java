package com.alquilaria;

import com.alquilaria.view.MenuPrincipal;

public class Main {

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        // Iniciar la aplicación mostrando el menú principal
        MenuPrincipal menu = new MenuPrincipal();
        menu.mostrar();

    }
}