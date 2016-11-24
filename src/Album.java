import java.util.ArrayList;
import java.util.List;

public class Album {

    private String name;
    private List<Song> songs;
    private final int albumId;

    public Album(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
        this.albumId = AlbumIdentifier.getNextId();
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSongs() {
        return songs.size();
    }

    public Song getSongAt(int index) {
        return index >= 0 && songs.size() > index ? songs.get(index) : null;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSongAt(int index) {
        songs.remove(index);
    }

    public int duration() {
        return songs.stream().mapToInt(Song::getDuration).sum();
    }

    @Override
    public String toString() {
        String result = "Album " + albumId + ":";
        for (int i = 0; i < songs.size(); i++) {
            result += "\n" + (i + 1) + ") " + songs.get(i).toString();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Album other = (Album) obj;
        if (songs.size() != other.songs.size()) {
            return false;
        }
        for (int i = 0; i < songs.size(); i++) {
            if (!getSongAt(i).equals(other.getSongAt(i))) {
                return false;
            }
        }
        return true;
    }

}
