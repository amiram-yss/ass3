import biuoop.DrawSurface;

import java.awt.*;

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
        return null;
    }

    public Block drawOn(DrawSurface d){
        d.setColor(this.color);
        d.fillRectangle((int)this.rectangle.getUpperLeft().getX()
                ,(int)this.rectangle.getUpperLeft().getY()
                ,(int)this.rectangle.getWidth()
                ,(int)this.rectangle.getHeight());
        return this;
    }
}
