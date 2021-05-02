import biuoop.DrawSurface;

import java.awt.*;
import java.util.List;

public class Block implements Collidable{
    private Rectangle rectangle;
    private Color color;

    public Block(Rectangle r, Color c) {
        rectangle = r;
        color = c;
    }

    public Block(Rectangle r) {
        rectangle = r;
        color = Color.BLACK;
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
        //TODO: Edit when able.
        return updateVelocityForEdge(currentVelocity, directionList);
    }

    private Velocity updateVelocityForEdge(Velocity currentVelocity, List<DIRECTION> directionList) {
        if(currentVelocity == null  || directionList == null || directionList.isEmpty())
            return null;
        if(directionList.get(0) == DIRECTION.UP)
            return new Velocity(currentVelocity.dx, -Math.abs(currentVelocity.dy));
        if(directionList.get(0) == DIRECTION.DOWN)
            return new Velocity(currentVelocity.dx, Math.abs(currentVelocity.dy));
        if(directionList.get(0) == DIRECTION.LEFT)
            return new Velocity(-Math.abs(currentVelocity.dx), currentVelocity.dy);
        if(directionList.get(0) == DIRECTION.RIGHT)
            return new Velocity(Math.abs(currentVelocity.dx), currentVelocity.dy);
        return null;
    }

    private boolean isOnSingleLine(List<DIRECTION> directionList) {
        return directionList.size() == 1;
    }

    public Block drawOn(DrawSurface d){
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
        return this;
    }
}
