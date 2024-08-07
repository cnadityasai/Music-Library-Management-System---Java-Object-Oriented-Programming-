import java.util.ArrayList;

public class Albums {
    // Album's name
    public String name;
    // Album's type
    private String type;
    // Object of the artist
    private Artists artist;
    // ArrayList of MusicTrack to store inside the album
    public ArrayList<MusicTrack> songs;

    /*
        Creates the album, ready to add the songs to the album
     */
    public Albums(){
        songs = new ArrayList<MusicTrack>();
    }

    /*
        Sets the details of the album
        @param name album's name
        @param type album's type
        @param artist Album's artist (object of class Artists)
     */
    public void setDetails(String name, String type, Artists artist) {
        this.name = name;
        this.type = type;
        this.artist = artist;
    }

    /*
        Prints the details of the album
     */
    public void getDetails(){
        System.out.println("Name: "+name);
        System.out.println("Type: "+type);
        System.out.println("Artist: "+artist.getName());
    }

    /*
        Adds the tracks with the artist of the current album into the album
        @param allSongs Songs that has to be added into the album
     */
    public void addTracks(ArrayList<MusicTrack> allSongs) {
        for(MusicTrack song: allSongs){
            /* Adds songs to album if the artist of the song matches with
                the artist of the current album
             */
            if(song.artist.getName().equals(this.artist.getName())){
                songs.add(song);
            }
        }
    }

    /*
        Returns the total running time of the album
        @return total
     */
    public String getRunningTime() {
        //int totalhr
        int totalmin = 0;
        int totalsec = 0;
        String total;
        for(MusicTrack song: songs) {
            String[] arr = song.length.split(":");
            int min = Integer.parseInt(arr[0]);
            int secs = Integer.parseInt(arr[1]);
            totalmin += min;
            totalsec += secs;
            if(totalsec>=60){
                totalmin += 1;
                totalsec = totalsec - 60;
            }
        }
        String sec = String.valueOf(totalsec);
        if(sec.length() == 1){
            sec = "0" + sec;
        }
        total = totalmin + "m" + ":" + sec + "s";
        return total;
    }

    /*
        Returns the total size of the album
        @return total total file size of all the songs summed together
     */
    public float getTotalSize() {
        float total = 0;
        for(MusicTrack song: songs) {
            total+= song.size;
        }
        return total;
    }

    /*
        Returns the average rating of the album
        @return total returns the average rating of all the songs present in the album
     */
    public float getAverageRating() {
        float total = 0;
        for(MusicTrack song: songs) {
            total+= song.rating;
        }
        return total/songs.size();
    }

    /*
        Displays all the songs that are present in the album
     */
    public void printSongs(){
        for(MusicTrack song: songs){
            System.out.println(song.title);
        }
    }

    /*
        Sets the album type
     */
    public void setType(String type){
        this.type = type;
    }

    /*
        Returns the type of the album
        @return type returns the album type
     */
    public String getType(){
        return type;
    }
}
