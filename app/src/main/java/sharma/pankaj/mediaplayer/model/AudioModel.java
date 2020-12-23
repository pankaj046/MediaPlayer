package sharma.pankaj.mediaplayer.model;

public class AudioModel {

    private String name;
    private String duration;
    private String path;

    public AudioModel(String name, String duration, String path) {
        this.name = name;
        this.duration = duration;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
