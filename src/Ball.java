
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.Random;

/**
 * @author Amiram Yassif
 * 314985474
 * ass2
 */
public class Ball {
    Point center, nextIntersectionPoint;
    int radius;
    Color color;
    Velocity velocity;
    GameEnvironment gameEnvironment;

    private static final int NO_MOVE = 0;
    private static final int DEGREES = 360;
    private static final int MAX_RADIUS_SPEED_REDUCER = 50;
    private static final int MAX_SPEED = 70;
    private static final int MIN_SPEED = 2;
    private static final double SPEED_FACTOR = 0.1;

    /**
     * Builds a ball with Point param, velocity = 0
     * @param center    Point representing the center
     * @param r         int representing radius
     * @param color     the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment ge) {
        this.center=center;
        this.radius=r;
        this.color=color;
        this.velocity = new Velocity(NO_MOVE,NO_MOVE);
        this.gameEnvironment = ge;
        nextIntersectionPoint = null;
    }

    /**
     * Builds a ball using center coordinates, velocity = 0
     * @param x         x of center point
     * @param y         y of center point
     * @param r         int representing radius
     * @param color     color
     */
    public Ball(double x, double y, int r, java.awt.Color color
            , GameEnvironment ge) {
        this.center = new Point(x,y);
        this.radius=r;
        this.color=color;
        this.gameEnvironment = ge;
        nextIntersectionPoint = null;
    }

    // accessors
    public int getX()
    {
        return (int)this.center.getX();
    }
    public int getY()
    {
        return (int) this.center.getY();
    }
    public int getSize()
    {
        return this.radius;
    }
    public java.awt.Color getColor()
    {
        return this.color;
    }

    /**
     * Draws the ball on a given drawSurface
     * @param surface the surface to draw the ball on
     */
    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        //Set the color to the ball's color, and print it.
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(),this.radius);
    }

    /**
     * Get the X value of the right edge point of the ball.
     * @return the X value as a double
     */
    public double getMaxX() {
        return center.getX()+radius;
    }
    /**
     * Get the X value of the left edge point of the ball.
     * @return the X value as a double
     */
    public double getMinX() {
        return center.getX()-radius;
    }
    /**
     * Get the Y value of the upper edge point of the ball.(lower Y->high Y val)
     * @return the Y value as a double
     */
    public double getMaxY() {
        return center.getY()+radius;
    }
    /**
     * Get the Y value of the lower edge point of the ball.(lower Y->high Y val)
     * @return the Y value as a double
     */
    public double getMinY() {
        return center.getY()-radius;
    }

    /**
     * set the right edge of the ball to a certain point given in the params.
     * @param value the point's value
     */
    public void setMaxX(double value) {
        this.center = new Point(value - radius,this.center.getY());
    }

    /**
     * set the right edge of the ball to a certain point given in the params.
     * @param value the point's value
     */
    public void setMinX(double value) {
        this.center = new Point(value + radius,this.center.getY());
    }

    /**
     * set the lower edge of the ball to a certain point given in the params.
     * @param value the point's value
     */
    public void setMaxY(double value) {
        this.center = new Point(this.center.getX(), value-radius);
    }

    /**
     * set the top edge of the ball to a certain point given in the params.
     * @param value the point's value
     */
    public void setMinY(double value) {
        this.center = new Point(this.center.getX(), value+radius);
    }

    /**
     * sets the velocity of the ball, directly from the params.
     * @param v the velocity to set as the property
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * sets the velocity of the ball, from dx dy vals.
     * @param dx    dx for the velocity params
     * @param dy    dy for the velocity params
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx,dy);
    }

    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Create a new ball on a random point in given range.
     * Radius param is given as well
     * @param r         int representing a radius.
     * @param boardMinX int representing the minimum X value of the board.
     * @param boardMinY int representing the minimum Y value of the board.
     * @param boardMaxX int representing the maximum X value of the board.
     * @param boardMaxY int representing the maximum Y value of the board.
     * @return A new ball with the wanted parameters.
     */
    public static Ball generateBallInRandomLocationAndColor
    (int r, int boardMinX, int boardMinY, int boardMaxX, int boardMaxY
            , GameEnvironment ge){
        Random rand =new Random();
        //Will create location of center point randomly.must be: minXY+r<loc<max
        int x = rand.nextInt
                ((int)(boardMaxX - boardMinX - 2*r)) + (int)(boardMinX + r);
        int y = rand.nextInt
                ((int)(boardMaxY - boardMinY - 2*r)) + (int)(boardMinY + r);
        //Create the ball based on the random XY given above.
        Ball btr = new Ball(x,y,r,new Color(rand.nextFloat()
                ,rand.nextFloat(),rand.nextFloat()),ge);
        //Create random velocity, based on random angle and speed.
        btr.velocity = Velocity.fromAngleAndSpeed
                (rand.nextInt(DEGREES),
                        r>=MAX_RADIUS_SPEED_REDUCER ?
                                MIN_SPEED : (MAX_SPEED-r)*SPEED_FACTOR);
        return btr;
    }
    /**
     * Move one step based on the velocity prop.
     * Add dx,dy from velocity to the ball's current position.
     * If not possible (moves out of border), bounce it back to the frame.
     * @param boardMinX frame min x.
     * @param boardMinY frame min y.
     * @param boardMaxX frame max x.
     * @param boardMaxY frame max y.
     */
    @Deprecated
    public void moveOneStep
    (int boardMinX, int boardMinY, int boardMaxX, int boardMaxY) {
        //Add dx, dy to the current center point.
        this.center = this.getVelocity().applyToPoint(this.center);
        this.velocity = this.velocity.createCopy();
        //If the new X val is too low:
        if(this.getMinX() <= boardMinX)
        {
            //set the left side of the ball in the minX val of frame + the delta
            this.setMinX(boardMinX - this.getMinX() + boardMinX);
            //reflect X component to the opposite side.
            this.velocity.dx *= -1;
        }
        //If the new X value is too high:
        if(boardMaxX <= this.getMaxX())
        {
            //set the right side of the ball in the maxX val of frame - the delta
            this.setMaxX(boardMaxX - (this.getMaxX() - boardMaxX));
            //reflect X component to the opposite side.
            this.velocity.dx *= -1;
        }
        //If the new Y val is too low:
        if(this.getMinY() <= boardMinY)
        {
            //set the upper side of the ball in the minY val of frame-the delta
            this.setMinY(boardMinY - this.getMinY() + boardMinY);
            //reflect Y component to the opposite side.
            this.velocity.dy *= -1;
        }
        //If the new Y val is too high:
        if(boardMaxY <= this.getMaxY())
        {
            //set the lower side of the ball in maxY of the border + delta.
            this.setMaxY(boardMaxY - (this.getMaxY() - boardMaxY));
            //reflect velocity's Y component.
            this.velocity.dy *= -1;
        }


    }

    public void moveOneStep(){
        Point newLocation = this.velocity.applyToPoint(this.center);
        Line trace = new Line(this.center,newLocation);
        Point intersection =


        this.center = this.velocity.applyToPoint(this.center);
    }

    private Line createTrajectoryFromBallToEndOfTheBoard() {
        double[] mn = Line.toLinearEquation
                (center, velocity.dx, velocity.dy);
        Point p2 = Line.solveYforX(mn,GameEnvironment.SCREEN_WIDTH);
        Point p1 = Line.solveYforX(mn,0);
        if((this.velocity.dx > 0) && (p2.getX() > p1.getX()))
            return new Line(this.center, p2);
        return new Line(this.center, p1);
    }

    public Point getIntersectionPointWithBorders() {
        Point ptr = null, holder = null;

        Line fullLengthLine = createTrajectoryFromBallToEndOfTheBoard();//new Line(p1.getCopy(),p2.getCopy());
        for(Collidable c: gameEnvironment.getBorders()){
            ptr = fullLengthLine.closestIntersectionToStartOfLine
                    (c.getCollisionRectangle());
            if(ptr == null)
                continue;
            if((velocity.dx > 0) && (ptr.getX()>this.center.getX()))
                return ptr;
            if((velocity.dx < 0) && (ptr.getX()<this.center.getX()))
                return ptr;
        }
        return ptr;
    }

    public Line getIntersectionLineWithBorders(){
        return new Line(center,getIntersectionPointWithBorders());
    }

    public Point getClosestIntersectionPoint(){
        Point pHolder = null, ptr = null;
        Line lHolder = null, shortest = null;
        Line route = getIntersectionLineWithBorders();
        for(Collidable c: gameEnvironment.collidables) {
            pHolder = route.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if(ptr == null)
                ptr = pHolder;
            if(pHolder == null)
                continue;
            if((velocity.dx > 0) && (ptr.getX() > pHolder.getX())) {
                ptr = pHolder;
            }
            if ((velocity.dx < 0) && (ptr.getX() < pHolder.getX())){
                ptr = pHolder;
            }
        }
        return ptr;
    }

    public Line getClosestIntersectionLine(){
        Line l = new Line(this.center, getClosestIntersectionPoint());
        return l;
    }

}
