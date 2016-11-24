public class AlbumIdentifier {

    private static int albumId = 0;

    public static int getNextId() {
        return ++albumId;
    }

}
