package org.cats;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class GatosService {

    public static void verGatos() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search?")
                .build();
        Response response = client.newCall(request).execute();

        String responseJson = response.body().string();
        responseJson = responseJson.substring(1, responseJson.length());
        responseJson = responseJson.substring(0, responseJson.length() - 1);

        Gson gson = new Gson();
        Gatos gatos = gson.fromJson(responseJson, Gatos.class);

        Image image = null;
        try {
            URL url = new URL(gatos.getUrl());
            image = ImageIO.read(url);

            ImageIcon fondoGato = new ImageIcon(image);

            if (fondoGato.getIconWidth() > 800) {
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            }
            String menu = "Opciones: \n" +
                    " 1. ver otra imagen \n" +
                    " 2. favorito \n" +
                    " 3. Volver \n";
            String [] botones = {"ver otra imagen", "favorito", "volver"};
            String idGato = gatos.getId();
            String opcion = (String) JOptionPane.showInputDialog(null, menu, idGato,
                    JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

            int seleccion = -1;

            for(int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])) {
                    seleccion = i;
                }
            }

            switch (seleccion) {
                case 0: verGatos();
                case 1: favoritoGato(gatos);
                default: System.out.println("End of the program. Have a nice day");
            }

        } catch (IOException ioe){
            System.out.println("Error con la imagen : {}" + ioe);
        }
    }

    public static void favoritoGato(Gatos gato) {

    }

}
