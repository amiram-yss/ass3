public class CollisionInfo {
    private Point _collisionPoint;
    private Collidable _collisionObject;

    public CollisionInfo(Point collisionPoint, Collidable object){
        _collisionPoint = collisionPoint;
        _collisionObject = object;
    }
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return this._collisionPoint;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject(){
        return this._collisionObject;
    }

    public String toString(){
        return String.format("Obj: " + this._collisionObject + "Point: "+this._collisionPoint);
    }
}
