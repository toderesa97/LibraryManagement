import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SongShould {

    private final String DEFAULT_TITLE = "title";
    private final String DEFAULT_AUTHOR = "author";
    private final int DEFAULT_DURATION = 3;

    @Test
    public void have_a_name_an_author_and_a_duration() {
        Song song = new Song(DEFAULT_TITLE, "author", 3);
        assertEquals(DEFAULT_TITLE, song.getTitle());
        assertEquals(DEFAULT_AUTHOR, song.getAuthor());
        assertEquals(DEFAULT_DURATION, song.getDuration());
    }
}
