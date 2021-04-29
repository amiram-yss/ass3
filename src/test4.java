import biuoop.DrawSurface;
import biuoop.GUI;

import java.util.List;

public class test4 {
    public static void main(String[] args)
    {
        GameEnvironment ge = new GameEnvironment();

        ge.addCollidable(new Block(new Rectangle(new Point(0,0),100,20)));
        ge.addCollidable(new Block(new Rectangle(new Point(100,20),100,20)));

        GUI g = new GUI("test4",400,400);
        DrawSurface d = g.getDrawSurface();

        ge.drawOn(d);
        g.show(d);
    }
}
