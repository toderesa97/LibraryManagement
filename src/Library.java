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
        return getAllSongs().stream()
                .filter(song -> Collections.frequency(getAllSongs(), song) > 1)
                .collect(Collectors.toSet());
    }

    private List<Song> getAllSongs() {
        return albums.stream()
                .flatMap(album -> album.getAllSongs().stream())
                .collect(Collectors.toList());
    }

    public List<String> getAuthors() {
        List<String> authors = getAllSongs().stream()
                .map(Song::getAuthor)
                .collect(Collectors.toList());

        return authors.stream()
                .sorted(sortedCriteria(authors))
                .distinct()
                .collect(Collectors.toList());
    }

    private Comparator<String> sortedCriteria(final List<String> authors) {
        return (author1, author2) -> {
            int frequency1 = Collections.frequency(authors, author1);
            int frequency2 = Collections.frequency(authors, author2);
            if (frequency1 > frequency2) {
                return -1;
            } else if (frequency1 < frequency2){
                return 1;
            }
            return author1.compareTo(author2);
        };
    }

}


