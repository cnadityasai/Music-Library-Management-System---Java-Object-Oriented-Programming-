import java.io.*;
import java.util.ArrayList;

public class TrackReader {
    /*
        Create the track reader, ready to read tracks from the
        music library
     */
    public TrackReader()
    {
        // Nothing to do here.
    }

    /*
        Read music files from the given library folder
        with the given suffix
        @param folder The folder to look for files.
        @param suffix The suffix of the audio type.
     */
    public ArrayList<MusicTrack> readTracks(String folder, final String suffix)
    {
        File audioFolder = new File(folder);
        ArrayList<MusicTrack> tracks = new ArrayList<MusicTrack>();
        File[] audioFiles = audioFolder.listFiles(new FilenameFilter() {
            /*
                Accept files with matching suffix.
                @param dir The directory containing the file.
                @param name The name of the file.
                @return true if the name ends with the suffix.
             */
            public boolean accept(File dir, String name)
            {
                return name.toLowerCase().endsWith(suffix);
            }
        });

        // Put all the matching files into the organizer.
        for(File file : audioFiles) {
            MusicTrack trackDetails = decodeDetails(file);
            tracks.add(trackDetails);
        }
        return tracks;
    }

    /*
        Try to decode details of the artist and the title
        from the file name.
        It is assumed that the details are in the form:
            artist_title.mp3
        @param file The track file.
        @return A Track containing the details.
     */
    private MusicTrack decodeDetails(File file)
    {
        // The information needed.
        String artist = "unknown";
        String title = "unknown";
        String filename = file.getPath();

        // Look for artist and title in the name of the file.
        String details = file.getName();
        String[] parts = details.split("-");

        if(parts.length == 2) {
            artist = parts[0];
            String titlePart = parts[1];
            // Remove a file-type suffix.
            parts = titlePart.split("\\.");
            if(parts.length >= 1) {
                title = parts[0];
            }
            else {
                title = titlePart;
            }
        }
        return new MusicTrack(artist, title, filename);
    }
}
