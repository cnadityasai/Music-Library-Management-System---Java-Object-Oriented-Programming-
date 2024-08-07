import java.util.ArrayList;

public class MusicLibrary {
    // An ArrayList to store all the songs in the library
    public ArrayList<MusicTrack> songs;

    /*
        Constructor for the MusicLibrary class
        Initiates the ArrayList<MusicTrack> songs
     */
    public MusicLibrary() {
        songs = new ArrayList<MusicTrack>();
    }

    /*
        Adds a whole album to the music library
        @param inputSongs ArrayList of album consisting of MusicTracks
     */
    public void addAlbum(ArrayList<MusicTrack> inputSongs) {
        songs.addAll(inputSongs);
    }

    /*
        Adds a song to the music library
     */
    public void addSong(MusicTrack song) {
        songs.add(song);
    }

    // Displays the songs with a rating of 1
    public void lowestRating(){
        for(MusicTrack song: songs) {
            if(song.rating == 1)
                System.out.println(song.title);
        }
    }

}
