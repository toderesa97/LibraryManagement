import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNull;

public class LibraryShould {
    private Song song1 = new Song("c1", "i1", 90);
    private Song song2 = new Song("c2", "i1", 90);
    private Song song3 = new Song("c3", "i2", 120);
    private Song song4 = new Song("c4", "i2", 120);
    private Song song5 = new Song("c5", "i2", 120);
    private Song song7 = new Song("c7", "i3", 150);

    private Album album1 = new Album(DEFAULT_NAME);
    private Album album2 = new Album("B");
    private Album album3 = new Album("C");
    public static final String DEFAULT_NAME = "A";

    private Library library;

    @Before
    public void setUp() throws Exception {
        library = new Library();
    }

    @Test
    public void retrieve_an_album_by_name() {
        library.addAlbum(album1);

        assertEquals(album1, library.getAlbumByName(DEFAULT_NAME));
    }

    @Test
    public void add_an_album() {
        library.addAlbum(album1);

        assertEquals(album1, library.getAlbumByName(DEFAULT_NAME));
    }

    @Test
    public void remove_an_album() {
        library.addAlbum(album1);

        library.removeAlbumByName(DEFAULT_NAME);

        assertNull(library.getAlbumByName(DEFAULT_NAME));
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
    public void retrieve_all_authors() {
        album1.addSong(song5);
        album1.addSong(song4);
        album1.addSong(song3);

        album2.addSong(song5);
        album2.addSong(song7);
        album2.addSong(song4);

        album3.addSong(song4);
        album3.addSong(song5);
        album3.addSong(song5);
        album3.addSong(song5);
        album3.addSong(song4);
        album3.addSong(song4);
        album3.addSong(song3);
        album3.addSong(song3);
        album3.addSong(song4);
        album3.addSong(song3);
        album3.addSong(song7);
        album3.addSong(song3);
        album3.addSong(song2);
        album3.addSong(song1);

        library.addAlbum(album1);
        library.addAlbum(album2);
        library.addAlbum(album3);
        List<String> expected = Arrays.asList("i2", "i2", "i2", "i3", "i1", "i1");
        List<String> obtained = library.getAuthors();
        assertTrue(!(null == obtained));
        assertTrue(expected.size() == obtained.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), obtained.get(i));
        }
    }

    @Test
    public void retrieve_a_string_representation_given_an_empty_library() {
        assertEquals(library.toString(), "");
    }


}
