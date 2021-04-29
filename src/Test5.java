import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class Test5 {
    public static void main(String[] args){
        Sleeper s = new Sleeper();
        GameEnvironment g = new GameEnvironment();
        GUI gui = new GUI("test5",800,600);
        DrawSurface d = null;
        Ball b = new Ball(400,300,8, Color.RED,g);
        b.setVelocity(Velocity.fromAngleAndSpeed(1,1));
        Line l = b.getIntersectionLineWithBorders();
        while (true)
        {
            if(b.getIntersectionPointWithBorders() == null) {
                b.getIntersectionPointWithBorders();
            }
            System.out.println("BALL CENTER:("+b.center.getX()+","+b.center.getY()+")");
            l = b.getIntersectionLineWithBorders();
            d = gui.getDrawSurface();
            b.getIntersectionPointWithBorders().drawOn(d,Color.RED);
            b.moveOneStep();
            g.drawOn(d);
            b.drawOn(d);
            l.drawOn(d,Color.BLACK);
            gui.show(d);
            s.sleepFor(10);
        }

    }
}
