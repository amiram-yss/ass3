import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private Point upperLeft;
    private double width, height;
    // Create a new rectangle with location and width/height.
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
                new Line(getPoints()[0],getPoints()[1])
                ,new Line(getPoints()[1],getPoints()[2])
                ,new Line(getPoints()[2],getPoints()[3])
                ,new Line(getPoints()[3],getPoints()[0])
        };
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
