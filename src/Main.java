import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // ArrayList to store object of all the added songs
        ArrayList<MusicTrack> everySong = new ArrayList<MusicTrack>();
        // Arraylist to store albums that are created
        ArrayList<Albums> albums = new ArrayList<>();

        /*
            Creating multiple files to output the results to
            each file
         */
        File albumFile = new File("albums.txt");
        if(albumFile.createNewFile()){
            System.out.println("File Created Successfully");
        }
        else {
            System.out.println("File Already Exists/File Not Created");
        }

        File musicLibrary = new File("MusicLibrary.txt");
        if(musicLibrary.createNewFile()){
            System.out.println("File Created Successfully");
        }
        else {
            System.out.println("File Already Exists/File Not Created");
        }

        File compilationAlbum = new File("Compilation Album.txt");
        if(compilationAlbum.createNewFile()){
            System.out.println("File Created Successfully");
        }
        else {
            System.out.println("File Already Exists/File Not Created");
        }
        File optimalBins = new File("Optimal bins.txt");
        if(optimalBins.createNewFile()){
            System.out.println("File Created Successfully");
        }
        else {
            System.out.println("File Already Exists/File Not Created");
        }

        // Creates the object of TrackReader class
        TrackReader reader1 = new TrackReader();
        // Adding the mp3 songs that were present in the audio folder to the ArrayList
        everySong.addAll(reader1.readTracks("audio", ".mp3"));
        // Creating and initiating the variable for iterating through the array
        int count =0;

        /* Traversing through the ArrayList which contains MusicTrack objects
         to set the details for each track from JSON File
         */
        for(MusicTrack song: everySong) {
            // Creating a reader object to read the JSON file
            FileReader reader = new FileReader("src/files.json");
            /* Creating a tokener object to convert strings present in JSON file
             to tokens */
            JSONTokener tokener = new JSONTokener(reader);
            /* Creating a JSONArray object to read the array elements present in
            JSON file */
            JSONArray jsonArray = new JSONArray(tokener);

            /* Traversing through each array element in JSON file and collecting the
                details that are present in the array element and storing it in temporary
                variables */
            if(count<jsonArray.length()) {
                JSONObject obj = jsonArray.getJSONObject(count);
                String jsontitle = obj.getString("title");
//              String jsonartist = obj.getString("artist");
                String jsondate = obj.getString("date");
                String jsonlength = obj.getString("length");
                int jsonrating = obj.getInt("rating");
                float jsonsize = obj.getFloat("size");
                if(jsontitle.equals("closer")){
                    song.addGuestArtist(new Artists("Halsey"));
                }
                /* Calling the setDetails method that takes the values and assigns it to
                    the current MusicTrack object */
                song.setDetails(jsontitle,song.artist.getName(),jsondate,jsonlength,jsonrating,song.location, jsonsize);
                count++;
            }
            // if index exceeds the size of the ArrayList the for loop breaks
            else
                break;
            reader.close();
            System.out.println("Importing Music Track Successful");
        }
        System.out.println();
        System.out.println("Printing all songs: ");
        for(MusicTrack song: everySong){
            song.getDetails();
            System.out.println();
        }
        System.out.println();


        // Creating MusicLibrary object to add all the songs to the library
        MusicLibrary allSongs = new MusicLibrary();
        // Calling the addAlbum function which adds all the Music tracks to the library
        allSongs.addAlbum(everySong);
        // Prints the songs that has the rating of 1
        allSongs.lowestRating();

        FileWriter fileWriter = new FileWriter("MusicLibrary.txt");
        fileWriter.write("List of music tracks in music library (Track Name - Track's rating)" + "\n");
        fileWriter.write("-------------------------------------------------------------------\n");
        for(MusicTrack song: allSongs.songs){
            fileWriter.write(song.getTitle() + " - " + song.rating + "\n");
        }
        fileWriter.close();

        // Creates album for each author
        for(MusicTrack song: everySong) {
            Albums a1 = new Albums();
            a1.setDetails(song.artist.getName(), "Mini Album", song.artist);
            a1.addTracks(everySong);
            albums.add(a1);
        }
        // Deletes any duplicate albums that are present
        for(int i=0;i<albums.size()-1;i++) {
            if(albums.get(i).name.equals(albums.get(i+1).name)) {
                albums.remove(i);
            }
        }

//        for(MusicTrack song: everySong) {
//
//            //fileWriter.write(song.getTitle() + "\n");
//           // System.out.println("---------------------------------");
//        }

        fileWriter = new FileWriter("albums.txt");
        // Prints all the albums that are present including its details
        fileWriter.write("All the created albums are listed Below(Album name - Running time):\n");
        fileWriter.write("-------------------------------------------------------------------\n");
        System.out.println("List of albums: ");
        for(Albums album: albums){
            fileWriter.write(album.name + " - " + album.getRunningTime() + "\n");
            album.getDetails();
            System.out.println("Total Size of album: " + album.getTotalSize() + "mb");
            System.out.println("Average Rating of album: " + album.getAverageRating());
            System.out.println("Songs present in the album: ");
            album.printSongs();
            System.out.println(album.getRunningTime());
            System.out.println("---------------------------------");
        }
        fileWriter.close();

        // Creating a CompilationAlbum object to create a new compilation album
        CompilationAlbum c1 = new CompilationAlbum(albums);

        // Adding half of the songs that were added
        for(int i=0;i<everySong.size()/2;i++){
            c1.addSongs(everySong.get(i));
            c1.setType("Compilation Album");
        }
        // Prints all the songs that are present in the compilation album
        fileWriter = new FileWriter("Compilation Album.txt");
        fileWriter.write("All the songs present in the compilation album(Track Name - Original Album Name):\n");
        fileWriter.write("---------------------------------------------------------------------------------\n");
        for(MusicTrack song: c1.songs) {
            fileWriter.write(song.getTitle() + " - " + c1.findAlbum(c1.albums,song) + "\n");
        }
        fileWriter.close();



        /* Prints all the songs in the compilation album including its
            original album that it is originally present in
         */
        c1.displayOldAlbum();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the maximum limit for each CD/DVD(size in MB): ");
        int maximumLimit = scanner.nextInt();

        fileWriter = new FileWriter("Optimal bins.txt");
        String optimal = Integer.toString(BinPackingProblem(everySong,maximumLimit));
        // Outputs the result to the file optimalBins
        fileWriter.write("The optimal number of Disks with size " + maximumLimit + " each: ");
        fileWriter.write(optimal);
        fileWriter.close();

        play(everySong.get(4));

        // Prints the optimal number of CDs/DVDs that the songs can be fit in
        System.out.println("Optimal Number of Disks: " + BinPackingProblem(everySong,maximumLimit));
        System.out.println();

    }

    public static int BinPackingProblem(ArrayList<MusicTrack> songs, float maximumLimit) {
        // Creating a ArrayList to store the file size of all the songs
        ArrayList<Float> fileSize = new ArrayList<Float>();
        // Initialising the number of CDs/DVDs(bins) to zero
        int bin=0;

        // Adding all the file sizes of each song into the ArrayList
        for(MusicTrack song: songs){
            fileSize.add(song.size);
        }

        // Sorting the ArrayList in descending order
        fileSize.sort(Comparator.reverseOrder());

        // Prints all the file size of each song
//        for(Float size: fileSize){
//            System.out.println(size);
//        }

        /* Creating a ArrayList to store the remaining spaces that are present in each
            CDs/DVDs(bins)
        */
        float[] remainingSize = new float[fileSize.size()];

        // Implementing the First Fit Decreasing algorithm to solve the bin packing algorithm
        for(int i=0;i<fileSize.size();i++) {
            int j;
            for(j=0;j<bin;j++){
                if(remainingSize[j]>=fileSize.get(i)) {
                    remainingSize[j] = remainingSize[j] - fileSize.get(i);
                    break;
                }
            }
            if(j==bin){
                remainingSize[j] = maximumLimit - fileSize.get(i);
                bin++;
            }
        }
        // Returns the optimal number of CDs/DVDs(bins)
        return bin;
    }
    public static void play(MusicTrack song){
        song.play();
        System.out.println(song.title + " has been played");
        System.out.println("Current play count of the song is: " + song.playCount());
    }
}
