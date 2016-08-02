class SongBad implements Comparable <SongBad> {
    String title;
    String artist;
    String rating;
    String bpm;

    public SongBad(String t, String a, String r, String b) {
        title = t;
        artist = a;
        rating = r;
        bpm = b;
    }

    public boolean equals(Object aSong) {
        SongBad s = (SongBad) aSong;
        return getTitle().equals(s.getTitle());
    }

    // get rid of the duplicates
    public int hashCode() {
        return title.hashCode();
    }

    public int compareTo(SongBad s) {
        return title.compareTo(s.getTitle());
    }

    public String getArtist() {
        return artist;
    }

    public String getBpm() {
        return bpm;
    }

    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return title;
    }
}