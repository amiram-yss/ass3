/**
 * @author Amiram Yassif
 * 314985474
 * ass3
 */
public class CollisionInfo {
    /**
     * Properties
     */
    private Point _collisionPoint;
    private Collidable _collisionObject;

    /**
     * Constructor
     * @param collisionPoint    The collision point.
     * @param object            The object collided.
     */
    public CollisionInfo(Point collisionPoint, Collidable object){
        _collisionPoint = collisionPoint;
        _collisionObject = object;
    }

    /**
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this._collisionPoint;
    }

    /**
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject(){
        return this._collisionObject;
    }

    /**
     * Overrides to string for printing methods.
     * @return  String format.
     */
    public String toString(){
        return String.format("Obj: " + this._collisionObject + "Point: "+this._collisionPoint);
    }
}
