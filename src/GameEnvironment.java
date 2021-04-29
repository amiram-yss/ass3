import biuoop.DrawSurface;

import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    public static final double SCREEN_HEIGHT = 600;
    public static final double SCREEN_WIDTH = 800;
    public static final double BORDER_SHORT_EDGE = 20;
    public static final int UPPER_BORDER_INDEX = 0;
    public static final int RIGHT_BORDER_INDEX = 1;
    public static final int LOWER_BORDER_INDEX = 2;
    public static final int LEFT_BORDER_INDEX = 3;

    List<Collidable> collidables;

    public GameEnvironment(){
        collidables = new ArrayList<>();
        setUpperBorderBlock();
        setRightBorderBlock();
        setLowerBorderBlock();
        setLeftBorderBlock();
    }

    private void setUpperBorderBlock(){
        this.addCollidable(
                new Block(
                        new Rectangle(
                                new Point(0,0)
                                ,SCREEN_WIDTH
                                ,BORDER_SHORT_EDGE
                        )
                        ,Color.blue
                )
        );
    }

    private void setLowerBorderBlock(){
        this.addCollidable(
                new Block(
                        new Rectangle(
                                new Point(0,SCREEN_HEIGHT-BORDER_SHORT_EDGE)
                                ,SCREEN_WIDTH
                                ,BORDER_SHORT_EDGE
                        )
                        ,Color.blue
                )
        );
    }

    private void setRightBorderBlock(){
        this.addCollidable(
                new Block(
                        new Rectangle(
                                new Point(SCREEN_WIDTH-BORDER_SHORT_EDGE,0)
                                ,BORDER_SHORT_EDGE
                                ,SCREEN_HEIGHT
                        )
                        ,Color.blue
                )
        );
    }

    private void setLeftBorderBlock(){
        this.addCollidable(
                new Block(
                        new Rectangle(
                                new Point(0,0)
                                ,BORDER_SHORT_EDGE
                                ,SCREEN_HEIGHT
                        )
                        ,Color.blue
                )
        );
    }

    public Block getUpperBorderBlock(){
        return (Block) this.collidables.get(UPPER_BORDER_INDEX);
    }

    public Block getRightBorderBlock(){
        return (Block) this.collidables.get(RIGHT_BORDER_INDEX);
    }

    public Block getLowerBorderBlock(){
        return (Block) this.collidables.get(LOWER_BORDER_INDEX);
    }

    public Block getLeftBorderBlock() {
        return (Block) this.collidables.get(LEFT_BORDER_INDEX);
    }

    public boolean isBorder(Block block){
        return (this.getUpperBorderBlock() == block)
                || (this.getLowerBorderBlock() == block)
                || (this.getRightBorderBlock() == block)
                || (this.getLeftBorderBlock() == block);
    }

    public Block[] getBorders()
    {
        return new Block[]{
                getUpperBorderBlock()
                ,getRightBorderBlock()
                ,getLowerBorderBlock()
                ,getLeftBorderBlock()
        };
    }
    // add the given collidable to the environment.
    public void addCollidable(Collidable c){
        collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Line tmp = null, shortest = null;
        Point holder = null, toReturn = null;
        Collidable cHolder = null;
        for(Collidable c: collidables){
            holder = trajectory.closestIntersectionToStartOfLine
                    (c.getCollisionRectangle());
            tmp = new Line(trajectory.start().getCopy(),holder);
            if(shortest == null)
                shortest = tmp;
            if(tmp.length()<shortest.length()) {
                shortest = tmp;
                cHolder = c;
            }
        }
        if (shortest == null)
            return null;
        return new CollisionInfo(shortest.end(),cHolder);
    }

    public void drawOn(DrawSurface d) {
        for(Collidable c: collidables){
            ((Block)c).drawOn(d);
        }
    }
}
