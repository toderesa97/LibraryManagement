import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tdrs on 17/11/16.
 */
public class AlbumClassShould {
    private Song song1 = new Song("a","i1",90);
    private Song song2 = new Song("b","i1",90);
    private Song song3 = new Song("c","i2",120);
    private Song song4 = new Song("d","i2",120);
    private Song song5 = new Song("e","i2",120);
    private Song song6 = new Song("f","i3",150);
    private Song song7 = new Song("g","i3",150);
    private List<Song> list;
    private Album album = new Album("A");



    @Test
    public void constructorWorkingProperly (){

        assertEquals(album.dameNombre(),"A");

    }

    @Test
    public void dameNombreMethod (){
        assertEquals(song2.dameInterprete(),"i1" );
    }

    @Test
    public void dameCancionMethod (){

        album.aniadeCancion(song3);
        album.aniadeCancion(song3);
        album.aniadeCancion(song7);
        assertTrue(album.dameCancion(0).equals(song3));
        assertTrue(album.dameCancion(2).equals(song7));
        assertTrue(album.dameCancion(1).equals(song3));
    }

    @Test
    public void addMethodIsWorkingProperly (){

        assertEquals(album.dameNombre(),"A");
        album.aniadeCancion(song1);
        album.aniadeCancion(song4);
        album.aniadeCancion(song7);
        list = new ArrayList<>();
        list.add(song1);list.add(song4);list.add(song7);
        for (int i = 0; i < list.size(); i++) {
            assertTrue(list.get(i).equals(album.dameCancion(i)));
        }
    }

    @Test
    public void testingIfEqualsMethodFromAlbumIsWorking (){
        album.aniadeCancion(song5);album.aniadeCancion(song7);
        Album a = new Album("album2");
        a.aniadeCancion(song5);a.aniadeCancion(song2);
        assertTrue("1",!album.equals(a));
        assertTrue("2",!song6.equals(song7));
    }

    @Test
    public void removeMethodIsWorkingProperly (){

        assertEquals(album.dameNombre(),"A");
        album.aniadeCancion(song1);
        album.aniadeCancion(song4);
        album.aniadeCancion(song7);
        album.aniadeCancion(song6);

        album.quitaCancion(1);
        list = new ArrayList<>();
        list.add(song1);list.add(song7);list.add(song6);
        for (int i = 0; i < list.size(); i++) {
            assertTrue(list.get(i).equals(album.dameCancion(i)));
        }
    }

    @Test
    public void numberOfSongsMethod (){


        album.aniadeCancion(song1);
        album.aniadeCancion(song4);
        album.aniadeCancion(song7);
        album.aniadeCancion(song6);
        album.aniadeCancion(song5);
        assertTrue(5==album.numeroDeCanciones());
    }

    @Test
    public void durationMethod (){


        album.aniadeCancion(song1);
        album.aniadeCancion(song4);
        album.aniadeCancion(song7);
        album.aniadeCancion(song6);
        album.aniadeCancion(song5);

        assertTrue(630==album.duracion());
    }

    @Test
    public void toStringMethod (){

        Album al = new Album("B");
        al.aniadeCancion(song1);
        al.aniadeCancion(song4);
        String expected = "Album "+al.getAlbumId()+":\n1) [Título:a intérprete:i1 duración:90]\n" +
                "2) [Título:d intérprete:i2 duración:120]";
        assertEquals(expected,al.toString());
    }
}
