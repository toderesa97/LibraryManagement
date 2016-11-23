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
    public String dameNombre() {
        return albumName;
    }
    public int numeroDeCanciones(){
        return canciones.size();
    }
    public Song dameCancion(int song){
        return canciones.size()>song && song>=0 ? canciones.get(song) : null;
    }
    public void aniadeCancion(Song song){
        canciones.add(song);
    }
    public void quitaCancion(int i){
        canciones.remove(i);
    }
    public int duracion(){
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
        return (obj instanceof Album) && canciones.stream().map(p->p.equals(((Album)obj).
                canciones.stream().map(u->u))).count() == canciones.size();
    }
}
