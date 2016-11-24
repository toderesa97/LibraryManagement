import java.util.*;
import java.util.stream.Collectors;
public class Album {
    private String albumName;
    private List<Song> canciones;
    private static int numberOfAlbum=0;

    public Album(String albumName){
        this.albumName = albumName;
        this.canciones = new ArrayList<>();
        numberOfAlbum = numberOfAlbum+1;

    }
    public String getName() {
        return albumName;
    }
    public int getNumberOfSongs(){
        return canciones.size();
    }
    public Song getSongAt(int song){
        return canciones.size()>song && song>=0 ? canciones.get(song) : null;
    }
    public void addSong(Song song){
        canciones.add(song);
    }
    public void removeSongAt(int i){
        canciones.remove(i);
    }
    public int duration(){
        return canciones.stream().mapToInt(Song::dameDuracion).sum();
    }
    @Override
    public String toString(){
        String string = "Album "+numberOfAlbum+":\n";

        for(int i=0; i<canciones.size();i++){
            string += (i+1)+") "+canciones.get(i).toString()+"\n";
        }
        return string.substring(0,string.length()-1);
    }
    @Override
    public boolean equals(Object obj){
       return (obj instanceof Album) ? ((Album)obj).canciones.size() == canciones.size() &&
                canciones.stream().map(p->p.equals(((Album)obj).
                canciones.stream().map(u->u))).count() == canciones.size():false;
    }
}
