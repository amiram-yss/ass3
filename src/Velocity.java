
import java.util.Random;

/**
 * @author Amiram Yassif
 * 314985474
 * ass3
 */
public class Velocity {
    double dx,dy;

    /**
     * Represents movement in one time unit
     * @param dx    change in x axis
     * @param dy    change in y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    public Point applyToPoint(Point p) {
        return new Point(p.getX()+dx,p.getY()+dy);
    }

    /**
     * Copies a velocity object
     * @return copy of this.velocity
     */
    public Velocity createCopy() {
        return new Velocity(this.dx,this.dy);
    }

    /**
     * creates dx, dy based on angel(0 for up) and speed
     * @param angle angel of velocity
     * @param speed size of velocity vector
     * @return  a new velocity based on angel and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(90 - angle)) * speed;
        if(angle == 0 || angle == 180)
            dx = Math.pow(10,-6);
        double dy = -(Math.sin(Math.toRadians(angle + 90)) * speed);
        return new Velocity(dx, dy);
    }

    /**
     * Generate random velocity
     * @param max   max speed
     * @return      new random velocity (not faster than max)
     */
    public static Velocity generateRandomVelocity(int max) {
        int degrees = 360;
        Random r = new Random();
        return fromAngleAndSpeed(r.nextInt(max),degrees);
    }

    public String toString(){
        return String.format("<"+dx+","+dy+">");
    }
}
