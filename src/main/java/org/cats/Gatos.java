package org.cats;

public class Gatos {

    int id;
    String url;
    String apikey = "live_XsmcVUTnO8YgL9yF52JXMKjRATMF0BCZohWRR8fRibZeycdv8No9CJNcxqvyB5Yd";
    String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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