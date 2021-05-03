/**
 * @author Amiram Yassif
 * 314985474
 * ass3
 */
public interface Collidable {
    // Return the "collision shape" of the object.
    Rectangle getCollisionRectangle();



    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint
     * @param currentVelocity
     * @return
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);

    /**
     * check if a point is inside the collidable
     * @param p The point
     * @return  Is it inside the collidable
     */
    public boolean isPointInside(Point p);

//    public boolean pointOnBlocks(Point p);
}
