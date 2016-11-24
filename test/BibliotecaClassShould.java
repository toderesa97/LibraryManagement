import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by tdrs on 18/11/16.
 */
public class BibliotecaClassShould {
    private Song song1 = new Song("c1","i1",90);
    private Song song2 = new Song("c2","i1",90);
    private Song song3 = new Song("c3","i2",120);
    private Song song4 = new Song("c4","i2",120);
    private Song song5 = new Song("c5","i2",120);
    private Song song6 = new Song("c6","i3",150);
    private Song song7 = new Song("c7","i3",150);

    private Album album1 = new Album("A");
    private Album album2 = new Album("B");
    private Album album3 = new Album("C");

    private Library library = new Library();

    @Test
    public void testingIfDameAlbumMethodIsWorking (){
        library.aniadeAlbum(album1);
        assertEquals(album1, library.dameAlbum("A"));
    }

    @Test
    public void testingIfAddMethodIsWorking (){
        library.aniadeAlbum(album1);
        assertEquals("Album "+album1.getAlbumId()+":", library.toString());
        System.out.println(library);

        album1.aniadeCancion(song1);
        library.aniadeAlbum(album1); /** sin esta linea el assert pasa */
        assertEquals("Album "+album1.getAlbumId()+":\n1) [Título:c1 intérprete:i1 duración:90]",library.toString());

    }

    @Test
    public void testingIfRemoveMethodIsWorking (){
        album1.aniadeCancion(song1);
        album2.aniadeCancion(song2);
        library.aniadeAlbum(album1);
        library.aniadeAlbum(album2);

        assertEquals("Album "+album1.getAlbumId()+":\n1) [Título:c1 intérprete:i1 duración:90]\n" +
                "Album "+album2.getAlbumId()+":\n1) [Título:c2 intérprete:i1 duración:90]",library.toString());

        library.eliminaAlbum(album1.dameNombre());

        assertEquals("Album "+album2.getAlbumId()+":\n1) [Título:c2 intérprete:i1 duración:90]",library.toString());

    }

    @Test
    public void testingIfDameCancionesRepetidasIsWorkingProperly (){
        album1.aniadeCancion(song5);
        album1.aniadeCancion(song4);
        album1.aniadeCancion(song3);
        album1.aniadeCancion(song3);

        album2.aniadeCancion(song5);
        library.aniadeAlbum(album1);
        library.aniadeAlbum(album2);

        Set<Song> songSet = library.dameCancionesRepetidas();
        Set<Song> songSet1 = new HashSet<>();
        songSet1.add(song3);
        songSet1.add(song5);

        assertEquals(songSet, songSet1);
    }

    @Test
    public void testingIfdameIntérpretesIsWorkingProperly (){
        album1.aniadeCancion(song5);
        album1.aniadeCancion(song4);
        album1.aniadeCancion(song3);

        album2.aniadeCancion(song5);
        album2.aniadeCancion(song7);
        album2.aniadeCancion(song4);

        album3.aniadeCancion(song4);
        album3.aniadeCancion(song5);
        album3.aniadeCancion(song5);
        album3.aniadeCancion(song5);
        album3.aniadeCancion(song4);
        album3.aniadeCancion(song4);
        album3.aniadeCancion(song3);
        album3.aniadeCancion(song3);
        album3.aniadeCancion(song4);
        album3.aniadeCancion(song3);
        album3.aniadeCancion(song7);
        album3.aniadeCancion(song3);
        album3.aniadeCancion(song2);
        album3.aniadeCancion(song1);

        library.aniadeAlbum(album1);
        library.aniadeAlbum(album2);
        library.aniadeAlbum(album3);
        List<String> expected = Arrays.asList("i2","i2","i2","i3","i1","i1");
        List<String> obtained = library.dameInterpretes();
        assertTrue(!(null==obtained));
        assertTrue(expected.size()==obtained.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i),obtained.get(i));
        }

    }

    @Test
    public void testingIfdameIntérpretesIsWorkingProperly2 (){
        Song song1 = new Song("c1","malu",90);
        Song song2 = new Song("c2","pepe",90);
        Song song3 = new Song("c3","jesus",120);
        Song song4 = new Song("c4","santana",120);
        Song song5 = new Song("c5","maroke",120);
        album1.aniadeCancion(song1);album2.aniadeCancion(song1);
        album1.aniadeCancion(song5);album2.aniadeCancion(song5);
        album2.aniadeCancion(song2);album1.aniadeCancion(song3);
        album1.aniadeCancion(song4);album1.aniadeCancion(song5);
        album1.aniadeCancion(song1);album2.aniadeCancion(song2);
        library.aniadeAlbum(album1);
        library.aniadeAlbum(album2);

        List<String> expected = Arrays.asList("malu","maroke","pepe","jesus","santana");
        List<String> obtained = library.dameInterpretes();

        assertTrue(expected.size() == obtained.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i),obtained.get(i));
        }
    }



    @Test
    public void testingIfToStringMethodIsWorking (){
        album1.aniadeCancion(song1);

        album2.aniadeCancion(song1);

        library.aniadeAlbum(album2);
        library.aniadeAlbum(album1);

        String expected = "Album "+album2.getAlbumId()+":\n1) [Título:c1 intérprete:i1 duración:90]\n"+
                "Album "+album1.getAlbumId()+":\n1) [Título:c1 intérprete:i1 duración:90]";
        assertEquals(expected,library.toString());
    }
}
