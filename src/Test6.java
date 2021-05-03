import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class Test6 {
    public static void main(String[] args){
        int ctr = 0;
        GUI gui = new GUI("test6",800,600);
        DrawSurface d = gui.getDrawSurface();
        Sleeper s = new Sleeper();
        GameEnvironment ge = new GameEnvironment();

        Line l1;
        //Ball b = new Ball(new Point(700,100),5, Color.RED,ge);
        //Ball b = new Ball(new Point(700,110),5, Color.RED,ge);
        Ball b = new Ball(new Point(700,320),5, Color.RED,ge);
        Point p;
        b.setVelocity(Velocity.fromAngleAndSpeed(45,1));


        while (true){
            ctr++;
            //System.out.println(b.velocity);
           /* if(b.center.getX() > 200)
                UTIL.DEBUG_MODE = true;*/
            ge.drawOn(d);
            b.drawOn(d);
            l1 = b.getClosestIntersectionLine();
            p = b.getClosestIntersectionPoint();
            //System.out.println(b.center);
            p.drawOn(d,Color.RED);
            l1.drawOn(d,Color.BLACK);
            gui.show(d);
            if(UTIL.DEBUG_MODE)
                UTIL.NOP();
            b.moveOneStep();
            d=gui.getDrawSurface();
            s.sleepFor(2);
        }
    }
}
