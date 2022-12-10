package id.ac.umn.uts_00000048162_arnoldusyitzhakpetramanoppo;

public class ModelVideo {
    private String videoName, description;
    private int linkPhoto, linkVideo;

    public ModelVideo(){

    }

    public ModelVideo(String videoName, String description, int linkPhoto, int linkVideo){
        this.videoName = videoName;
        this.description = description;
        this.linkPhoto = linkPhoto;
        this.linkVideo = linkVideo;
    }

    public String getVideoName(){
        return this.videoName;
    }

    public int getLinkPhoto(){
        return this.linkPhoto;
    }

    public int getLinkVideo(){
        return this.linkVideo;
    }

    public String getDescription(){
        return this.description;
    }
}
