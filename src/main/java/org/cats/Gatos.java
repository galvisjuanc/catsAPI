package org.cats;

import io.github.cdimascio.dotenv.Dotenv;

public class Gatos {

    Dotenv dotenv = Dotenv.load();

    String id;
    String url;
    String apikey = dotenv.get("API_KEY");
    String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
