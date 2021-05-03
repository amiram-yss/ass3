import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * @author Amiram Yassif
 * 314985474
 * ass3
 */
public class Paddle implements Sprite, Collidable {
    private static final double SCREEN_WIDTH = 800;
    private static final double UPPER_LEFT_Y_VALUE = 560;
    private static final double PADDLE_WIDTH = 100;
    private static final int PADDLE_PAINTED_HEIGHT = 20;
    private static final double PADDLE_ACTUAL_HEIGHT = 0.01;
    private static final double STARTING_X_VALUE = 500;
    private static final double PADDLE_SPEED = 5;
    private static final double PADDLE_PARTS = 5;
    private static final double ANGEL = 30;

    KeyboardSensor keyboardSensor;
    Rectangle rectangle;

    public Paddle(){
        rectangle = new Rectangle(
                new Point(STARTING_X_VALUE,UPPER_LEFT_Y_VALUE)
        ,PADDLE_WIDTH
        , PADDLE_ACTUAL_HEIGHT);
    }

    public void moveLeft(){
        if(this.rectangle.getUpperLeft().getX()
                < PADDLE_PAINTED_HEIGHT + PADDLE_SPEED)
            return;
        this.rectangle.moveInXAxis(-PADDLE_SPEED);
    }

    public void moveRight(){
        if(this.rectangle.getUpperLeft().getX()
                > SCREEN_WIDTH - PADDLE_PAINTED_HEIGHT - PADDLE_WIDTH - PADDLE_SPEED)
            return;
        this.rectangle.moveInXAxis(PADDLE_SPEED);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double x = collisionPoint.getX()
                - this.getCollisionRectangle().getUpperLeft().getX();
        double[] dots = new double[]{
                0
                ,PADDLE_WIDTH / PADDLE_PARTS
                ,2 * (PADDLE_WIDTH / PADDLE_PARTS)
                ,3 * (PADDLE_WIDTH / PADDLE_PARTS)
                ,4 * (PADDLE_WIDTH / PADDLE_PARTS)
                ,5 * (PADDLE_WIDTH / PADDLE_PARTS)
        };
        if(UTIL.isBetweenOrEquals(x,dots[0],dots[1]))
            return Velocity.fromAngleAndSpeed(-2 * ANGEL,5);
        else if (UTIL.isBetweenOrEquals(x,dots[1],dots[2]))
            return Velocity.fromAngleAndSpeed(-ANGEL,5);
        else if (UTIL.isBetweenOrEquals(x,dots[2],dots[3]))
            return new Velocity
                    (currentVelocity.dx, -Math.abs(currentVelocity.dy));
        else if (UTIL.isBetweenOrEquals(x,dots[3],dots[4]))
            return Velocity.fromAngleAndSpeed(ANGEL,5);
        return Velocity.fromAngleAndSpeed(2 * ANGEL,5);
    }

    @Override
    public boolean isPointInside(Point p) {
        if (!(UTIL.isBetween(p.getX()
                ,rectangle.getPoints()[0].getX()
                ,rectangle.getPoints()[1].getX())
                || UTIL.equals(p.getX(),rectangle.getPoints()[0].getX())
                || UTIL.equals(p.getX(),rectangle.getPoints()[1].getX())
        ))
            return false;
        return UTIL.isBetween(p.getY()
                , rectangle.getPoints()[2].getY()
                , rectangle.getPoints()[1].getY())
                || UTIL.equals(p.getY(), rectangle.getPoints()[2].getY())
                || UTIL.equals(p.getY(), rectangle.getPoints()[1].getY());
    }

    /*@Override
    public boolean pointOnBlocks(Point p) {
        return isPointInside(p);
    }*/

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.RED);
        d.fillRectangle((int)this.rectangle.getUpperLeft().getX()
                ,(int)this.rectangle.getUpperLeft().getY()
                ,(int)this.rectangle.getWidth()
                ,PADDLE_PAINTED_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawRectangle((int)this.rectangle.getUpperLeft().getX()
                ,(int)this.rectangle.getUpperLeft().getY()
                ,(int)this.rectangle.getWidth()
                ,PADDLE_PAINTED_HEIGHT);
    }

    @Override
    public void timePassed() {
        if(keyboardSensor.isPressed(KeyboardSensor.LEFT_KEY))
            moveLeft();
        if (keyboardSensor.isPressed(KeyboardSensor.RIGHT_KEY))
            moveRight();
    }

    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
        this.keyboardSensor = g.getSensor();
    }
}
