
public class Song{
    private String title;
    private String author;
    private int duration;

    public Song(String title, String author, int duration){
        this.title = title;
        this.author = author;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Song)
            return ((Song)obj).title.equals(this.title)
                    && ((Song)obj).dameInterprete().equals(this.author);
        return false;
    }
    public String dameTitulo(){
        return this.title;
    }

    public Song dameCancion(){return this;}

    public String dameInterprete() {
        return author;
    }

    public int dameDuracion() {
        return duration;
    }

    @Override
    public String toString(){
        return "[Título:"+title+" intérprete:"+author+" duración:"+duration+"]";
    }

}
