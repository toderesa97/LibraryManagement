import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private Set<Album> albumSet;

    public Library(){
        albumSet = new LinkedHashSet();
    }

    public void aniadeAlbum(Album album){
        albumSet.add(album);
    }
    public Album dameAlbum(String albumName){
        return albumSet.stream().filter(p -> p.dameNombre().equals(albumName)).findFirst().orElse(null);
    }

    public void eliminaAlbum(String albumName){
        albumSet = albumSet.stream().filter(album -> !album.dameNombre().equals(albumName)).collect(Collectors.toSet());
    }

    @Override
    public String toString(){
        return albumSet.stream().map(s->s.toString()).collect(Collectors.joining("\n"));
    }

    public Set<Song> dameCancionesRepetidas(){
        return getAllSongs().stream()
                .filter(song -> Collections.frequency(getAllSongs(), song) > 1)
                .collect(Collectors.toSet());
    }
    private List<Song> getAllSongs(){
        return albumSet.stream().flatMap(songs -> songs.dameCanciones()).collect(Collectors.toList());
    }

    public List<String> dameInterpretes() {
        List<Song> listaDeCanciones = getAllSongs();
        List<GestionCancion> gestionCancionList = new ArrayList<>();
        List<Song> auxiliarList = new ArrayList<>();

        for (Song song : listaDeCanciones) {
            long vecesQueEstaRepetida = listaDeCanciones.stream().filter(p -> p.equals(song)).count();
            if (!auxiliarList.contains(song)) {
                gestionCancionList.add(new GestionCancion(vecesQueEstaRepetida, song));
                auxiliarList.add(song);
            }
        }

        gestionCancionList.sort((GestionCancion t1, GestionCancion t2)
                ->t1.vecesQueSeRepite == t2.vecesQueSeRepite ?
                t1.song.dameInterprete().compareTo(t2.song.dameInterprete())
                : (int)(t2.vecesQueSeRepite - t1.vecesQueSeRepite));
        return gestionCancionList.stream().map(p->p.song.dameInterprete()).collect(Collectors.toList());
    }
    private class GestionCancion {
        long vecesQueSeRepite;
        Song song;
        public GestionCancion(long vecesQueSeRepite, Song song){
            this.vecesQueSeRepite = vecesQueSeRepite;
            this.song = song;
        }
    }
}


