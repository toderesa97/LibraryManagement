import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
        album1.aniadeCancion(song1);
        album1.aniadeCancion(song4);

        album2.aniadeCancion(song2);

        album3.aniadeCancion(song2);
        album3.aniadeCancion(song5);

        library.aniadeAlbum(album1);
        library.aniadeAlbum(album3);
        library.aniadeAlbum(album2);
        Album obtained = library.dameAlbum("B");
        assertTrue(album2.equals(obtained));
    }

    @Test
    public void testingIfAddMethodIsWorking (){
        boolean a = library.aniadeAlbum(album1);
        assertTrue(a);
        a= library.aniadeAlbum(album3);
        assertTrue(a);
        a= library.aniadeAlbum(album2);
        assertTrue(a);

        Album[] ls = library.get().stream().toArray(Album[]::new);
        Album[] expected = {album1,album3,album2};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i],ls[i]);
        }
        assertNotEquals(song5, album1.dameCancion(0));
        album1.aniadeCancion(song5);
        library.aniadeAlbum(album1);
        //ls = library.get().stream().toArray(Album[]::new);
        Album[] expected2 = {album1,album3,album2};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected2[i],ls[i]);
        }
        assertEquals(song5, album1.dameCancion(0));

    }

    @Test
    public void testingIfRemoveMethodIsWorking (){
        album1.aniadeCancion(song1);
        album1.aniadeCancion(song4);

        album2.aniadeCancion(song2);

        album3.aniadeCancion(song2);
        album3.aniadeCancion(song5);

        library.aniadeAlbum(album1);
        library.aniadeAlbum(album3);
        library.aniadeAlbum(album2);

        library.eliminaAlbum("C");
        System.out.println(album1);

        System.out.println(library.dameAlbum("A"));

        assertTrue("1",album1.equals(library.dameAlbum("A")));
        assertTrue("2",album2.equals(library.dameAlbum("B")));

        assertTrue("Album erased (C) must be null ",null== library.dameAlbum("C"));



    }

    @Test
    public void testingIfDameCancionesRepetidasIsWorkingProperly (){
        album1.aniadeCancion(song5);
        album1.aniadeCancion(song4);
        album1.aniadeCancion(song3);

        album2.aniadeCancion(song5);
        album2.aniadeCancion(song7);
        album2.aniadeCancion(song4);

        album3.aniadeCancion(song1);
        album3.aniadeCancion(song2);
        album3.aniadeCancion(song6);
        Library b = new Library();
        b.aniadeAlbum(album1);b.aniadeAlbum(album2);b.aniadeAlbum(album3);


        Set<Song> obtained = b.dameCancionesRepetidas();
        Song[] ob = obtained.stream().toArray(Song[]::new);
        Song[] expected = {song5, song4};
        assertTrue("expected "+expected.length+" but was "+ob.length,expected.length==obtained.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i],ob[i]);
        }
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
    public void testingIfEliminaConjuntoIsworking (){
        List<String> obtained = library.removeConjunto(Arrays.asList("ab","bcf","defljn","ab","pepe21"),"ab");
        List<String> expected = Arrays.asList("bcf","defljn","pepe21");
        assertTrue(obtained.size()==expected.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i),obtained.get(i));
        }

        List<Integer> exp = Arrays.asList(4,6,3,9,0,5);
        List<Integer> btained = library.apply(Arrays.asList(2,4,1,7,-2,3),2);
        assertTrue(exp.size() == btained.size());

        for (int i = 0; i < exp.size(); i++) {
            assertTrue(exp.get(i) == btained.get(i));
        }

    }

    @Test
    public void testingIfToStringMethodIsWorking (){
        album1.aniadeCancion(song5);
        album1.aniadeCancion(song4);
        album1.aniadeCancion(song3);

        album2.aniadeCancion(song5);
        album2.aniadeCancion(song7);
        album2.aniadeCancion(song4);

        album3.aniadeCancion(song4);
        album3.aniadeCancion(song5);
        album3.aniadeCancion(song5);
        library.aniadeAlbum(album3);
        library.aniadeAlbum(album2);
        library.aniadeAlbum(album1);

        assertEquals(library.toString(), "C\nB\nA");
    }




}
