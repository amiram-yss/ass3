import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private Point upperLeft;
    private double width, height;
    // Create a new rectangle with location and width/height.

    private static final int UP = 0;
    private static final int RT = 1;
    private static final int DN = 2;
    private static final int LF = 3;

    public Rectangle(Point upperLeft, double width, double height)
    {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }

    public Rectangle setWidth(double width){
        this.width = width;
        return this;
    }

    public Rectangle setHeight(double height){
        this.height = height;
        return this;
    }

    public Point[] getPoints()
    {
        return new Point[]{
                this.upperLeft.getCopy()
                , this.upperLeft.getCopy()
                        .setX(this.upperLeft.getX() + width)
                , this.upperLeft.getCopy()
                        .setX(this.upperLeft.getX() + width)
                        .setY(this.upperLeft.getY() + height)
                , this.upperLeft.getCopy()
                        .setY(this.upperLeft.getY() + height)
        };
    }

    public Line[] getLines()
    {
        return new Line[]{
                new Line(getPoints()[UP],getPoints()[RT])
                ,new Line(getPoints()[RT],getPoints()[DN])
                ,new Line(getPoints()[DN],getPoints()[LF])
                ,new Line(getPoints()[LF],getPoints()[UP])
        };
    }

    public List<DIRECTION> directionOfEdgeWithThePoint(Point p){
        List<DIRECTION> ltr = new ArrayList<>();
        Line[] edges = this.getLines();
        for(int i = 0 ; i< edges.length; i++){
            if(edges[i].isPointOnLine(p)){
                switch (i) {
                    case UP:
                        ltr.add(DIRECTION.UP);
                        break;
                    case RT:
                        ltr.add(DIRECTION.RIGHT);
                        break;
                    case DN:
                        ltr.add(DIRECTION.DOWN);
                        break;
                    case LF:
                        ltr.add(DIRECTION.LEFT);
                        break;
                }
            }
        }
        return ltr;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> ltr = new ArrayList<>();
        Point intersection = null;
        for(Line l: getLines()){
            intersection = line.intersectionWith(l);
            if(intersection != null)
                ltr.add(intersection);
        }
        return ltr;
    }

    // Return the width and height of the rectangle
    public double getWidth()
    {
        return this.width;
    }
    public double getHeight()
    {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft()
    {
        return this.upperLeft;
    }

    public Rectangle drawOn(DrawSurface d, Color c) {
        d.setColor(c);
        d.fillRectangle((int)this.upperLeft.getX(),(int)this.upperLeft.getY()
                ,(int)width,(int)height);
        return this;
    }
}
