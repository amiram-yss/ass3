import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;

public class Test7 {
    static Ball tester;
    public static void main(String[] args){
        GUI gui = new GUI("test7",800,600);
        DrawSurface d = gui.getDrawSurface();
        GameEnvironment ge = new GameEnvironment();
        tester = new Ball(new Point(403.80462460332836,82.03320245590632), 5, Color.RED,ge);
        tester.setVelocity(Velocity.fromAngleAndSpeed(1,1));
        tester.drawOn(d);
        //
        Line THATONE = tester.getClosestIntersectionLine();
        System.out.println(THATONE.end());
        //
        ge.drawOn(d);
        gui.show(d);
    }
}
