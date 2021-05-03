import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amiram Yassif
 * 314985474
 * ass3
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    public SpriteCollection(){
        sprites = new ArrayList<>();
    }

    public void addSprite(Sprite s){
        sprites.add(s);
    }

    public void drawAllOn(DrawSurface d) {
        for(Sprite s: sprites)
            s.drawOn(d);
    }

    public void notifyAllTimePassed() {
        for(Sprite s: sprites)
            s.timePassed();
    }
}
