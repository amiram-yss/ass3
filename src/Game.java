import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.*;

/**
 * @author Amiram Yassif
 * 314985474
 * ass3
 */
public class Game {
    /**
     * Consts
     */
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final double BORDER_SHORT_EDGE = 20;
    public static final int NUM_BLOCK_LINES = 6;
    public static final int MAX_BLOCKS_IN_LINE = 12;
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HIGHT = 20;

    /**
     * Properties
     */
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Ball[] balls;
    private KeyboardSensor sensor;

    /**
     *
     * @return The keyboard sensor
     */
    public KeyboardSensor getSensor(){
        return this.sensor;
    }

    /**
     *
     * @return  Array of 6 colors
     */
    private Color[] LinesColorsArray(){
        return new Color[]
                {Color.GREEN
                        ,Color.BLUE
                        ,Color.ORANGE
                        ,Color.CYAN
                        ,Color.GRAY
                        ,Color.WHITE};
    }

    /**
     * Arranges the game blocks
     */
    private void setGameBlocks() {
        Color[] clrs = LinesColorsArray();
        for(int i = 0; i < NUM_BLOCK_LINES; i++){
            for(int j = 0; j < MAX_BLOCKS_IN_LINE - i; j++){
                Block bta = new Block
                        (new Rectangle
                                (new Point
                                        ((SCREEN_WIDTH
                                                - BORDER_SHORT_EDGE)
                                                - (j+1)*BLOCK_WIDTH
                                                ,(BORDER_SHORT_EDGE
                                                * 5
                                                + BORDER_SHORT_EDGE * i)
                                                + BLOCK_HIGHT)
                                        ,BLOCK_WIDTH
                                        ,BLOCK_HIGHT)
                                , clrs[i]);
                environment.addCollidable(bta);
                sprites.addSprite(bta);
            }
        }
    }

    /**
     * Adds collidable to the collection
     * @param c The collidable
     */
    public void addCollidable(Collidable c){
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the collection
     * @param s The sprite
     */
    public void addSprite(Sprite s){
        sprites.addSprite(s);
    }

    /**
     * Sets the borders (as blocks)
     */
    private void setBorders() {
        environment.addCollidable(environment.getUpperBorderBlock());
        environment.addCollidable(environment.getLowerBorderBlock());
        environment.addCollidable(environment.getRightBorderBlock());
        environment.addCollidable(environment.getLeftBorderBlock());

        sprites.addSprite(environment.getUpperBorderBlock());
        sprites.addSprite(environment.getLowerBorderBlock());
        sprites.addSprite(environment.getRightBorderBlock());
        sprites.addSprite(environment.getLeftBorderBlock());
    }

    /**
     * Creates a balls array and sets them to the game.
     */
    private void setBalls() {
        balls = new Ball[2];
        //Set first ball + speed.
        balls[0] = new Ball(new Point(350,400),5, Color.RED,environment);
        balls[0].setVelocity(Velocity.fromAngleAndSpeed(-45,5));
        //The second one.
        balls[1] = new Ball(new Point(250,400),5, Color.GREEN,environment);
        balls[1].setVelocity(Velocity.fromAngleAndSpeed(-22.5,5));
        //Add them to sprite collections.
        sprites.addSprite(balls[0]);
        sprites.addSprite(balls[1]);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize(){
        //Set collections
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
        //Set balls
        setBalls();
        //Borders
        setBorders();
        //And blocks
        setGameBlocks();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run(){
        //Inits vars for the method
        Sleeper sleeper = new Sleeper();
        GUI gui = new GUI("game", SCREEN_WIDTH, SCREEN_HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        sensor = gui.getKeyboardSensor();

        //Insert paddle
        setPaddle(this);

        //Video settings
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        //Animation
        while(true) {
            //Handling equal timing
            long startTime = System.currentTimeMillis();

            //Paint all sprites on the surface
            d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            //And tell them time passed.
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Set the paddle on the screen
     * @param s this
     */
    private void setPaddle(Game s) {
        Paddle p = new Paddle();
        p.addToGame(this);
    }
}
