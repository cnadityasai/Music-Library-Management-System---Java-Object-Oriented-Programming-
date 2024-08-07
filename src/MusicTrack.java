import java.io.File;
import java.util.Date;
import java.util.ArrayList;

public class MusicTrack {
    // The track title
    public String title;
    // The artist
    public Artists artist;
    // Date of the track
    private String date;
    // Length of the track
    public String length;
    // Rating of the track
    public int rating;
    // File path of the track
    public String location;
    // File size of the track
    public float size;
    // Number of times the track has been played
    private int count;
    // Stores the guest artists for the track
    private ArrayList<Artists> guestArtist;
    // Object of the Album class
    private Albums album;

    // Constructor for the MusicTrack class
    public MusicTrack() {
        guestArtist = new ArrayList<Artists>();
        album = new Albums();
        count = 0;
    }

    /* Parameterised constructor for MusicTrack class
    * @param artist The artist
    * @param title Track's title
    * @param filename Name of the file
     */
    public MusicTrack(String artist, String title, String filepath) {
        this.title = title;
        this.location = filepath;
        this.artist = new Artists(artist);
    }

    /*
        Sets the details of the Track
        @param title Track's title
        @param artistName The artist
        @param date Track's date
        @param length running time of the track
        @param rating Track's rating
        @param location Track's file path
        @param size Track's file size
     */
    public void setDetails(String title, String artistName, String date, String length, int rating, String location, float size) {
        this.title = title;
        this.artist = new Artists(artistName);
        this.date = date;
        this.length = length;
        this.rating = rating;
        this.location = location;
        this.size = size;
    }

    /*
        Prints the details of
         a track containing the details
     */
    public void getDetails(){
        System.out.println("Title: "+title);
        System.out.println("Artists: ");
        System.out.println("Artist: "+artist.getName());
        System.out.println("Date: "+date);
        System.out.println("Length: "+length+ " mins");
        System.out.println("Rating: "+rating);
        System.out.println("Location: "+location);
        System.out.println("Size: "+size+ " mb");
    }

    /*
        Adds the guest artist for the track
     */
    public void addGuestArtist(Artists guestArtist) {
        this.guestArtist.add(guestArtist);
    }

    /*
        Prints the artists of the track including the
        guest artists if present
     */
    public void getArtists(){
        System.out.println(artist.getName());
        if(guestArtist.isEmpty()) {
            System.out.println("No guest Artists");
        }
        else{
            for(Artists artist: this.guestArtist) {
                System.out.println(artist.getName());
            }
        }
    }

    // Increments the count variable each time
    public void play(){
        count++;
    }

    /*
        Returns the number of times a track is played
        @return count
     */
    public int playCount(){
        return count;
    }

    /*
        Returns the title of the track
        @return title
     */
    public String getTitle(){
        return title;
    }
}
