package Playlist;

public class Song {
    private int track;
    private String title;
    private double duration;
    private boolean inPlaylist;

    public Song(int track, String title, int duration) {
        this.title = title;
        this.duration = duration;
        this.inPlaylist = false;
    }

    public int getTrack() {
        return track;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }

    public boolean isInPlaylist() {
        return inPlaylist;
    }

    public void setInPlaylist(boolean inPlaylist) {
        this.inPlaylist = inPlaylist;
    }
}
