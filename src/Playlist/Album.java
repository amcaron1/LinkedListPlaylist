package Playlist;

import java.util.ArrayList;

public class Album {
    private String name;
    private String artist;
    private ArrayList<Song> albumSongs;

    public Album(String name, String artist, ArrayList<Song> albumSongs) {
        this.name = name;
        this.artist = artist;
        this.albumSongs = albumSongs;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public ArrayList<Song> getAlbumSongs() {
        return albumSongs;
    }
}
