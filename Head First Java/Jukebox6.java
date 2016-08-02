import java.util.*;
import java.io.*;

public class Jukebox6 {
    ArrayList<SongBad> songList = new ArrayList<SongBad>();
    
    public static void main(String[] args) {
        new Jukebox6().go();
    }

    public void go() {
        getSongs();
        System.out.println(songList);
        Collections.sort(songList);
        System.out.println(songList);

        HashSet<SongBad> songSet = new HashSet<SongBad>();
        songSet.addAll(songList);
        System.out.println(songSet);
    }

    void getSongs() {
        try {
            File file = new File("files/SongList.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                addSong(line);
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    void addSong(String lineToParse) {
        String[]tokens = lineToParse.split("/");
        SongBad nextSong = new SongBad(tokens[0], tokens[1], tokens[2], tokens[3]);
        songList.add(nextSong);
    }
}