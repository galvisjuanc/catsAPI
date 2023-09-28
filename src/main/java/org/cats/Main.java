package org.cats;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int opcion_menu = -1;
        String [] botones = {
                "1. ver gatos",
                "2. salir"
        };

        do {
            String opcion = (String) JOptionPane.showInputDialog(null, "Gatitos java",
                    "Menu Principal", JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);

            for(int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])) {
                    opcion_menu = i;
                }
            }

            switch (opcion_menu){
                case 0: GatosService.verGatos();
                default: break;
            }

        } while(opcion_menu != 1);
    }
}