import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;

//Tests intersection points with a single rectangle
public class Test3 {
    public static void main(String[] args)
    {
        GUI g = new GUI("test3",400,400);
        DrawSurface d = g.getDrawSurface();
        Rectangle r1 = new Rectangle(new Point(50,50),100,100);
        Rectangle r2 = new Rectangle(new Point(30,80),100,20);

        Line l = new Line(50,200,200,100);
        r1.drawOn(d,Color.green);
        r2.drawOn(d,Color.blue);
        l.drawOn(d,Color.BLACK);

        l.closestIntersectionToStartOfLine(r1).drawOn(d,Color.RED);

        g.show(d);
    }
}
