import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private Set<Album> albumSet;

    public Library(){
        albumSet = new LinkedHashSet();
    }

    public boolean aniadeAlbum(Album album){
        return albumSet.add(album);
    }
    public Album dameAlbum(String albumName){
        List<Album> ls = albumSet.stream().filter(p->p.dameNombre().equals(albumName)).collect(Collectors.toList());
        return ls.size() == 0 ? null : ls.get(0);
    }

    public Set<Album> get(){
        return albumSet;
    }

    public void eliminaAlbum(String albumName){
        Album[] l = albumSet.stream().filter(s->s.dameNombre().equals(albumName)).toArray(Album[]::new);
        albumSet.remove(l[0]);
    }

    @Override
    public String toString(){
        return albumSet.stream().map(s->s.dameNombre()).collect(Collectors.joining("\n"));
    }

    public List<String> removeConjunto(List<String> lista, String conjunto){
        return lista.stream().filter(p->!p.equals(conjunto)).collect(Collectors.toList());
    }
    public List<Integer> apply(List<Integer> ls,final int a){
        return ls.stream().map(p->(p+a)).collect(Collectors.toList());
    }

    public Set<Song> dameCancionesRepetidas(){
        Set<Song> songs = new LinkedHashSet<>();
        Set<Song> todasLasCanciones = new LinkedHashSet<>();

        for(Album album:albumSet){
            for (int i = 0; i < album.numeroDeCanciones(); i++) {
                if(songs.add(album.dameCancion(i))){
                    continue;
                }
                todasLasCanciones.add(album.dameCancion(i));
            }
        }
        return todasLasCanciones;
    }
    private List<Song> dameTodasLasCaciones(){

        List<Song> todasLasCanciones = new ArrayList<>();
        for(Album album:albumSet){
            for (int i = 0; i < album.numeroDeCanciones(); i++) {
                todasLasCanciones.add(album.dameCancion(i));
            }
        }
        return todasLasCanciones;

    }

    public List<String> dameInterpretes() {
        List<Song> listaDeCanciones = dameTodasLasCaciones();
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
        List<String> result = new ArrayList<>();
        for (int i = 0; i < gestionCancionList.size(); i++) {
            result.add(gestionCancionList.get(i).song.dameInterprete());
        }
        return result;
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


