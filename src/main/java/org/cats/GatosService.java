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
                    " 1. Ver otra imagen \n" +
                    " 2. Favorito \n" +
                    " 3. Volver \n";
            String [] botones = {"Ver otra imagen", "Favorito", "Volver"};
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
                default: System.out.println("End of the program. Have a nice day and a wonderful week");
            }

        } catch (IOException ioe){
            System.out.println("Error con la imagen : {}" + ioe);
        }
    }

    public static void favoritoGato(Gatos gato) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n    \"image_id\":\""+gato.getId()+"\"\r\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gato.getApikey())
                    .build();
            Response response = client.newCall(request).execute();
        } catch(IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void verFavoritos(String apikey) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", apikey)
                .build();
        Response response = client.newCall(request).execute();

        String parseJson = response.body().string();

        Gson gson = new Gson();

        GatosFav[] gatosArray = gson.fromJson(parseJson, GatosFav[].class);

        if (gatosArray.length > 0) {
            int min = 1;
            int max = gatosArray.length;
            int aleatorio = (int) (Math.random() * ((max - min) + 1)) + min;
            int indice = aleatorio - 1;

            GatosFav gatoFav = gatosArray[indice];
            Image image = null;
            try {
                URL url = new URL(gatoFav.image.getUrl());
                image = ImageIO.read(url);

                ImageIcon fondoGato = new ImageIcon(image);

                if (fondoGato.getIconWidth() > 800) {
                    Image fondo = fondoGato.getImage();
                    Image modificada = fondo.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                    fondoGato = new ImageIcon(modificada);
                }
                String menu = "Opciones: \n" +
                        " 1. Ver otra imagen \n" +
                        " 2. Eliminar Favorito \n" +
                        " 3. Volver \n";
                String [] botones = {"Ver otra imagen", "Eliminar Favorito", "Volver"};
                String idGato = gatoFav.getId();
                String opcion = (String) JOptionPane.showInputDialog(null, menu, idGato,
                        JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

                int seleccion = -1;

                for(int i = 0; i < botones.length; i++) {
                    if (opcion.equals(botones[i])) {
                        seleccion = i;
                    }
                }

                switch (seleccion) {
                    case 0: verFavoritos(apikey);
                    case 1: borrarFavorito(gatoFav);
                    default: System.out.println("End of the program. Have a nice day and a wonderful week");
                }

            } catch (IOException ioe){
                System.out.println("Error con la imagen : {}" + ioe);
            }
        }
    }

    private static void borrarFavorito(GatosFav gatoFav) {
    }
}
