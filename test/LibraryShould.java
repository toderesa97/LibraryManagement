import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;

public class LibraryShould {

    private final int DEFAULT_DURATION = 3;
    private final String DEFAULT_TITLE = "title";
    private final String DEFAULT_ALBUM_NAME = "Album";

    private Song song1 = new Song("c1", "i1", 90);
    private Song song2 = new Song("c2", "i1", 90);
    private Song song3 = new Song("c3", "i2", 120);
    private Song song4 = new Song("c4", "i2", 120);

    private Album album1 = new Album(DEFAULT_ALBUM_NAME);
    private Album album2 = new Album("B");

    private Library library;

    @Before
    public void setUp() throws Exception {
        library = new Library();
    }

    @Test
    public void retrieve_an_album_by_name() {
        library.addAlbum(album1);

        assertEquals(album1, library.getAlbumByName(DEFAULT_ALBUM_NAME));
    }

    @Test
    public void add_an_album() {
        library.addAlbum(album1);

        assertEquals(album1, library.getAlbumByName(DEFAULT_ALBUM_NAME));
    }

    @Test
    public void remove_an_album() {
        library.addAlbum(album1);

        library.removeAlbumByName(DEFAULT_ALBUM_NAME);

        assertNull(library.getAlbumByName(DEFAULT_ALBUM_NAME));
    }

    @Test
    public void retrieve_repeated_songs() {
        album1.addSong(song1);
        album1.addSong(song2);
        album1.addSong(song3);

        album2.addSong(song1);
        album2.addSong(song2);
        album2.addSong(song4);

        library.addAlbum(album1);
        library.addAlbum(album2);

        Set<Song> expectedSongs = new HashSet<>();
        expectedSongs.add(song1);
        expectedSongs.add(song2);

        assertEquals(expectedSongs, library.getRepeatedSongs());
    }

    @Test
    public void retrieve_all_authors_sorted_by_number_of_songs() {
        String author1 = "author1";
        String author2 = "author2";
        String author3 = "author3";

        Song song1 = getSongFor(author1);
        Song song2 = getSongFor(author2);
        Song song3 = getSongFor(author2);
        Song song4 = getSongFor(author3);
        Song song5 = getSongFor(author3);
        Song song6 = getSongFor(author3);

        Album album1 = new Album("Album1");
        album1.addSong(song1);
        album1.addSong(song2);
        album1.addSong(song3);
        Album album2 = new Album("Album2");
        album2.addSong(song4);
        album2.addSong(song5);
        album2.addSong(song6);

        library.addAlbum(album1);
        library.addAlbum(album2);

        List<String> expected = Arrays.asList(author3, author2, author1);
        assertEquals(expected, library.getAuthors());
    }

    private Song getSongFor(String author1) {
        return new Song(DEFAULT_TITLE, author1, DEFAULT_DURATION);
    }

    @Test
    public void retrieve_a_string_representation_given_an_empty_library() {
        assertEquals(library.toString(), "");
    }

    @Test
    public void retrieve_a_string_representation_given_a_library_with_one_album_and_some_songs() {
        Album album = new Album("album");
        album.addSong(new Song("title1", "author1", DEFAULT_DURATION));
        album.addSong(new Song("title2", "author2", DEFAULT_DURATION));
        library.addAlbum(album);

        String expected = "Album " + album.getId() +":\n" +
                            "1) [Título:title1 intérprete:author1 duración:3]\n" +
                            "2) [Título:title2 intérprete:author2 duración:3]";
        assertEquals(expected, library.toString());
    }


}
