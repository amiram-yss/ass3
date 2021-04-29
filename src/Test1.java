import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;

public class Test1 {
    public static void main(String[] args)
    {
        GUI gui = new GUI("test",200,200);
        DrawSurface ds = gui.getDrawSurface();
        Line l1 = new Line(10,100, 90,100);
        Line l2 = new Line(80,30, 80,120);
        l1.drawOn(ds, Color.GREEN);
        l2.drawOn(ds, Color.BLUE);
        Point intersection = l2.intersectionWith(l1);

        if(intersection!=null) {
            new Ball(intersection.getX(), intersection.getY(), 3, Color.RED, null).drawOn(ds);
            System.out.println(intersection.getX() + ", " + intersection.getY());
        }

        gui.show(ds);
    }
}
