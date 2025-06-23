
package musclemind.media;

public class Video {
    private String url;
    private String descripcion;

    public Video(String url, String descripcion) {
        this.url = url;
        this.descripcion = descripcion;
    }

    public String getUrl() { return url; }
    public String getDescripcion() { return descripcion; }

    public void verVideo() {
        System.out.println("Reproduciendo video: " + descripcion + " (" + url + ")");
    }
}
