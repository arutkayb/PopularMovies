package nd.centertableinc.popularmovies1.data.movie;

public class MovieTrailer {
    private String id;
    private String key;
    private String name;
    private String type;
    private String site;

    public MovieTrailer(String id, String key, String name, String type, String site) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.type = type;
        this.site = site;
    }

    public MovieTrailer(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
