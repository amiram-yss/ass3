import biuoop.DrawSurface;

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
    public static final int NUM_BLOCK_LINES = 6;
    public static final int MAX_BLOCKS_IN_LINE = 12;
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HIGHT = 20;

    List<Collidable> collidables;
    List<Sprite> sprites;

    public GameEnvironment(){
        collidables = new ArrayList<>();

        setUpperBorderBlock();
        setRightBorderBlock();
        setLowerBorderBlock();
        setLeftBorderBlock();
        setUpperRightBorder();
        setGameBlocks();
    }

    private void setUpperRightBorder() {
        this.addCollidable(new Block(
                new Rectangle(
                        new Point(
                                778,0
                        ),22,22
                )
                ,Color.blue
        ));
    }

    private void setGameBlocks() {
        Color[] clrs = new Color[]{Color.GREEN, Color.BLUE,Color.ORANGE,Color.CYAN,Color.GRAY,Color.WHITE};
        for(int i = 0; i < NUM_BLOCK_LINES; i++){
            for(int j = 0; j < MAX_BLOCKS_IN_LINE - i; j++){
                /*var r = new Block
                        (new Rectangle
                                (new Point
                                        ((SCREEN_WIDTH - BORDER_SHORT_EDGE) - (j+1)*BLOCK_WIDTH
                                                ,(BORDER_SHORT_EDGE * 5 + BORDER_SHORT_EDGE * i) + BLOCK_HIGHT)
                                        ,BLOCK_WIDTH
                                        ,BLOCK_HIGHT)
                                , clrs[i]);
                if(i == 5 && j == 0)
                    UTIL.NOP();
                System.out.println(r.getCollisionRectangle().getPoints()[0]+"->"+r.getCollisionRectangle().getPoints()[1]);*/
                Block bta = new Block
                        (new Rectangle
                                        (new Point
                                                ((SCREEN_WIDTH - BORDER_SHORT_EDGE) - (j+1)*BLOCK_WIDTH
                                                        ,(BORDER_SHORT_EDGE * 5 + BORDER_SHORT_EDGE * i) + BLOCK_HIGHT)
                                                ,BLOCK_WIDTH
                                                ,BLOCK_HIGHT)
                                , clrs[i]);
                bta.setGameEnvironment(this);
                collidables.add(bta);
            }
        }
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

    public List<Collidable> getCollidablesSharingPoint(Point p){
        List<Collidable> ltr = new ArrayList<>();
        for (Collidable c: collidables){
            if(((Block)c).isPointInside(p))
                ltr.add(c);
        }
        return ltr;
    }

    private void setLowerBorderBlock(){
        this.addCollidable(
                new Block(
                        new Rectangle(
                                new Point(0
                                        ,SCREEN_HEIGHT-BORDER_SHORT_EDGE)
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
                                new Point(SCREEN_WIDTH-BORDER_SHORT_EDGE
                                        ,BORDER_SHORT_EDGE)
                                ,BORDER_SHORT_EDGE
                                ,SCREEN_HEIGHT - 2 * BORDER_SHORT_EDGE
                        )
                        ,Color.blue
                )
        );
    }

    private void setLeftBorderBlock(){
        this.addCollidable(
                new Block(
                        new Rectangle(
                                new Point(0,BORDER_SHORT_EDGE)
                                ,BORDER_SHORT_EDGE
                                ,SCREEN_HEIGHT - 2 * BORDER_SHORT_EDGE
                        )
                        ,Color.blue
                )
        );
        this.addCollidable(
                new Block(
                        new Rectangle(
                                new Point(780,0)
                                ,BORDER_SHORT_EDGE
                                ,BORDER_SHORT_EDGE
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

    public List<Block> pointOnBlocks(Point p){
        int counter = 0;
        List<Block> ptr = new ArrayList<>();
        for(Collidable c: collidables){
            counter++;
            if(counter == 57)
                UTIL.NOP();
            if (((Block)c).isPointInside(p))
                ptr.add((Block)c);
        }
        return ptr;
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
            if(holder == null)
                continue;
            tmp = new Line(trajectory.start().getCopy(),holder);
            if(shortest == null) {
                shortest = tmp;
                cHolder = c;
            }
            if(tmp.length() < shortest.length()) {
                shortest = tmp;
                cHolder = c;
            }
        }
        if (shortest == null)
            return null;
        return new CollisionInfo(shortest.end(),cHolder);
    }

    public boolean isPointInsideCollidable(Point p){
        //TODO SHOW THE DUDE LEFT TO ME!
        return !pointOnBlocks(p).isEmpty();
    }

    public void drawAllOn(DrawSurface d) {
        for(Collidable c: collidables){
            ((Block)c).drawOn(d);
        }
    }

    public void notifyAllTimePasses(){

    }
}
