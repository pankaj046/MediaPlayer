package sharma.pankaj.mediaplayer.model;

import android.graphics.Bitmap;

public class VideoModel {

    private String name;
    private String duration;
    private String path;
    private Bitmap thumbNail;

    public VideoModel(String name, String duration, String path, Bitmap thumbNail) {
        this.name = name;
        this.duration = duration;
        this.path = path;
        this.thumbNail = thumbNail;
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

    public Bitmap getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(Bitmap thumbNail) {
        this.thumbNail = thumbNail;
    }
}
