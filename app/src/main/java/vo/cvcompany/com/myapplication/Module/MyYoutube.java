package vo.cvcompany.com.myapplication.Module;

/**
 * Created by kh on 8/17/2017.
 */

public class MyYoutube {
    private String api;
    private String title;
    private String url;

    public MyYoutube(){

    }

    public MyYoutube(String api, String title, String url) {
        this.api = api;
        this.title = title;
        this.url = url;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
