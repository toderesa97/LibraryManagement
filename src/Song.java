public class Song {

    private final String title;
    private final String author;
    private final int duration;

    public Song(String title, String author, int duration) {
        this.title = title;
        this.author = author;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object obj) {
        Song other = (Song) obj;
        return this.title.equals(other.getTitle()) &&
               this.author.equals(other.getAuthor()) &&
               this.duration == other.getDuration();
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return author;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return String.format("[Título:%s intérprete:%s duración:%d]", title, author, duration);
    }

}
