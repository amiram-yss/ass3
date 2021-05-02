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
        Ball b = new Ball(new Point(400,300),5, Color.RED,ge);
        Point p;
        b.setVelocity(Velocity.fromAngleAndSpeed(180,1));
        ge.addCollidable
                (new Block
                        (new Rectangle
                                (new Point(300,100), 200,100)
                                ,Color.BLUE));

        while (true){
            ctr++;
//            System.out.println("Loop: "+ctr);
            if(ctr == 110)
                UTIL.DEBUG_MODE = true;
            b.drawOn(d);
            ge.drawOn(d);
            l1 = b.getClosestIntersectionLine();
            p = b.getClosestIntersectionPoint();
            System.out.println(p);
            p.drawOn(d,Color.RED);
            l1.drawOn(d,Color.BLACK);
            gui.show(d);
            b.moveOneStep();
            d=gui.getDrawSurface();
            s.sleepFor(10);
            if(UTIL.DEBUG_MODE)
                UTIL.DEBUG_MODE = false;
        }
    }
}
