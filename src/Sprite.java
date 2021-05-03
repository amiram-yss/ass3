import biuoop.DrawSurface;

/**
 * @author Amiram Yassif
 * 314985474
 * ass3
 */
public interface Sprite {
    // draw the sprite to the screen
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed
    void timePassed();
}
