package Playlist;

import java.util.*;

public class Main {

    public static ArrayList<Album> albumList = new ArrayList<Album>();
    public static Scanner scanner = new Scanner(System.in);
    public static LinkedList<Song> playlist = new LinkedList<Song>();


    public static void main(String[] args) {
        createAlbumList();
        displayMainMenu();
        handleMainMenu(); //Single MainMenu mod
    }

    public static void createAlbumList() {
        Song song1a = new Song(1, "Come Together", 186);
        Song song1b = new Song(2, "Here Comes The Sun", 180);
        Song song1c = new Song(3, "Carry That Weight", 190);
        ArrayList<Song> albumSongs1 = new ArrayList<Song>();
        albumSongs1.add(song1a);
        albumSongs1.add(song1b);
        albumSongs1.add(song1c);
        Album album1 = new Album("Abbey Road", "The Beatles", albumSongs1);

        Song song2a = new Song(1, "Ain't That A Shame", 310);
        Song song2b = new Song(2, "I Want You To Want Me", 218);
        Song song2c = new Song(3, "Surrender", 280);
        ArrayList<Song> albumSongs2 = new ArrayList<Song>();
        albumSongs2.add(song2a);
        albumSongs2.add(song2b);
        albumSongs2.add(song2c);
        Album album2 = new Album("Cheap Trick At Budokan", "Cheap Trick", albumSongs2);

        Song song3a = new Song(1, "Billie Jean", 186);
        Song song3b = new Song(2, "Beat It", 180);
        Song song3c = new Song(3, "Thriller", 190);
        ArrayList<Song> albumSongs3 = new ArrayList<Song>();
        albumSongs3.add(song3a);
        albumSongs3.add(song3b);
        albumSongs3.add(song3c);
        Album album3 = new Album("Thriller", "Michael Jackson", albumSongs3);

        albumList.add(album1);
        albumList.add(album2);
        albumList.add(album3);
    }

    public static void displayMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("0 - Quit");
        System.out.println("1 - Add to playlist");
        System.out.println("2 - Play songs");
       //handleMainMenu(); //Single MainMenu mod
    }

    public static void handleMainMenu() {
        boolean quit = false; //Single MainMenu mod
        while (!quit) { //Single MainMeun mod
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Shutting down playlist");
                    quit = true; //Single MainMenu mod
                    break;
                case 1:
                    displayAlbumMenu();
                    break;
                case 2:
                    if (playlist.isEmpty()) {
                        System.out.println("No songs in the playlist");
                        displayMainMenu();
                    } else {
                        displayPlaylistMenu();
                        handlePlaylistMenu();
                    }
                    break;
            }
        }
    }

    public static void displayAlbumMenu() {
        System.out.println("Album Menu:");
        for (int i = 0; i < albumList.size(); i++) {
            System.out.println(i + ": " + albumList.get(i).getName());
        }
        System.out.println(albumList.size() + ": Return to Main Menu");
        handleAlbumMenu();
    }

    public static void handleAlbumMenu() {
        int albumIndex = scanner.nextInt();
        if (albumIndex == albumList.size()) {
            displayMainMenu();
        } else {
            ArrayList<Song> albumSongs = albumList.get(albumIndex).getAlbumSongs();
            displaySongMenu(albumSongs);
        }
    }

    public static void displaySongMenu(ArrayList<Song> albumSongs) {
        System.out.println("Song Menu");
        for (int i = 0; i < albumSongs.size(); i++) {
            System.out.println(i + ": " + albumSongs.get(i).getTitle());
        }
        System.out.println(albumSongs.size() + ": Return to Album Menu");
        handleSongMenu(albumSongs);
    }

    public static void handleSongMenu(ArrayList<Song> albumSongs) {
        int songIndex = scanner.nextInt();
        if (songIndex == albumSongs.size()) {
            displayAlbumMenu();
        } else {
            if (!albumSongs.get(songIndex).isInPlaylist()) {
                playlist.add(albumSongs.get(songIndex));
                albumSongs.get(songIndex).setInPlaylist(true);
                System.out.println(albumSongs.get(songIndex).getTitle() +
                        " added to playlist");
            } else {
                System.out.println(albumSongs.get(songIndex).getTitle() +
                        " already in playlist");
            }
           displaySongMenu(albumSongs);
        }
    }

    public static void displayPlaylistMenu() {
        System.out.println("Playlist Menu:");
        System.out.println("0 - Play Next");
        System.out.println("1 - Play Previous");
        System.out.println("2 - Replay Current");
        System.out.println("3 - Remove Current");
        System.out.println("4 - Print Playlist");
        System.out.println("5 - Print Playlist Menu");
        System.out.println("6 - Return to Main Menu");
    }

    public static void handlePlaylistMenu() {
        boolean quit = false;
        boolean goingForward = true;
        ListIterator<Song> listIterator = playlist.listIterator();

        while (!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch(action) {
                case 0:
                    if(!goingForward) {
                        if(listIterator.hasNext()) {
                            listIterator.next();
                        }
                        goingForward = true;
                    }
                    if(listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next().getTitle());
                    } else {
                        System.out.println("We are at the end of the list");
                        goingForward = false;
                    }
                    break;

                case 1:
                    if(goingForward) {
                        if(listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        goingForward = false;
                    }
                    if(listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous().getTitle());
                    } else {
                        System.out.println("We are at the start of the list");
                        goingForward = true;
                    }
                    break;

                case 2:
                    if(goingForward) {
                        if(listIterator.hasPrevious()) {
                            System.out.println("Replaying " +
                                    listIterator.previous().getTitle());
                            goingForward = false;
                        } else {
                            System.out.println("We are at the start of the list");
                        }
                    } else {
                        if(listIterator.hasNext()) {
                            System.out.println("Replaying " +
                                    listIterator.next().getTitle());
                            goingForward = true;
                        } else {
                            System.out.println("We are at the end of the list");
                        }
                    }
                    break;

                case 3:
                    if(goingForward) {
                        if(listIterator.hasPrevious()) {
                            Song previousSong = listIterator.previous();
                            System.out.println(previousSong.getTitle() +
                                    " removed from playlist");
                            previousSong.setInPlaylist(false);
                            listIterator.remove();
                        } else {
                            System.out.println("We are at the start of the list");
                        }
                    } else {
                        if(listIterator.hasNext()) {
                            Song nextSong = listIterator.next();
                            System.out.println(nextSong.getTitle() +
                                    " removed from playlist");
                            nextSong.setInPlaylist(false);
                            listIterator.remove();
                        } else {
                            System.out.println("We are are the end of the list");
                        }
                    }
                    break;

                case 4:
                    printPlaylist();
                    break;

                case 5:
                    displayPlaylistMenu();
                    break;

                case 6:
                    quit = true;
                    break;
            }
        }
        displayMainMenu();
    }

    public static void printPlaylist() {
        System.out.println("Playlist:");
        Iterator<Song> i= playlist.iterator();
        int j = 1;
        while(i.hasNext()) {
            System.out.println(j + ": " + i.next().getTitle());
            j++;
        }
    }
}
