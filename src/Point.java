/**
 * @author Amiram Yassif
 * 314985474
 * ass3
 */
import biuoop.DrawSurface;

import javax.swing.*;
import java.awt.*;

/**
 * @author Amiram Yassif
 * 314985474
 * ass3
 */
public class Point {
    private double x;
    private double y;

    /**
     * creates point based on coords
     * @param x x coord
     * @param y y coord
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point){
        this.x = point.getX();
        this.y = point.getY();
    }

    /**
     * return the distance of this point to the other point
     * @param other other point
     * @return  tistance as double.
     */
    public double distance(Point other)
    {
        return Math.sqrt(Math.pow(this.x-other.x,2)+Math.pow(this.y-other.y,2));
    }

    /**
     * return true is the points are equal, false otherwise
     * @param other other point
     * @return  true if equal, false otherwise.
     */
    public boolean equals(Point other)
    {
        return UTIL.equals(this.x, other.x)
                && UTIL.equals(this.y,other.y);
    }

    /**
     * Return the x and y values of this point
     * @return x value of the point
     */
    public double getX(){
        return this.x;
    }
    /**
     * Return the y and y values of this point
     * @return y value of the point
     */
    public double getY()
    {
        return this.y;
    }

    /**
     * set x value of the point
     * @param x new value to update
     */
    public Point setX(double x)
    {
        this.x=x;
        return this;
    }
    /**
     * set y value of the point
     * @param y new value to update
     */
    public Point setY(double y)
    {
        this.y=y;
        return this;
    }

    /**
     * draws the point as a dot on the screen
     * @param ds the surface
     * @param c the color
     */
    public void drawOn(DrawSurface ds, Color c) {
        ds.setColor(c);
        new Ball(this.x,this.y,3,c, null).drawOn(ds);
    }

    public Point getCopy()
    {
        return new Point(this.x,this.y);
    }


    @Override
    public String toString() {
        return String.format("("+this.x + "," + this.y+")");
    }
}
