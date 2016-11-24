import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private Set<Album> albums;

    public Library() {
        albums = new LinkedHashSet<>();
    }

    public boolean addAlbum(Album album) {
        return albums.add(album);
    }

    public Album getAlbumByName(String albumName) {
        return findByName(albumName).orElse(null);
    }

    public void removeAlbumByName(String albumName) {
        findByName(albumName).ifPresent(album -> albums.remove(album));
    }

    private Optional<Album> findByName(String albumName) {
        return albums.stream()
                .filter(album -> album.getName().equals(albumName))
                .findFirst();
    }

    @Override
    public String toString() {
        return albums.stream().map(Album::toString).collect(Collectors.joining("\n"));
    }

    public Set<Song> getRepeatedSongs() {
        List<Song> allSongs = getAllSongs();
        return allSongs.stream().filter(p -> {
            int counter = 0;
            for (Song i : allSongs) {
                if (p.equals(i)) counter++;
            }
            return counter > 1;
        }).collect(Collectors.toSet());
    }

    private List<Song> getAllSongs() {
        return albums.stream()
                .flatMap(album -> album.getAllSongs().stream())
                .collect(Collectors.toList());
    }

    public List<String> getAuthors() {
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
                -> t1.vecesQueSeRepite == t2.vecesQueSeRepite ?
                t1.song.getAuthor().compareTo(t2.song.getAuthor())
                : (int) (t2.vecesQueSeRepite - t1.vecesQueSeRepite));
        return gestionCancionList.stream().map(p -> p.song.getAuthor()).collect(Collectors.toList());
    }

    private class GestionCancion {
        long vecesQueSeRepite;
        Song song;

        public GestionCancion(long vecesQueSeRepite, Song song) {
            this.vecesQueSeRepite = vecesQueSeRepite;
            this.song = song;
        }
    }
}


