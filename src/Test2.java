import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;

public class Test2 {
    public static void main(String[] args)
    {
        Rectangle r = new Rectangle(new Point(150,0),150,10);
        GUI g = new GUI("test2", 300, 300);
        DrawSurface d = g.getDrawSurface();
        r.drawOn(d,Color.PINK);
        for(Point p: r.getPoints()) {
            p.drawOn(d, Color.RED);
        }
        for(Line l: r.getLines()) {
            l.drawOn(d, Color.BLUE);
        }
        g.show(d);
    }
}
