package org.cats;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int opcionMenu = -1;
        String [] botones = {
                "1. Ver gatos",
                "2. Ver Favoritos",
                "3. Salir"
        };

        do {
            String opcion = (String) JOptionPane.showInputDialog(null, "Gatitos java",
                    "Menu Principal", JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);

            for(int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])) {
                    opcionMenu = i;
                }
            }

            switch (opcionMenu){
                case 0: GatosService.verGatos();
                case 1: {
                    Gatos gato = new Gatos();
                    GatosService.verFavoritos(gato.getApikey());
                }
                default: break;
            }

        } while(opcionMenu != 1);
    }
}