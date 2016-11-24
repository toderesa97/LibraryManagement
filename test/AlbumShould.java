import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class AlbumShould {

    private Song song1;
    private Song song2;
    private final String DEFAULT_NAME = "A";
    private final Album album = new Album(DEFAULT_NAME);

    @Before
    public void setUp() throws Exception {
        song1 = new Song("a", "i1", 90);
        song2 = new Song("b", "i1", 90);
    }

    @Test
    public void have_a_default_name_when_any_name_is_given() {
        assertEquals(album.getName(), DEFAULT_NAME);
    }

    // TODO: move to SongShould class
    @Test @Ignore
    public void dameNombreMethod() {
        assertEquals(song2.dameInterprete(), "i1");
    }

    @Test
    public void retrieve_null_given_an_invalid_index() {
        assertNull(album.getSongAt(0));
        assertNull(album.getSongAt(-1));
    }

    @Test
    public void retrieve_a_song_given_an_index() {
        album.addSong(song1);

        assertEquals(song1, album.getSongAt(0));
    }

    @Test
    public void add_a_song() {
        album.addSong(song1);

        assertEquals(1, album.getNumberOfSongs());
        assertEquals(song1, album.getSongAt(0));
    }

    @Test
    public void compare_to_another_album_and_should_be_equals() {
        Album album1 = new Album("album1");
        album1.addSong(song1);
        Album album2 = new Album("album2");
        album2.addSong(song1);

        assertEquals(album1, album2);
    }

    @Test
    public void compare_to_another_album_and_should_not_be_equals() {
        Album album1 = new Album("album1");
        album1.addSong(song1);
        Album album2 = new Album("album2");

        assertNotEquals(album1, album2);
    }

    @Test
    public void remove_a_song() {
        album.addSong(song1);

        album.removeSongAt(0);

        assertEquals(0, album.getNumberOfSongs());
    }

    @Test
    public void retrieve_the_number_of_songs() {
        album.addSong(song1);
        album.addSong(song2);

        assertEquals(2, album.getNumberOfSongs());
    }

    @Test
    public void retrieve_the_total_duration() {
        album.addSong(new Song("title1", "author1", 3));
        album.addSong(new Song("title2", "author2", 8));

        assertEquals(11, album.duration());
    }

    @Test
    public void retrieve_a_string_representation_having_at_least_one_song() {
        album.addSong(new Song("title1", "author1", 3));
        album.addSong(new Song("title2", "author2", 3));

        String expected = "Album 1:\n" +
                          "1) [Título:title1 intérprete:author1 duración:3]\n" +
                          "2) [Título:title2 intérprete:author2 duración:3]";

        assertEquals(expected, album.toString());
    }

    @Test
    public void retrieve_a_string_representation_having_zero_songs() {
        String expected = "Album 1:";

        assertEquals(expected, album.toString());
    }
}
