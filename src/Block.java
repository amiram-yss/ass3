import biuoop.DrawSurface;

import java.awt.*;
import java.util.List;

public class Block implements Collidable, Sprite{
    private Rectangle rectangle;
    private Color color;
    private GameEnvironment gameEnvironment;
    static int counter = 0;

    public Block(Rectangle r, Color c) {
        rectangle = r;
        color = c;
    }

    public Block(Rectangle r) {
        rectangle = r;
        color = Color.BLACK;
    }

    public GameEnvironment getGameEnvironment(){
        return this.gameEnvironment;
    }

    public void setGameEnvironment(GameEnvironment ge){
        gameEnvironment = ge;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        List<DIRECTION> directionList
                = this.rectangle.directionOfEdgeWithThePoint(collisionPoint);
        if(isOnSingleLine(directionList))
            return updateVelocityForEdge(currentVelocity, directionList);
        return updateVelocityForCorner(currentVelocity, directionList);
    }

    private Velocity updateVelocityForCorner(Velocity currentVelocity, List<DIRECTION> directionList) {
        return new Velocity(-currentVelocity.dx, -currentVelocity.dy);
    }

    private Velocity updateVelocityForEdge(Velocity currentVelocity, List<DIRECTION> directionList) {
        Velocity vtr = new Velocity(currentVelocity.dx, currentVelocity.dy);
        if(directionList.get(0) == DIRECTION.UP
                ||directionList.get(0) == DIRECTION.DOWN)
            vtr.dy *= -1;
        if(directionList.get(0) == DIRECTION.LEFT
                ||directionList.get(0) == DIRECTION.RIGHT)
            vtr.dx *= -1;
        if(directionList.size() == 2) {
            if (directionList.get(1) == DIRECTION.UP
                    || directionList.get(1) == DIRECTION.DOWN)
                vtr.dy *= -1;
            if (directionList.get(1) == DIRECTION.LEFT
                    || directionList.get(1) == DIRECTION.RIGHT)
                vtr.dx *= -1;
        }
        return vtr;
    }

    private boolean isOnSingleLine(List<DIRECTION> directionList) {
        return directionList.size() == 1;
    }

    public void drawOn(DrawSurface d){
        d.setColor(this.color);
        d.fillRectangle((int)this.rectangle.getUpperLeft().getX()
                ,(int)this.rectangle.getUpperLeft().getY()
                ,(int)this.rectangle.getWidth()
                ,(int)this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int)this.rectangle.getUpperLeft().getX()
                ,(int)this.rectangle.getUpperLeft().getY()
                ,(int)this.rectangle.getWidth()
                ,(int)this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {

    }

    public boolean isPointInside(Point p){
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

    @Override
    public boolean pointOnBlocks(Point p) {
        return isPointInside(p);
    }
}
