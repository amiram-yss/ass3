import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class Test6 {
    public static void NOP(){}
    public static void main(String[] args){
        int ctr = 0;
        GUI gui = new GUI("test6",800,600);
        DrawSurface d = gui.getDrawSurface();
        Sleeper s = new Sleeper();
        GameEnvironment ge = new GameEnvironment();

        Line l1;
        Ball b = new Ball(new Point(200,200),5, Color.RED,ge);
        Point p;
        b.setVelocity(Velocity.fromAngleAndSpeed(45,5));
        ge.addCollidable
                (new Block
                        (new Rectangle
                                (new Point(300,100), 100,50)
                                ,Color.BLUE));
        ge.addCollidable
                (new Block
                        (new Rectangle
                                (new Point(200,50), 100,50)
                                ,Color.GREEN));

        while (true){
            ctr++;
            System.out.println(b.velocity);
            if(ctr == 110)
                UTIL.DEBUG_MODE = true;
            b.drawOn(d);
            ge.drawOn(d);
            l1 = b.getClosestIntersectionLine();
            p = b.getClosestIntersectionPoint();
            System.out.println(b.center);
            p.drawOn(d,Color.RED);
            l1.drawOn(d,Color.BLACK);
            gui.show(d);
            b.moveOneStep();
            d=gui.getDrawSurface();
            s.sleepFor(10);
        }
    }
}
