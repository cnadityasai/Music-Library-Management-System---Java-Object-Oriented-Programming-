import java.util.ArrayList;

/* Compilation album same as the class Albums with added methods
    Uses inheritance to inherit from the class Albums
    Unlike Albums, Compilation albums store songs with different artists
 */
public class CompilationAlbum extends Albums{
    // List to store the Albums
    ArrayList<Albums> albums;

    /*
        Constructor for the CompilationAlbum
        @param albums input album
     */
    public CompilationAlbum(ArrayList<Albums> albums){
        this.albums = albums;
    }
    /*
        Adds songs to the compilation album
        @param song input song to add
     */
    public void addSongs(MusicTrack song) {
        songs.add(song);
    }

    /*
        Displays the album that the Music Track that was
        orginally a part of
     */
    public void displayOldAlbum() {
        for(MusicTrack song: songs){
            // Calls the method that finds the original album
            findAlbum(albums,song);
        }
    }

    /*
        Finds the album that the song originally is present in
        @param albums all the songs present in the compilation album
        @param song song to find the album of
     */
    public String findAlbum(ArrayList<Albums> albums, MusicTrack song){
        for(Albums album: albums){
            for(MusicTrack currentsong: album.songs) {
                if(currentsong.title.equals(song.title)){
                    return album.name + "'s album";
                }
            }
        }
        return "";
    }
}
